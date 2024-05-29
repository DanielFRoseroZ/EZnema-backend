package com.eznema.vb_test.auth.filter;

import com.eznema.vb_test.user.model.User;
import com.eznema.vb_test.auth.service.JwtService;
import com.eznema.vb_test.user.service.UserDetailsServiceImp;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filtro personalizado que se ejecuta una única vez por cada solicitud.
 * Su función es interceptar las solicitudes HTTP, extraer y validar el token JWT del encabezado de autorización, y establecer el contexto de seguridad si el token es válido.
 * Nota: Es un componente de Spring y debe ser administrado por el contenedor de Spring.
 * */

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsServiceImp userDetailsService;

    /**
     * Constructor ->
     *  - jwtService: Maneja las operaciones relacionadas con JWT.
     *  - userDetailsService: Servicio que proporciona los detalles de usuario personalizado.
     * */
    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsServiceImp userDetails) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetails;
    }


    /**
     * doFilterInternal: Este método se ejecuta una vez por cada solicitud. Extrae el token JWT del encabezado de autorización, lo valida y establece el contexto de seguridad si el token es válido.
     *  - Parámetros->
     *      - request: La solicitud HTTP.
     *      -response: La respeusta del servidor.
     *      filterChain: Cadena de filtros personaliozada para pasar la solicitud y respuesta al siguiente filtro.
     * */
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer "))
        {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);
        String username = jwtService.extractUsername(token);

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null)
        {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if(jwtService.isValid(token, (User) userDetails))
            {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
