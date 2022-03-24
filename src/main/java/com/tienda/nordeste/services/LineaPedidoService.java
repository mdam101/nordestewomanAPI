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

    public LineaPedido crearLineaPedido(Integer numero_linea, Integer cantidad, Double precio_unidad) {
        LineaPedido lineaPedido = new LineaPedido();
        lineaPedido.setNumero_linea(numero_linea);
        lineaPedido.setCantidad(cantidad);
        lineaPedido.setPrecio_unidad(precio_unidad);
        return lineaPedido;
    }
}
