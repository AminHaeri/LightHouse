package org.amin.fanoos.usermanagement.user.web.exception;

import org.amin.fanoos.usermanagement.user.web.error.ErrorResponse;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class RestControllerExceptionHandler {

    private final MessageSource messageSource;

    public RestControllerExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public ResponseEntity<ErrorResponse> handleException(Exception exception, String messageSourceId) {
        exception.printStackTrace();
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append(messageSource.getMessage(messageSourceId, null, Locale.US))
                .append(": ")
                .append("[")
                .append(exception.getMessage())
                .append("]");

        ErrorResponse errorResponse =
                new ErrorResponse(HttpStatus.BAD_REQUEST.value(), messageBuilder.toString(), System.currentTimeMillis());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(IllegalArgumentException exception) {
        return handleException(exception, "exceptions.malformed_request");
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        return handleException(exception, "exceptions.unknown");
    }
}
