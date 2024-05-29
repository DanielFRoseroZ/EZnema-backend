package com.eznema.vb_test.auth.CustomErrors;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message) {
        super(message);
    }
}
