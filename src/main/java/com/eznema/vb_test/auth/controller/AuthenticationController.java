package com.eznema.vb_test.auth.controller;

import com.eznema.vb_test.auth.CustomErrors.ForbiddenException;
import com.eznema.vb_test.auth.CustomErrors.UnauthorizedException;
import com.eznema.vb_test.auth.model.AuthenticationResponse;
import com.eznema.vb_test.user.model.User;
import com.eznema.vb_test.auth.service.AuthenticationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
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
     * - Párametro: Objeto User que contiene la los datos del usuario a registrar.
     * - Retorno: ResponseEntity que contiene la respuesta de autenticación, con un token JWT de ser exitosa.
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User request) {
        try {
            authenticationService.register(request);
            return new ResponseEntity<>("Usuario registrado con éxito", HttpStatus.CREATED);
        } catch (UnauthorizedException e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (ForbiddenException e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al registrar el usuario", HttpStatus.BAD_REQUEST);
        }
    }


    /**
     *Método que maneja las solicitudes de inicio de sesión de usuarios
     *  - Parámetro: Objeto user que contiene los datos del usuario que desea iniciar sesión.
     *  - Retorno: ResponseEntity que contiene la respuesta de autenticación, con un JWT de ser exitosa.
     * */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody User request, HttpServletResponse response) {
        AuthenticationResponse authResponse = authenticationService.authenticate(request);

        // Crear la cookie con el token JWT
        Cookie jwtCookie = new Cookie("eznema", authResponse.getToken());
        jwtCookie.setHttpOnly(true);
        jwtCookie.setMaxAge(24*60*60);
        jwtCookie.setPath("/");
        jwtCookie.setSecure(false);

        // Configurar el encabezado Set-Cookie con SameSite
        String sameSiteCookie = String.format("eznema=%s; Max-Age=%d; Path=/; Secure; HttpOnly; SameSite=None",
                authResponse.getToken(), 24 * 60 * 60);
        response.setHeader("Set-Cookie", sameSiteCookie);

        // Añadir la cookie a la respuesta
        response.addCookie(jwtCookie);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
