package com.tienda.nordeste.controllers;

import com.tienda.nordeste.models.categoria.Categoria;
import com.tienda.nordeste.models.categoria.CategoriaDTO;
import com.tienda.nordeste.models.categoria.CategoriaOutputDTO;
import com.tienda.nordeste.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.OPTIONS})
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    //Ver categorías
    @GetMapping("/categorias")
    public ResponseEntity<?> getCategorias() {
        List<Categoria> listCategorias = new ArrayList<Categoria>();
        List<CategoriaOutputDTO> categoriaOutput = new ArrayList<CategoriaOutputDTO>();
        return ResponseEntity.status(HttpStatus.OK).body(categoriaOutput);
    }

    //Ver categoría por id
    @GetMapping("/categoria/detail/{idCategoria}")
    public ResponseEntity<?> getCategoriaById(@PathVariable String idCategoria) {
        Optional<Categoria> categoriaOptional = categoriaService.findById(idCategoria);
        if(categoriaOptional.isPresent()) {
            Categoria categoria = categoriaOptional.get();
            return ResponseEntity.status(HttpStatus.OK).body(new CategoriaOutputDTO(categoria));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //Añadir categoría
    @GetMapping("/categoria/add")
    public ResponseEntity<?> addCategoria(@RequestBody CategoriaDTO categoriaInput) {
        Categoria categoria = categoriaInput.getCategoria(categoriaInput, new Categoria());
        categoriaService.save(categoria);
        return ResponseEntity.status(HttpStatus.OK).body(new CategoriaOutputDTO(categoria));
    }

    //Borrar categoría
    @GetMapping("/categoria/delete/{id}")
    public ResponseEntity<?> deleteCategoria(@PathVariable String id) {
        Optional<Categoria> categoriaOptional = categoriaService.findById(id);
        if(categoriaOptional.isPresent()) {
            categoriaService.delete(categoriaOptional.get());
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
