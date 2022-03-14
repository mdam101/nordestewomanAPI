package com.tienda.nordeste.models.categoria;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoriaDTO {
    private String id;
    private String nombre;

    public Categoria getCategoria(CategoriaDTO categoriaInput, Categoria categoria) {
        if(categoriaInput.getNombre()!=null) {
            categoria.setNombre(categoriaInput.getNombre());
        }
        return categoria;
    }
}
