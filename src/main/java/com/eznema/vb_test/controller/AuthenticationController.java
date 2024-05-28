package com.eznema.vb_test.controller;

import com.eznema.vb_test.model.AuthenticationResponse;
import com.eznema.vb_test.model.User;
import com.eznema.vb_test.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controlador REST propio de Spring que maneja las solicitudes de registro y autenticación de usuarios.
 * Utiliza un servicio de autenticación para procesar estas solicitudes y devolver las respuestas de autenticación.
 * */

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    /**
     * Constructor: authenticationService es un servicio que maneja la lógica de registro y autenticación de usuarios.
     * */
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    /**
     * Método que maneja las solicitudes de registro de nuevos usuarios.
     *  - Párametro: Objeto User que contiene la los datos del usuario a registrar.
     *  - Retorno: ResponseEntity que contiene la respuesta de autenticación, con un token JWT de ser exitosa.
     * */
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody User request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }


    /**
     *Método que maneja las solicitudes de inicio de sesión de usuarios
     *  - Parámetro: Objeto user que contiene los datos del usuario que desea iniciar sesión.
     *  - Retorno: ResponseEntity que contiene la respuesta de autenticación, con un JWT de ser exitosa.
     * */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody User request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

}
