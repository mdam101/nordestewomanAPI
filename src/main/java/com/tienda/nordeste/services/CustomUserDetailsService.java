package com.tienda.nordeste.services;

import java.util.Optional;

import com.tienda.nordeste.models.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> user = usuarioService.findByEmail(email);
        if (user.isPresent()) {
            return (UserDetails) user.get();
        } else {
            throw new UsernameNotFoundException(email + " not found");
        }
    }

    public UserDetails loadUserById(String idUser) throws UsernameNotFoundException {
            Optional<Usuario> user = usuarioService.findById(idUser);
        if (user.isPresent()) {
            return (UserDetails) user.get();
        } else {
            throw new UsernameNotFoundException(idUser + " not found");
        }
    }
}