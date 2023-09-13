package br.lucasmsf.aded.history.exception;

import br.lucasmsf.aded.application.exception.BaseException;
import br.lucasmsf.aded.history.enumerable.TurnAction;

public class IncorrectTurnActionException extends BaseException {
    public IncorrectTurnActionException() {
        super("Incorrect turn action");
    }
}
