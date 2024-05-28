package com.eznema.vb_test.model;

public class AuthenticationResponse {
    private final String token;

    public AuthenticationResponse(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
