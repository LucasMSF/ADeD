package br.lucasmsf.aded.game.strategy.impl;

import br.lucasmsf.aded.character.enumerable.CharacterType;
import br.lucasmsf.aded.game.Game;
import br.lucasmsf.aded.game.exception.CharacterTypeNotAllowedException;
import br.lucasmsf.aded.game.strategy.CreateGameStrategy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 2)
public class CpuCharacterTypeStrategy implements CreateGameStrategy {
    @Override
    public void execute(Game game) {
        if(game.getCpuCharacter().getType() != CharacterType.MONSTER) {
            throw new CharacterTypeNotAllowedException(CharacterType.MONSTER);
        }
    }
}
