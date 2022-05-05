package com.tienda.nordeste.models.pedido;

import com.tienda.nordeste.models.lineaPedido.LineaPedido;
import com.tienda.nordeste.models.lineaPedido.LineaPedidoOutputDTO;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PedidoOutputDTO {
    private String id;
    private Date fecha_pedido;
    private Double precio_pedido;
    private String metodo_pago;
    private Boolean entregado;
    private List<LineaPedidoOutputDTO> lineas;

    public PedidoOutputDTO(Pedido pedido) {
        this.setId(pedido.getId());
        this.setFecha_pedido(pedido.getFecha_pedido());
        this.setPrecio_pedido(pedido.getPrecio_pedido());
        this.setMetodo_pago(pedido.getMetodo_pago());
        this.setEntregado(pedido.getEntregado());
        List<LineaPedidoOutputDTO> lineas = new ArrayList<>();
        for(LineaPedido l: pedido.getLineaspedido()) {
            lineas.add(new LineaPedidoOutputDTO(l));
        }
        this.setLineas(lineas);
    }
}
