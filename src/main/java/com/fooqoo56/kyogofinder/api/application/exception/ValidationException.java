package com.fooqoo56.kyogofinder.api.application.exception;

public class ValidationException extends RuntimeException {

    private static final long serialVersionUID = -3147065045035775676L;

    public ValidationException() {

    }

    public ValidationException(final String message) {
        super(message);
    }

    public ValidationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ValidationException(final String errorType, final String msg) {
        super(errorType + " : " + msg);
    }

    public interface ValidationMessage {
        String PARAMETER_VALID_ERROR = "パラメータが不正です。";
    }
}
