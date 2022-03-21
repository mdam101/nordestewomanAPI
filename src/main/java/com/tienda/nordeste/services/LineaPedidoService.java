package com.tienda.nordeste.services;

import com.tienda.nordeste.models.lineaPedido.LineaPedido;
import com.tienda.nordeste.repositories.LineaPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class LineaPedidoService extends BaseService<LineaPedido, String, LineaPedidoRepository>  {
    @Autowired
    private LineaPedidoRepository lineaPedidoRepository;

    public Optional<LineaPedido> findById(String id){
        return lineaPedidoRepository.findById(id);
    }
}
