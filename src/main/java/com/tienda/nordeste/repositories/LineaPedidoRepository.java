package com.tienda.nordeste.repositories;

import com.tienda.nordeste.models.lineaPedido.LineaPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LineaPedidoRepository extends JpaRepository<LineaPedido, String> {
    public Optional<LineaPedido> findById(String id);
}
