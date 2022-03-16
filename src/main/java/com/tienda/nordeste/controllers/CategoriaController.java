package com.tienda.nordeste.controllers;

import com.tienda.nordeste.models.categoria.Categoria;
import com.tienda.nordeste.models.categoria.CategoriaInputDTO;
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
        List<Categoria> categorias = categoriaService.findAll();
        List<CategoriaOutputDTO> categoriaOutput = new ArrayList<CategoriaOutputDTO>();
        for(Categoria categoria: categorias) {
            categoriaOutput.add(new CategoriaOutputDTO(categoria));
        }
        return ResponseEntity.status(HttpStatus.OK).body(categoriaOutput);
    }

    //Ver categoría por id
    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<?> getCategoriaById(@PathVariable String idCategoria) {
        try {
            Categoria categoria = categoriaService.findById(idCategoria).orElseThrow(() -> new Exception("La categoría que buscas no existe."));
            return ResponseEntity.status(HttpStatus.OK).body(new CategoriaOutputDTO(categoria));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    //Añadir categoría
    @PostMapping("/categoria/add")
    public ResponseEntity<?> addCategoria(@RequestBody CategoriaInputDTO categoriaInput) {
        Categoria categoria = categoriaInput.getCategoria(categoriaInput, new Categoria());
        Optional<Categoria> categoriaOptional = categoriaService.findByNombre(categoriaInput.getNombre());
        if(categoriaOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Esta categoría ya existe");
        } else {
            categoriaService.save(categoria);
            return ResponseEntity.status(HttpStatus.OK).body(new CategoriaOutputDTO(categoria));
        }
    }

    //Borrar categoría
    @DeleteMapping("/categoria/delete/{id}")
    public ResponseEntity<?> deleteCategoria(@PathVariable String id) {
        try {
            Categoria categoria = categoriaService.findById(id).orElseThrow(() -> new  Exception("Esta categoría no existe, no se puede borrar"));
            categoriaService.delete(categoria);
            return ResponseEntity.status(HttpStatus.OK).body("La categoría se ha borrado con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
