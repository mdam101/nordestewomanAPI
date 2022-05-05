package com.tienda.nordeste.controllers;

import com.tienda.nordeste.models.ropa.Ropa;
import com.tienda.nordeste.models.talla.Talla;
import com.tienda.nordeste.models.talla.TallaRopa;
import com.tienda.nordeste.services.BaseService;
import com.tienda.nordeste.services.TallaRopaService;
import com.tienda.nordeste.services.RopaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;



@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.OPTIONS})
public class TallaRopaController {
    @Autowired
    private TallaRopaService tallaRopaService;

    @Autowired
    private RopaService ropaService;

    //Buscar por idropa y talla el stock
    @GetMapping("/tallasropa")
    @Transactional(readOnly = true)
    public ResponseEntity<?> getStockByIdRopayTalla(@RequestParam String idRopa, @RequestParam Talla talla) {
        try {
            TallaRopa tallaRopa = tallaRopaService.findByRopaIdAndTalla(idRopa, talla).orElseThrow(() -> new Exception("No se encuentra nada con ese idRopa y talla"));
            Integer stock = tallaRopa.getStock();
            return ResponseEntity.status(HttpStatus.OK).body(stock);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    //Añadir talla y cantidad a tallaropa por idRopa
    @PostMapping("/tallaropa/add")
    public ResponseEntity<?> addTallaRopa(@RequestParam String idRopa, @RequestParam String talla, @RequestParam Integer stock) {
        try {
            Ropa ropa = ropaService.findById(idRopa).orElseThrow(() -> new Exception("No se ha encontrado una prenda con ese id"));
            TallaRopa tallaRopa = tallaRopaService.crearTallaRopa(ropa, Talla.valueOf(talla), stock);
            tallaRopaService.save(tallaRopa);
            return ResponseEntity.status(HttpStatus.OK).body(tallaRopa);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
