package br.lucasmsf.aded.history.exception;

import br.lucasmsf.aded.application.exception.BaseException;
import br.lucasmsf.aded.game.Game;

public class TurnAlreadyBeenStartedException extends BaseException {
    public TurnAlreadyBeenStartedException() {
        super("Current turn already been started");
    }
}
