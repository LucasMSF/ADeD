package br.lucasmsf.aded.history.service;

import br.lucasmsf.aded.application.exception.ResourceNotFoundException;
import br.lucasmsf.aded.character.Character;
import br.lucasmsf.aded.game.Game;
import br.lucasmsf.aded.game.GameService;
import br.lucasmsf.aded.application.util.Dice;
import br.lucasmsf.aded.history.entity.History;
import br.lucasmsf.aded.history.repository.HistoryRepository;
import br.lucasmsf.aded.history.strategy.StartGameStrategy;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class HistoryService {
    private final HistoryRepository historyRepository;
    private final HistoryTurnService historyTurnService;
    private final GameService gameService;
    private final List<StartGameStrategy> startGameStrategyList;

    private static final int START_GAME_DICE_FACES = 20;

    public List<History> findAll() {
        return this.historyRepository.findAll();
    }

    public History findByGameId(Long gameId) {
        var history = this.historyRepository.findByGameId(gameId)
                .orElseThrow(ResourceNotFoundException::new);

        Collections.sort(history.getTurns());

        return history;
    }

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
            playerRoll = Dice.roll(START_GAME_DICE_FACES);
            cpuRoll = Dice.roll(START_GAME_DICE_FACES);
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
