package br.lucasmsf.aded.history.exception;

import br.lucasmsf.aded.application.exception.BaseException;

public class HasNoDamageException extends BaseException {
    public HasNoDamageException() {
        super("Current turn has no damage to be calculated");
    }
}
