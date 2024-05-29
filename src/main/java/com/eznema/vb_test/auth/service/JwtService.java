package com.eznema.vb_test.auth.service;


import com.eznema.vb_test.user.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    public String extractUsername(String token)
        {
            return extractClaim(token, Claims::getSubject);
        }

        public boolean isValid(String token, User user)
        {
            String username = extractUsername(token);
            return (username.equals(user.getUsername())) && !isTokenExpired(token);
        }

        private boolean isTokenExpired(String token)
        {
            return extractExpiration(token).before(new Date());
        }

        private Date extractExpiration(String token)
        {
            return extractClaim(token, Claims::getExpiration);
        }

        public <T> T extractClaim(String token, Function<Claims, T> resolver)
        {
            Claims claims = extractAllClaimsFromToken(token);
            return resolver.apply(claims);
        }

        private Claims extractAllClaimsFromToken(String token)
        {
            return Jwts
                    .parser()
                    .verifyWith(getSigninKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        };

        public String generateToken(User user)
        {
            return Jwts
                    .builder()
                    .claim("id", user.getId())
                    .claim("firstName", user.getFirstName())
                    .claim("lastName", user.getLastName())
                    .claim("phone", user.getPhone())
                    .subject(user.getUsername())
                    .claim("role", user.getRole())
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(new Date(System.currentTimeMillis()+ 24*60*60*1000))
                    .signWith(getSigninKey())
                    .compact();
        }

        private SecretKey getSigninKey()
        {
            String SECRET_KEY = "199e43d0cb1869fae7bcc6ed70eca843aaac93fb63d90329d7593123a04c953d";
            byte[] keyBytes = Decoders.BASE64URL.decode(SECRET_KEY);
            return Keys.hmacShaKeyFor(keyBytes);
        }


}
