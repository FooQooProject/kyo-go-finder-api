package com.fooqoo56.kyogofinder.api.presentation.controller.advice;

import com.fooqoo56.kyogofinder.api.application.exception.FirestoreException;
import com.fooqoo56.kyogofinder.api.application.exception.ValidationException;
import com.fooqoo56.kyogofinder.api.presentation.dto.response.ErrorResponse;
import java.net.SocketTimeoutException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

    /**
     * バリデーションエラー
     *
     * @param request   リクエスト
     * @param exception 例外
     * @return エラーレスポンス
     */
    @ExceptionHandler({ValidationException.class, ConstraintViolationException.class,
            BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleException(final HttpServletRequest request,
                                         final Exception exception) {
        log.error(createErrorMessage("パラメータバリデーションエラーが発生しました", request), exception);
        return createErrorResponse(HttpStatus.BAD_REQUEST);
    }

    /**
     * 404エラー
     *
     * @param request   リクエスト
     * @param exception 例外
     * @return エラーレスポンス
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleException(
            final HttpServletRequest request, final NoHandlerFoundException exception) {
        log.error(createErrorMessage("存在しないパスにアクセスされてます", request), exception);
        return createErrorResponse(HttpStatus.NOT_FOUND);
    }

    /**
     * タイムアウトエラー
     *
     * @param request   リクエスト
     * @param exception 例外
     * @return エラーレスポンス
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
    @ResponseBody
    public ErrorResponse handleException(
            final HttpServletRequest request, final SocketTimeoutException exception) {
        log.error(createErrorMessage("API呼び出し時にタイムアウトが発生しています", request), exception);
        return createErrorResponse(HttpStatus.REQUEST_TIMEOUT);
    }

    /**
     * 内部エラー
     *
     * @param request   リクエスト
     * @param exception 例外
     * @return エラーレスポンス
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleException(final HttpServletRequest request,
                                         final Throwable exception) {
        log.error(createErrorMessage("内部エラーが発生しています", request), exception);
        return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 許可しないメソッドによるエラー
     *
     * @param request   リクエスト
     * @param exception 例外
     * @return エラーレスポンス
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ResponseBody
    public ErrorResponse handleException(
            final HttpServletRequest request,
            final HttpRequestMethodNotSupportedException exception) {
        log.error(createErrorMessage("許可されていないHTTPメソッドでアクセスされています", request), exception);
        return createErrorResponse(HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * Firestoreのエラー
     *
     * @param request   リクエスト
     * @param exception 例外
     * @return エラーレスポンス
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleException(
            final HttpServletRequest request,
            final FirestoreException exception) {
        log.error(createErrorMessage("Firestoreでエラーが発生しました", request), exception);
        return createErrorResponse(HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * エラーメッセージ取得
     *
     * @param message メッセージ
     * @param request リクエスト
     * @return エラーメッセージ
     */
    private String createErrorMessage(final String message, final HttpServletRequest request) {
        return message + ": リクエストURL:" + request.getRequestURI();
    }

    /**
     * エラーレスポンス取得
     *
     * @param httpStatus ステータスコード
     * @return エラーレスポンス
     */
    private ErrorResponse createErrorResponse(final HttpStatus httpStatus) {
        return new ErrorResponse(httpStatus.value(), httpStatus.getReasonPhrase());
    }
}
