package com.tienda.nordeste.models.lineaPedido;

import com.tienda.nordeste.models.ropa.Ropa;
import com.tienda.nordeste.models.ropa.RopaOutputDTO;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LineaPedidoOutputDTO {
    private Integer numero_linea;
    private Integer cantidad;
    private Double precio_unidad;
    private RopaOutputDTO ropa;

    public LineaPedidoOutputDTO(LineaPedido lineaPedido) {
        this.setNumero_linea(lineaPedido.getNumero_linea());
        this.setCantidad(lineaPedido.getCantidad());
        this.setPrecio_unidad(lineaPedido.getPrecio_unidad());
        this.setRopa(new RopaOutputDTO(lineaPedido.getRopa()));
    }
}
