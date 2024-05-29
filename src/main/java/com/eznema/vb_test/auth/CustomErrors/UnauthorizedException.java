package com.eznema.vb_test.auth.CustomErrors;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
