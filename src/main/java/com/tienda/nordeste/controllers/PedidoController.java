package com.tienda.nordeste.controllers;

import com.tienda.nordeste.models.lineaPedido.LineaPedido;
import com.tienda.nordeste.models.lineaPedido.LineaPedidoInputDTO;
import com.tienda.nordeste.models.pedido.Pedido;
import com.tienda.nordeste.models.pedido.PedidoInputDTO;
import com.tienda.nordeste.models.pedido.PedidoOutputDTO;
import com.tienda.nordeste.models.ropa.Ropa;
import com.tienda.nordeste.services.LineaPedidoService;
import com.tienda.nordeste.services.PedidoService;
import com.tienda.nordeste.services.RopaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.OPTIONS})
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    private RopaService ropaService;

    private LineaPedidoService lineaPedidoService;

    //Ver pedido
    @GetMapping("/pedidos")
    public ResponseEntity<?> getPedidos() {
        List<Pedido> pedidos = pedidoService.findAll();
        List<PedidoOutputDTO> ropaOutput = new ArrayList<PedidoOutputDTO>();
        for(Pedido pedido: pedidos) {
            ropaOutput.add(new PedidoOutputDTO(pedido));
        }
        return ResponseEntity.status(HttpStatus.OK).body(ropaOutput);
    }

    //Ver pedido por id
    @GetMapping("/pedido/{id}")
    public ResponseEntity<?> getPedidoById(@PathVariable String id) {
        try {
            Pedido pedido = pedidoService.findById(id).orElseThrow(() -> new Exception("El pedido que buscas no existe."));
            return ResponseEntity.status(HttpStatus.OK).body(new PedidoOutputDTO(pedido));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    //Añadir pedido
    @PostMapping("/pedido/add")
    public ResponseEntity<?> addPedido(@RequestBody PedidoInputDTO pedidoInput) {
        try {
            Pedido pedido = pedidoInput.pedido();
            pedidoService.save(pedido);
            List<LineaPedidoInputDTO> lineas = pedidoInput.getLineas();
            List<LineaPedido> lineasPedido = new ArrayList<>();
            int numLinea = 1;
            for(LineaPedidoInputDTO linea: lineas) {
                Ropa ropa = ropaService.findById(linea.getId_ropa()).orElseThrow(() -> new Exception("No existe la prenda"));
                LineaPedido lineaPedido = new LineaPedido();
                lineaPedido.setRopa(ropa);
                lineaPedido.setCantidad(linea.getCantidad());
                lineaPedido.setPrecio_unidad(ropa.getPrecio()*linea.getCantidad());
                lineaPedido.setPedido(pedido);
                lineaPedido.setNumero_linea(numLinea++);
                lineaPedidoService.save(lineaPedido);
            }
            pedido.setLineapedido(lineasPedido);
            pedidoService.edit(pedido);
            return ResponseEntity.status(HttpStatus.OK).body(new PedidoOutputDTO(pedido));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    //Editar pedido
    @PutMapping("/pedido/edit/{id}")
    public ResponseEntity<?> editPedido() {
        return null;
        //Por hacer
    }

    //Borrar pedido
    @DeleteMapping("/pedido/delete/{id}")
    public ResponseEntity<?> deletePedido(@PathVariable String id) {
        try {
            Pedido pedido = pedidoService.findById(id).orElseThrow(() -> new Exception("No se ha podido borrar el pedido."));
            pedidoService.delete(pedido);
            return ResponseEntity.status(HttpStatus.OK).body("El pedido se ha borrado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
