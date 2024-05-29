package com.eznema.vb_test.auth.config;

import com.eznema.vb_test.auth.CustomErrors.CustomAccesDeniedHandler;
import com.eznema.vb_test.auth.filter.JwtAuthenticationFilter;
import com.eznema.vb_test.user.service.UserDetailsServiceImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Clase de configuración que define la seguridad de la aplicación.
 * Configura la autenticación, politicas de sesión, cifrado de contraseñas y filtros de seguridad con un JWT personalizado.
 * */

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsServiceImp userDetailsServiceImp;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final CustomAccesDeniedHandler accesDeniedHandler;


    /**
     * Constructor ->
     *  - userDetailsServiceImp: Servicio personalizado para la gestión de detalles de usuario.
     *  - jwtAuthenticationFilter: Filtro personalizado para la autenticación con JWT.
     * */
    public SecurityConfig(UserDetailsServiceImp userDetailsServiceImp, JwtAuthenticationFilter jwtAuthenticationFilter, CustomAccesDeniedHandler accesDeniedHandler) {
        this.userDetailsServiceImp = userDetailsServiceImp;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.accesDeniedHandler = accesDeniedHandler;
    }


    /**
     * Configuración de la cadena de filtros de seguridad a aplicar
     *
     * -Parámetro: http -> Configurador de seguridad propio de HTTP
     * -Retorno: Instancia de la clase "SecurityFilterChain" (Cadena de filtro personalizada ubicada en el directorio filter)
     * */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        req->req.requestMatchers("/login/**", "/register/**", "/logout/**")
                                .permitAll()
                                .requestMatchers("/admin_only/**").hasAuthority("ADMIN")
                                .anyRequest()
                                .authenticated()

                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                )
                .userDetailsService(userDetailsServiceImp)
                .exceptionHandling(e->e.accessDeniedHandler(accesDeniedHandler)
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    /**
     * Codifica las contraseñas a traves de BCryptPasswordEncoder
     * */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * Proporicona el admiistrador de autenticación
     *  - Parámetro: configuration -> Configuración de autenticación.
     *  - Retorno: Instancia de la clase AuthenticationManager propia de Spring Security
     * */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
