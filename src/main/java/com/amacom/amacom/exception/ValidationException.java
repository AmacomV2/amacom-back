package com.amacom.amacom.exception;

public class ValidationException extends RuntimeException {
    private static final long serialVersionUID = 3053566579779021848L;

    public ValidationException() {
        super("Error on validating fields.");
    }

    public ValidationException(String aMensaje) {
        super(aMensaje);
    }
}