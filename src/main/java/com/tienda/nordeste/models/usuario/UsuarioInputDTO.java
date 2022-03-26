package com.tienda.nordeste.models.usuario;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioInputDTO {
    private String email;
    private String password;

    public Usuario getUsuario(UsuarioInputDTO usuarioInput) {
        Usuario usuario = new Usuario();
        if(usuarioInput.getEmail() != null) {
            usuario.setEmail(usuarioInput.getEmail());
        }
        if(usuarioInput.getPassword() != null) {
            usuario.setPassword(usuarioInput.getPassword());
        }
        return usuario;
    }
}
