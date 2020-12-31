package com.fooqoo56.kyogofinder.api.application.exception;

public class ValidationException extends RuntimeException {

    private static final long serialVersionUID = -3147065045035775676L;

    /**
     * デフォルトコンストラクタ
     */
    public ValidationException() {

    }

    /**
     * コンストラクタ
     *
     * @param message メッセージ
     */
    public ValidationException(final String message) {
        super(message);
    }

    /**
     * コンストラクタ
     *
     * @param message メッセージ
     * @param cause   Throwable
     */
    public ValidationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * コンストラクタ
     *
     * @param errorType エラー種別
     * @param msg       メッセージ
     */
    public ValidationException(final String errorType, final String msg) {
        super(errorType + " : " + msg);
    }

    public interface ValidationMessage {
        String PARAMETER_VALID_ERROR = "パラメータが不正です。";
    }
}
