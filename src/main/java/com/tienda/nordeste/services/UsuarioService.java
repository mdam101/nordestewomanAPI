package com.tienda.nordeste.services;

import com.tienda.nordeste.models.usuario.UserRole;
import com.tienda.nordeste.models.usuario.Usuario;
import com.tienda.nordeste.repositories.UsuarioRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioService extends BaseService<Usuario, String, UsuarioRepository> {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<Usuario> findByEmail(String email) {
        return this.usuarioRepository.findByEmail(email);
    }

    public Optional<Usuario> findById(String id) { return this.usuarioRepository.findById(id); }

    public Usuario nuevoUsuario(Usuario usuario) {
        Set<UserRole> defaultRole = new HashSet<UserRole>();
        //encode password
        if(usuario.getRol() == null) {
            defaultRole.add(UserRole.USER);
            usuario.setRol(defaultRole);
        } else if(usuario.getRol().size() == 0) {
            defaultRole.add(UserRole.USER);
            usuario.setRol(defaultRole);
        }
        return this.usuarioRepository.save(usuario);
    }
}
