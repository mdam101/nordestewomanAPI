package com.tienda.nordeste.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tienda.nordeste.models.categoria.Categoria;
import com.tienda.nordeste.models.ropa.Ropa;
import com.tienda.nordeste.models.ropa.RopaInputDTO;
import com.tienda.nordeste.repositories.RopaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class RopaService extends BaseService<Ropa, String, RopaRepository> {
    @Autowired
    private RopaRepository ropaRepository;

    public Optional<Ropa> findById(String id){
        return ropaRepository.findById(id);
    }

    public List<Ropa> findByCategoriaNombre(String nombreCategoria) { return ropaRepository.findByCategoriaNombre(nombreCategoria); }

    public Ropa crearRopa(String nombre, String descripcion, Double precio, Categoria categoria, MultipartFile file) throws IOException {
         Ropa ropa = new Ropa();
         ropa.setNombre(nombre);
         ropa.setDescripcion(descripcion);
         ropa.setPrecio(precio);
         ropa.setCategoria(categoria);
         ropa.setImagen(file.getBytes());
         return ropa;
    }

    public RopaInputDTO getRopaInputDTO(String ropa) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(ropa, RopaInputDTO.class);
    }
}
