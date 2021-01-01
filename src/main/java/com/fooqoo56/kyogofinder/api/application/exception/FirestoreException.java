package com.fooqoo56.kyogofinder.api.application.exception;

public class FirestoreException extends RuntimeException {

    private static final long serialVersionUID = 8779220608126634989L;

    /**
     * デフォルトコンストラクタ
     */
    public FirestoreException() {

    }

    /**
     * message引数コンストラクタ
     *
     * @param message メッセージ
     */
    public FirestoreException(final String message) {
        super(message);
    }

    /**
     * コンストラクタ
     *
     * @param message メッセージ
     * @param cause Throwable
     */
    public FirestoreException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
