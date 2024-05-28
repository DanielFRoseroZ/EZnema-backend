package com.eznema.vb_test.config.CustomErrors;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
