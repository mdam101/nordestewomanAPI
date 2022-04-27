package com.tienda.nordeste.models.usuario;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDTOConverter {
    @Autowired
    private ModelMapper modelMapper;

    public UserDTOConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public GetUserDTO convertUsuarioToGetUserDTO(Usuario usuario) {
        return modelMapper.map(usuario, GetUserDTO.class);
    }
}
