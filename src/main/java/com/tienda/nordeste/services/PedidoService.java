package com.tienda.nordeste.services;

import com.tienda.nordeste.models.pedido.Pedido;
import com.tienda.nordeste.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService extends BaseService<Pedido, String, PedidoRepository> {
    @Autowired
    private PedidoRepository pedidoRepository;

    public Optional<Pedido> findById(String id){
        return pedidoRepository.findById(id);
    }
}
