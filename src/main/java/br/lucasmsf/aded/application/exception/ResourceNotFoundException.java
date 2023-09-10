package br.lucasmsf.aded.application.exception;

public class ResourceNotFoundException extends BaseException {
    public ResourceNotFoundException() {
        super("Resource not found");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
