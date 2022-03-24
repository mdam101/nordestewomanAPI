package com.tienda.nordeste.models.lineaPedido;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LineaPedidoInputDTO {
    private Integer numero_linea;
    private Integer cantidad;
    private Double precio_unidad;
    private String id_ropa;

    public LineaPedido getLineaPedido(LineaPedidoInputDTO lineaPedidoInput, LineaPedido lineaPedido) {
        if(lineaPedidoInput.getNumero_linea() != null) {
            lineaPedido.setNumero_linea(lineaPedidoInput.getNumero_linea());
        }
        if(lineaPedidoInput.getCantidad() != null) {
            lineaPedido.setCantidad(lineaPedidoInput.getCantidad());
        }
        if(lineaPedidoInput.getPrecio_unidad() != null) {
            lineaPedido.setPrecio_unidad(lineaPedidoInput.getPrecio_unidad());
        }

        return lineaPedido;
    }
}
