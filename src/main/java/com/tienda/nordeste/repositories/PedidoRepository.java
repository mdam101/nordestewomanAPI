package com.tienda.nordeste.repositories;

import com.tienda.nordeste.models.pedido.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, String> {
    public Optional<Pedido> findById(String id);
}
