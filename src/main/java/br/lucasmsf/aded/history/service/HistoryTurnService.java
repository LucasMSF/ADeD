package br.lucasmsf.aded.history.service;

import br.lucasmsf.aded.application.exception.ResourceNotFoundException;
import br.lucasmsf.aded.application.util.Dice;
import br.lucasmsf.aded.character.Character;
import br.lucasmsf.aded.game.GameService;
import br.lucasmsf.aded.history.entity.History;
import br.lucasmsf.aded.history.entity.HistoryTurn;
import br.lucasmsf.aded.history.enumerable.TurnAction;
import br.lucasmsf.aded.history.exception.GameNotStartedException;
import br.lucasmsf.aded.history.repository.HistoryTurnRepository;
import br.lucasmsf.aded.history.strategy.StartTurnStrategy;
import br.lucasmsf.aded.history.strategy.TurnActionStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HistoryTurnService {
    private final HistoryTurnRepository historyTurnRepository;
    private final GameService gameService;
    private final List<StartTurnStrategy> startTurnStrategyList;
    private final List<TurnActionStrategy> turnActionStrategyList;

    private static final int TURN_DICE_FACES = 12;

    public HistoryTurn find(Long id) {
        return this.historyTurnRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    public HistoryTurn create(
            History history,
            Character attackingCharacter,
            Character defenseCharacter
    ) {
        var historyTurn = new HistoryTurn();
        historyTurn.setHistory(history);
        historyTurn.setAttackingCharacter(attackingCharacter);
        historyTurn.setDefenderCharacter(defenseCharacter);

        return this.historyTurnRepository.save(historyTurn);
    }

    public HistoryTurn nextTurn(HistoryTurn currentTurn) {
        return this.create(
                currentTurn.getHistory(),
                currentTurn.getDefenderCharacter(),
                currentTurn.getAttackingCharacter()
        );
    }

    public boolean haveDamage(int attack, int defense) {
        return attack > defense;
    }

    public HistoryTurn getCurrentTurn(History history) {
        return this.historyTurnRepository
                .findLatestHistoryTurnByHistoryId(history.getId());
    }

    private int attack(HistoryTurn historyTurn) {
        var character = historyTurn.getAttackingCharacter();
        var attack = Dice.roll(TURN_DICE_FACES);
        attack += character.getStrength() + character.getAgility();
        return attack;
    }

    private int defense(HistoryTurn historyTurn) {
        var character = historyTurn.getDefenderCharacter();
        var defense = Dice.roll(TURN_DICE_FACES);
        defense += character.getDefense() + character.getAgility();
        return defense;
    }

    public HistoryTurn startTurn(HistoryTurn historyTurn, TurnAction turnAction) {
        startTurnStrategyList.forEach(startTurnStrategy -> startTurnStrategy.execute(historyTurn));
        turnActionStrategyList.forEach(turnActionStrategy -> turnActionStrategy.execute(historyTurn, turnAction));
        var attack = this.attack(historyTurn);
        var defense = this.defense(historyTurn);
        historyTurn.setAttack(attack);
        historyTurn.setDefense(defense);
        if(!this.haveDamage(attack, defense)) {
            this.nextTurn(historyTurn);
        }
        return this.historyTurnRepository.save(historyTurn);
    }

    public HistoryTurn startTurn(Long gameId, TurnAction turnAction) {
        var game = this.gameService.find(gameId);
        var history = game.getHistory();
        if (history == null) {
            throw new GameNotStartedException(game);
        }
        var historyTurn = this.getCurrentTurn(history);
        return this.startTurn(historyTurn, turnAction);
    }
}
