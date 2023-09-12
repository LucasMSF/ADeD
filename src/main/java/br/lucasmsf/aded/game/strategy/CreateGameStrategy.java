package br.lucasmsf.aded.game.strategy;

import br.lucasmsf.aded.game.Game;

@FunctionalInterface
public interface CreateGameStrategy {
   public abstract void execute(Game game);
}
