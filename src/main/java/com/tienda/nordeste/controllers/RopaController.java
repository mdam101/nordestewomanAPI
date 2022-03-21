package com.tienda.nordeste.controllers;

import com.tienda.nordeste.models.categoria.Categoria;
import com.tienda.nordeste.models.ropa.Ropa;
import com.tienda.nordeste.models.ropa.RopaInputDTO;
import com.tienda.nordeste.models.ropa.RopaOutputDTO;
import com.tienda.nordeste.services.CategoriaService;
import com.tienda.nordeste.services.RopaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.OPTIONS})
public class RopaController {
    @Autowired
    private RopaService ropaService;

    @Autowired
    private CategoriaService categoriaService;

    //Ver ropa
    @GetMapping("/ropas")
    public ResponseEntity<?> getRopas() {
        List<Ropa> ropas = ropaService.findAll();
        List<RopaOutputDTO> ropaOutput = new ArrayList<RopaOutputDTO>();
        for(Ropa ropa: ropas) {
            ropaOutput.add(new RopaOutputDTO(ropa));
        }
        return ResponseEntity.status(HttpStatus.OK).body(ropaOutput);
    }

    //Ver ropa por id
    @GetMapping("/ropa/{idRopa}")
    public ResponseEntity<?> getRopaById(@PathVariable String idRopa) {
        try {
            Ropa ropa = ropaService.findById(idRopa).orElseThrow(() -> new Exception("La prenda que buscas no existe."));
            return ResponseEntity.status(HttpStatus.OK).body(new RopaOutputDTO(ropa));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    //Ver imagen ropa
    @GetMapping(value = "/ropa/imagen/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?> getImagen(@PathVariable String id) {
        //buscar para hacerlo
        return null;
    }

    //Ver ropa por nombre de categoría
    @GetMapping("/ropa/categoria/{nombreCategoria}")
    public ResponseEntity<?> getRopaByNombreCategoria(@PathVariable String nombreCategoria) {
        List<Ropa> ropas = ropaService.findByCategoriaNombre(nombreCategoria);
        if(ropas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado nada en esta categoría o no existe");
        } else {
            List<RopaOutputDTO> ropaOutput = new ArrayList<RopaOutputDTO>();
            for(Ropa ropa: ropas) {
                ropaOutput.add(new RopaOutputDTO(ropa));
            }
            return ResponseEntity.status(HttpStatus.OK).body(ropaOutput);
        }
    }

    //Añadir ropa
    @PostMapping("/ropa/add")
    public ResponseEntity<?> addRopa(@RequestParam String nombre, @RequestParam String descripcion, @RequestParam String talla, @RequestParam Double precio, @RequestParam String nombreCategoria, @RequestPart("file")MultipartFile file) {
        try {
            Categoria categoria = categoriaService.findByNombre(nombreCategoria).orElseThrow(() -> new Exception("No se puede añadir ropa a una categoría que no existe."));
            Ropa ropa = ropaService.crearRopa(nombre, descripcion, talla, precio, categoria, file);
            ropaService.save(ropa);
            return ResponseEntity.status(HttpStatus.OK).body(new RopaOutputDTO(ropa));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    //Editar ropa
    @PutMapping("/ropa/edit/{id}")
    public ResponseEntity<?> editRopa(@PathVariable String id, @RequestBody RopaInputDTO ropaInput) {
        try {
            Ropa ropa = ropaService.findById(id).orElseThrow(() -> new Exception("No existe esta prenda"));
            Categoria categoria = categoriaService.findByNombre(ropaInput.getNombreCategoria()).orElseThrow(() -> new Exception("No existe esta categoría"));
            Ropa ropaEditada = ropaInput.getRopa(ropaInput, ropa);
            ropaEditada.setCategoria(categoria);
            ropaService.edit(ropaEditada);
            return ResponseEntity.status(HttpStatus.OK).body(new RopaOutputDTO(ropaEditada));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    //Editar imagen ropa
    @PutMapping("/ropa/editImage/{id}")
    public ResponseEntity<?> editImageRopa(@PathVariable String id, @RequestPart MultipartFile file) {
        try {
            Ropa ropa = ropaService.findById(id).orElseThrow(() -> new Exception("No se ha encontrado la prenda"));
            ropa.setImagen(file.getBytes());
            ropaService.save(ropa);
            return ResponseEntity.status(HttpStatus.OK).body(new RopaOutputDTO(ropa));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    //Borrar ropa
    @DeleteMapping("/ropa/delete/{id}")
    public ResponseEntity<?> deleteRopa(@PathVariable String id) {
        try {
            Ropa ropa = ropaService.findById(id).orElseThrow(() -> new Exception("No se ha podido borrar la prenda."));
            ropaService.delete(ropa);
            return ResponseEntity.status(HttpStatus.OK).body("La prenda se ha borrado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}