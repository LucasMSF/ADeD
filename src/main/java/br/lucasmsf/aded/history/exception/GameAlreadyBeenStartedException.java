package br.lucasmsf.aded.history.exception;

import br.lucasmsf.aded.application.exception.BaseException;
import br.lucasmsf.aded.game.Game;

public class GameAlreadyBeenStartedException extends BaseException {
    public GameAlreadyBeenStartedException(Game game) {
        super(String.format("Game #%d already been started", game.getId()));
    }
}
