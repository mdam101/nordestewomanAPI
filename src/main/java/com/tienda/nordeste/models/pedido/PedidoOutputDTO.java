package com.tienda.nordeste.models.pedido;

import com.tienda.nordeste.models.lineaPedido.LineaPedido;
import com.tienda.nordeste.models.lineaPedido.LineaPedidoOutputDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
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
        for(LineaPedido l: pedido.getLineapedido()) {
            lineas.add(new LineaPedidoOutputDTO(l));
        }
        this.setLineas(lineas);
    }
}