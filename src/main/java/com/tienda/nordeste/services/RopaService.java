package com.tienda.nordeste.services;

import com.tienda.nordeste.models.categoria.Categoria;
import com.tienda.nordeste.models.ropa.Ropa;
import com.tienda.nordeste.repositories.RopaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RopaService extends BaseService<Ropa, String, RopaRepository> {
    @Autowired
    private RopaRepository ropaRepository;

    public Optional<Ropa> findById(String id){
        return ropaRepository.findById(id);
    }

    public List<Ropa> findByCategoriaId(String idCategoria) {
        return ropaRepository.findByCategoriaId(idCategoria);
    }
}
