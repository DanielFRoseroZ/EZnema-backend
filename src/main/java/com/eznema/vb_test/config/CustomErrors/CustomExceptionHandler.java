package com.eznema.vb_test.config.CustomErrors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UnauthorizedException.class)
    protected ResponseEntity<Object> handleUnauthorizedException(UnauthorizedException ex, WebRequest request) {
        return handleExceptionInternal(ex, "Unauthorized: " + ex.getMessage(), null, HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler(ForbiddenException.class)
    protected ResponseEntity<Object> handleForbiddenException(ForbiddenException ex, WebRequest request) {
        return handleExceptionInternal(ex, "Forbidden: " + ex.getMessage(), null, HttpStatus.FORBIDDEN, request);
    }

}
