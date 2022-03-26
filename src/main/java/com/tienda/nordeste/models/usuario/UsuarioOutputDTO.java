package com.tienda.nordeste.models.usuario;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class UsuarioOutputDTO {
    private String id;
    private String email;
    private Set<UserRole> rol;

    public UsuarioOutputDTO(Usuario usuario) {
        this.setId(usuario.getId());
        this.setEmail(usuario.getEmail());
        this.setRol(usuario.getRol());
    }
}
