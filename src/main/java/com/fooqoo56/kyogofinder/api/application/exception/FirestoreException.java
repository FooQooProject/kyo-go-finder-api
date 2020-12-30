package com.fooqoo56.kyogofinder.api.application.exception;

public class FirestoreException extends RuntimeException {

    private static final long serialVersionUID = 8779220608126634989L;

    public FirestoreException() {

    }

    public FirestoreException(final String message) {
        super(message);
    }

    public FirestoreException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
