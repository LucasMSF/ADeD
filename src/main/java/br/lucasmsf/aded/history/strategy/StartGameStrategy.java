package br.lucasmsf.aded.history.strategy;

import br.lucasmsf.aded.game.Game;

@FunctionalInterface
public interface StartGameStrategy {
    void execute(Game game);
}
