package com.tienda.nordeste.repositories;

import com.tienda.nordeste.models.categoria.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, String> {
    public Optional<Categoria> findById(String id);
}
