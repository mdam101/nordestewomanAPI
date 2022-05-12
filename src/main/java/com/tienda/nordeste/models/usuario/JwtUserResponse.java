package com.tienda.nordeste.models.usuario;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class JwtUserResponse {
    private String id;
    private String email;
    private Set<UserRole> roles;
    private String token;

    @Builder(builderMethodName="jwtUserResponseBuilder")
    public JwtUserResponse(String id, String email, Set<UserRole> roles, String token) {
        this.setId(id);
        this.setEmail(email);
        this.setRoles(roles);
        this.token = token;
    }
}