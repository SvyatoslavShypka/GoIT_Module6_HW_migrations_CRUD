package com.goit.crud.exception;

public class InsertNameException extends RuntimeException {
    public InsertNameException() {
    }

    public InsertNameException(String message) {
        super(message);
    }

    public InsertNameException(String message, Throwable cause) {
        super(message, cause);
    }
}
