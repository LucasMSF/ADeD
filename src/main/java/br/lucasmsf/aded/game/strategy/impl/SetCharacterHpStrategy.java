package br.lucasmsf.aded.game.strategy.impl;

import br.lucasmsf.aded.game.Game;
import br.lucasmsf.aded.game.strategy.CreateGameStrategy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class SetCharacterHpStrategy implements CreateGameStrategy {
    @Override
    public void execute(Game game) {
        game.setPlayerHp(game.getPlayerCharacter().getHp());
        game.setCpuHp(game.getCpuCharacter().getHp());
    }
}
