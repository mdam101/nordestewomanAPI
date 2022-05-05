package com.tienda.nordeste.repositories;

import com.tienda.nordeste.models.talla.Talla;
import com.tienda.nordeste.models.talla.TallaRopa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TallaRopaRepository extends JpaRepository<TallaRopa, String> {
    Optional<TallaRopa> findByRopaIdAndTalla(String idRopa, Talla talla);
}
