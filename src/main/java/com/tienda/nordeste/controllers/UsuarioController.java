package com.tienda.nordeste.controllers;

import com.tienda.nordeste.models.usuario.*;
import com.tienda.nordeste.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.OPTIONS})
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    //Registrar usuario
    @PostMapping("/usuario")
    public ResponseEntity<?> registrarUsuario(@RequestBody UsuarioInputDTO usuarioInput) {
        try {
            if (!usuarioService.findByEmail(usuarioInput.getEmail()).isPresent()) {
                Usuario usuario = usuarioInput.getUsuario(usuarioInput);
                usuario = usuarioService.nuevoUsuario(usuario);
                return ResponseEntity.status(HttpStatus.CREATED).body(new UsuarioOutputDTO(usuario));
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    //Ver usuarios
    @GetMapping("/usuarios")
    public ResponseEntity<?> getUsuarios() {
        try {
            List<Usuario> usuarios = usuarioService.findAll();
            List<UsuarioOutputDTO> usuarioOutput = new ArrayList<>();
            for(Usuario usuario: usuarios) {
                usuarioOutput.add(new UsuarioOutputDTO(usuario));
            }
            return ResponseEntity.status(HttpStatus.OK).body(usuarioOutput);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    //Registrar usuarios admin
    @PostMapping("/usuario/admin")
        public ResponseEntity<?> registrarUsuarioAdmin(@RequestBody UsuarioInputAdminDTO usuarioInputAdmin) {
        try {
            if (!usuarioService.findByEmail(usuarioInputAdmin.getEmail()).isPresent()) {
                Usuario usuario = usuarioInputAdmin.getUsuario(usuarioInputAdmin);
                usuario = usuarioService.nuevoUsuario(usuario);
                return ResponseEntity.status(HttpStatus.CREATED).body(new UsuarioOutputDTO(usuario));
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
