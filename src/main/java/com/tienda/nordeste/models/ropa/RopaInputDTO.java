package com.tienda.nordeste.models.ropa;

import com.tienda.nordeste.models.categoria.Categoria;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RopaInputDTO {
    private String nombre;
    private String descripcion;
    private String talla;
    private Double precio;
    private String idcategoria;


    public Ropa getRopa(RopaInputDTO ropaInput, Ropa ropa) {
        if(ropaInput.getNombre()!=null) {
            ropa.setNombre(ropaInput.getNombre());
        }
        if(ropaInput.getDescripcion()!=null) {
            ropa.setDescripcion(ropaInput.getDescripcion());
        }
        if(ropaInput.getTalla()!=null) {
            ropa.setTalla(ropaInput.getTalla());
        }
        if(ropaInput.getPrecio()!=null) {
            ropa.setPrecio(ropaInput.getPrecio());
        }
        return ropa;
    }
}
