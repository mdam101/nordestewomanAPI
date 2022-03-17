package com.tienda.nordeste.models.ropa;

import com.tienda.nordeste.models.categoria.Categoria;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RopaOutputDTO {
    private String id;
    private String nombre;
    private String descripcion;
    private String talla;
    private Double precio;
    public String nombreCategoria;

    public RopaOutputDTO(Ropa ropa) {
        this.setId(ropa.getId());
        this.setNombre(ropa.getNombre());
        this.setDescripcion(ropa.getDescripcion());
        this.setTalla(ropa.getTalla());
        this.setPrecio(ropa.getPrecio());
        this.setNombreCategoria(ropa.getCategoria().getNombre());
    }
}
