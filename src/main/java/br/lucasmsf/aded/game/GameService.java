package br.lucasmsf.aded.game;

import br.lucasmsf.aded.application.exception.ResourceNotFoundException;
import br.lucasmsf.aded.character.Character;
import br.lucasmsf.aded.character.CharacterService;
import br.lucasmsf.aded.game.strategy.CreateGameStrategy;
import br.lucasmsf.aded.game.util.Dice;
import br.lucasmsf.aded.history.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class GameService {
    private final GameRepository gameRepository;
    private final CharacterService characterService;

    private final List<CreateGameStrategy> createGameStrategyList;

    public Game find(Long id) {
        return this.gameRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    public Game create(Game game) {
        return this.gameRepository.save(game);
    }

    public Game create(Long playerCharacterId, Long cpuCharacterId) {
        var game = new Game();
        game.setPlayerCharacter(this.characterService.find(playerCharacterId));

        if (cpuCharacterId != null) {
            game.setCpuCharacter(this.characterService.find(cpuCharacterId));
        }

        this.createGameStrategyList.forEach(createGameValidation -> createGameValidation.execute(game));

        return this.create(game);
    }

}
