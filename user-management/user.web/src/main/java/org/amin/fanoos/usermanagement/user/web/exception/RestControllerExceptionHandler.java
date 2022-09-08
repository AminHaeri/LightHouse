package org.amin.fanoos.usermanagement.user.web.exception;

import org.amin.fanoos.usermanagement.user.web.error.ErrorResponse;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class RestControllerExceptionHandler {

    private final MessageSource messageSource;

    public RestControllerExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public ResponseEntity<ErrorResponse> handleException(Exception exception, HttpStatus status) {
        exception.printStackTrace();

        ErrorResponse errorResponse =
                new ErrorResponse(status.value(), exception.getMessage(), System.currentTimeMillis());

        return new ResponseEntity<>(errorResponse, status);
    }

    public ResponseEntity<ErrorResponse> handleException(Exception exception, HttpStatus status, String messageSourceId) {
        exception.printStackTrace();
        String message = messageSource.getMessage(messageSourceId, null, Locale.getDefault()) +
                ": " +
                "[" +
                exception.getMessage() +
                "]";

        ErrorResponse errorResponse =
                new ErrorResponse(status.value(), message, System.currentTimeMillis());

        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(IllegalArgumentException exception) {
        return handleException(exception, HttpStatus.BAD_REQUEST, "exceptions.malformed_request");
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(AuthorizationServiceException exception) {
        return handleException(exception, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        return handleException(exception, HttpStatus.BAD_REQUEST, "exceptions.unknown");
    }
}
