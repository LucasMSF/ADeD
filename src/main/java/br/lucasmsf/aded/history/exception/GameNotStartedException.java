package br.lucasmsf.aded.history.exception;

import br.lucasmsf.aded.application.exception.BaseException;
import br.lucasmsf.aded.game.Game;

public class GameNotStartedException extends BaseException {
    public GameNotStartedException(Game game) {
        super(String.format("Game #%d not started", game.getId()));
    }
}
