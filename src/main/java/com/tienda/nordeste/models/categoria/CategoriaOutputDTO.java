package com.tienda.nordeste.models.categoria;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoriaOutputDTO {
    private String id;
    private String nombre;

    public CategoriaOutputDTO(Categoria categoria) {
        this.setId(categoria.getId());
        this.setNombre(categoria.getNombre());
    }
}
