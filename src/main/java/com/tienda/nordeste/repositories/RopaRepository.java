package com.tienda.nordeste.repositories;

import com.tienda.nordeste.models.ropa.Ropa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RopaRepository extends JpaRepository<Ropa, String> {
    public Optional<Ropa> findById(String id);
}