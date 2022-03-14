package com.tienda.nordeste.models.categoria;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoriaInputDTO {
    private String nombre;

    public Categoria getCategoria(CategoriaInputDTO categoriaInput, Categoria categoria) {
        if(categoriaInput.getNombre()!=null) {
            categoria.setNombre(categoriaInput.getNombre());
        }
        return categoria;
    }
}
