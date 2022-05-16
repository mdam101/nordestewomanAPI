package com.tienda.nordeste.models.usuario;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class UsuarioInputAdminDTO {
    private String email;
    private String password;
    private Set<UserRole> rol;

    public Usuario getUsuario(UsuarioInputAdminDTO usuarioInputAdmin) {
        Usuario usuario = new Usuario();
        if(usuarioInputAdmin.getEmail() != null) {
            usuario.setEmail(usuarioInputAdmin.getEmail());
        }
        if(usuarioInputAdmin.getPassword() != null) {
            usuario.setPassword(usuarioInputAdmin.getPassword());
        }
        if(usuarioInputAdmin.getRol() != null) {
            usuario.setRol(usuarioInputAdmin.getRol());
        }
        return usuario;
    }
}
