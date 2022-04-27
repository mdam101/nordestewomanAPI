package com.tienda.nordeste.controllers;

import com.tienda.nordeste.configurations.security.jwt.jwtTokenProvider;
import com.tienda.nordeste.models.usuario.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final jwtTokenProvider tokenProvider;
    private final UserDTOConverter converter;

    @PostMapping("/auth/login")
    public JwtUserResponse login(@RequestBody UsuarioInputDTO userLogin) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLogin.getEmail(),
                        userLogin.getPassword()
                ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Usuario user = (Usuario) authentication.getPrincipal();
        String jwtToken = tokenProvider.generateToken(authentication);

        return convertUserEntityAndTokenToJwtUserResponse(user, jwtToken);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/user/me")
    public UsuarioOutputDTO me(@AuthenticationPrincipal Usuario user) {
        return new UsuarioOutputDTO(user);
    }

    private JwtUserResponse convertUserEntityAndTokenToJwtUserResponse(Usuario user, String jwtToken) {
        return JwtUserResponse
                .jwtUserResponseBuilder()
                .id(Integer.valueOf(user.getId()))
                .email(user.getEmail())
                .roles(user.getRol())
                .token(jwtToken)
                .build();
    }
}