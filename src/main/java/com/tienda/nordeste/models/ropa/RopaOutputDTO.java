package com.tienda.nordeste.models.ropa;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RopaOutputDTO {
    private String id;
    private String nombre;
    private String descripcion;
    private Double precio;
    public String nombreCategoria;
    public String urlImagen;

    public RopaOutputDTO(Ropa ropa) {
        this.setId(ropa.getId());
        this.setNombre(ropa.getNombre());
        this.setDescripcion(ropa.getDescripcion());
        this.setPrecio(ropa.getPrecio());
        this.setNombreCategoria(ropa.getCategoria().getNombre());
        this.setUrlImagen("https://nordestewoman.herokuapp.com/ropa/imagen/" + ropa.getId());
    }
}
