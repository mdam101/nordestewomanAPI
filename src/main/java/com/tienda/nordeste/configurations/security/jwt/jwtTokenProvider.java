package com.tienda.nordeste.configurations.security.jwt;

import java.util.Date;
import java.util.stream.Collectors;

import com.tienda.nordeste.models.usuario.UserRole;
import com.tienda.nordeste.models.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class jwtTokenProvider {

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";

    @Value("${jwt.secret:EnUnLugarDeLaManchaDeCuyoNombreNoQuieroAcordarmeNoHaMuchoTiempoQueViviaUnHidalgo}")
    private String jwtSecreto;

    @Value("${jwt.token-expiration:86400}")
    private int jwtDuracionTokenEnSegundos;

    public String generateToken(Authentication authentication) {

        Usuario user = (Usuario) authentication.getPrincipal();

        Date tokenExpirationDate = new Date(System.currentTimeMillis() + (jwtDuracionTokenEnSegundos * 1000));

        return Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(jwtSecreto.getBytes()), SignatureAlgorithm.HS512)
                .setHeaderParam("typ", TOKEN_TYPE)
                .setSubject(String.valueOf(user.getId()))
                .setIssuedAt(new Date())
                .setExpiration(tokenExpirationDate)
                .claim("email", user.getEmail())
                .claim("roles", user.getRol().stream().map(UserRole::name).collect(Collectors.joining(", ")))
                .compact();
    }

    public String getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor(jwtSecreto.getBytes()))
                .parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {

            Jwts.parser().setSigningKey(jwtSecreto.getBytes()).parseClaimsJws(authToken);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}