package com.goit.crud.exception;

public class DeleteClientException extends RuntimeException {
    public DeleteClientException() {
    }

    public DeleteClientException(String message) {
        super(message);
    }

    public DeleteClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
