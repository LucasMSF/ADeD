package br.lucasmsf.aded.game.strategy.impl;

import br.lucasmsf.aded.character.CharacterRepository;
import br.lucasmsf.aded.game.Game;
import br.lucasmsf.aded.game.strategy.CreateGameStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Order(value = 1)
public class SelectMonsterStrategy implements CreateGameStrategy {
    private final CharacterRepository characterRepository;

    @Override
    public void execute(Game game) {
        if (game.getCpuCharacter() == null) {
            game.setCpuCharacter(
                    this.characterRepository.findRandomMonsterCharacter()
            );
            game.setCpuHp(game.getCpuCharacter().getHp());
        }
    }
}
