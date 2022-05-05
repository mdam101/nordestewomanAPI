package com.tienda.nordeste.models.pedido;

import com.tienda.nordeste.models.lineaPedido.LineaPedidoInputDTO;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PedidoInputDTO {
    private Double precio;
    private String metodo_pago;
    private List<LineaPedidoInputDTO> lineas;

    public Pedido pedido() throws Exception {
        Pedido pedido = new Pedido();
        Date fechaActual = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaActual);
        pedido.setFecha_pedido(fechaActual);
        pedido.setPrecio_pedido(this.getPrecio());
        pedido.setMetodo_pago(this.getMetodo_pago());
        pedido.setEntregado(false);
        return pedido;
    }
}
