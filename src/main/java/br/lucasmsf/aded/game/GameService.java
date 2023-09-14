package br.lucasmsf.aded.game;

import br.lucasmsf.aded.application.exception.ResourceNotFoundException;
import br.lucasmsf.aded.character.Character;
import br.lucasmsf.aded.character.CharacterService;
import br.lucasmsf.aded.game.enumerable.GamePlayerType;
import br.lucasmsf.aded.game.strategy.CreateGameStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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

    private GamePlayerType attackerPlayerType(Game game, Character character) {
        if (Objects.equals(character.getId(), game.getPlayerCharacter().getId())) {
            return GamePlayerType.PLAYER;
        } else {
            return GamePlayerType.CPU;
        }
    }

    private int savePlayerRemaining(Game game, int damage, GamePlayerType attackerPlayerType) {
        return switch (attackerPlayerType) {
            case PLAYER -> {
                var hp = game.getCpuHp() - damage;
                game.setCpuHp(hp);
                this.gameRepository.save(game);
                yield hp;
            }
            case CPU -> {
                var hp = game.getPlayerHp() - damage;
                game.setPlayerHp(hp);
                this.gameRepository.save(game);
                yield hp;
            }
        };
    }

    public int applyDamage(Game game, Character character, int damage) {
        var playerType = this.attackerPlayerType(game, character);
        return this.savePlayerRemaining(game, damage, playerType);
    }

}
