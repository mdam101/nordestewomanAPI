package com.tienda.nordeste.models.ropa;

import com.tienda.nordeste.models.categoria.Categoria;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RopaInputDTO {
    private String nombre;
    private String descripcion;
    private Double precio;
    private String nombreCategoria;


    public Ropa getRopa(RopaInputDTO ropaInput, Ropa ropa) {
        if(ropaInput.getNombre()!=null) {
            ropa.setNombre(ropaInput.getNombre());
        }
        if(ropaInput.getDescripcion()!=null) {
            ropa.setDescripcion(ropaInput.getDescripcion());
        }
        if(ropaInput.getPrecio()!=null) {
            ropa.setPrecio(ropaInput.getPrecio());
        }
        return ropa;
    }
}
