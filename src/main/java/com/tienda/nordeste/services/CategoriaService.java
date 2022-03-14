package com.tienda.nordeste.services;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.tienda.nordeste.models.categoria.Categoria;
import com.tienda.nordeste.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService extends BaseService<Categoria, String, CategoriaRepository> {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public Optional<Categoria> findById(String id){
        return categoriaRepository.findById(id);
    }
}
