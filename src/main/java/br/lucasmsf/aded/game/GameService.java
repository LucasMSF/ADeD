package br.lucasmsf.aded.game;

import br.lucasmsf.aded.application.exception.ResourceNotFoundException;
import br.lucasmsf.aded.character.CharacterService;
import br.lucasmsf.aded.game.strategy.CreateGameStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Game create(String playerName, Long playerCharacterId, Long cpuCharacterId) {
        var game = new Game();
        game.setPlayerName(playerName);
        game.setPlayerCharacter(this.characterService.find(playerCharacterId));

        if (cpuCharacterId != null) {
            game.setCpuCharacter(this.characterService.find(cpuCharacterId));
        }

        this.createGameStrategyList.forEach(createGameValidation -> createGameValidation.execute(game));

        return this.create(game);
    }

    public Game create(Long playerCharacterId, Long cpuCharacterId) {
        return this.create("Anonymous", playerCharacterId, cpuCharacterId);
    }

}
