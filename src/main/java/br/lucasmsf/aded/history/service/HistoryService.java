package br.lucasmsf.aded.history.service;

import br.lucasmsf.aded.character.Character;
import br.lucasmsf.aded.game.Game;
import br.lucasmsf.aded.game.GameService;
import br.lucasmsf.aded.game.util.Dice;
import br.lucasmsf.aded.history.entity.History;
import br.lucasmsf.aded.history.repository.HistoryRepository;
import br.lucasmsf.aded.history.strategy.StartGameStrategy;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class HistoryService {
    private final HistoryRepository historyRepository;
    private final HistoryTurnService historyTurnService;
    private final GameService gameService;

    private final List<StartGameStrategy> startGameStrategyList;

    @Transactional
    public History create(
            Game game,
            Character attackCharacter,
            Character defenseCharacter
    ) {
        var history = new History();
        history.setGame(game);
        history.setStartCharacter(attackCharacter);

        var historyTurn = this.historyTurnService.create(
                this.historyRepository.save(history),
                attackCharacter,
                defenseCharacter
        );

        return historyTurn.getHistory();
    }

    private int rollForCharacter(Character character) {
        return Dice.roll(
                character.getFacesOfTheDice(),
                character.getFacesOfTheDice()
        );
    }

    private Map<String, Character> getAttackAndDefense(Game game, Character attackCharacter) {
        var defenseCharacter = Objects.equals(attackCharacter.getId(), game.getPlayerCharacter().getId())
                ? game.getCpuCharacter()
                : game.getPlayerCharacter();

        return new HashMap<>() {{
            put("attack", attackCharacter);
            put("defense", defenseCharacter);
        }};
    }

    @Transactional
    public History startGame(Game game) {
        this.startGameStrategyList
                .forEach(startGameStrategy -> startGameStrategy.execute(game));

        int playerRoll, cpuRoll;

        do {
            playerRoll = Dice.roll(20);
            cpuRoll = Dice.roll(20);
        } while (playerRoll == cpuRoll);

        var startCharacter = playerRoll > cpuRoll
                ? game.getPlayerCharacter()
                : game.getCpuCharacter();

        var turnCharacters = this.getAttackAndDefense(game, startCharacter);

        return this.create(
                game,
                turnCharacters.get("attack"),
                turnCharacters.get("defense")
        );

    }

    @Transactional
    public History startGame(Long gameId) {
        return this.startGame(this.gameService.find(gameId));
    }

}
