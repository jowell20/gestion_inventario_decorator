package com.patrones.patrones.controller;

import com.patrones.patrones.model.OrdenCompra;
import com.patrones.patrones.service.OrdenService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordenes")

public class OrdenController {
    @Autowired
    private  OrdenService ordenService;

    @GetMapping
    public List<OrdenCompra> Listar() {
        return ordenService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenCompra> obtenerOrdenPorId(@PathVariable Long id) {
        try {
            OrdenCompra ordenCompra = ordenService.obtenerOrdenPorId(id);
            return ResponseEntity.ok(ordenCompra); // Retorna 200 OK con la orden de compra en el cuerpo
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Retorna 404 si la orden no se encuentra
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Manejo de otras excepciones
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<OrdenCompra> saveOrden(@RequestBody OrdenCompra ordenCompra) {
        OrdenCompra nuevaOrden = ordenService.saveOrden(ordenCompra);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaOrden);
    }




    @PutMapping("/{id}")
    public ResponseEntity<OrdenCompra> updateOrden(@PathVariable Long id, @RequestBody OrdenCompra ordenCompra) {
        try {
            // Llamada al servicio para actualizar la orden
            OrdenCompra ordenActualizada = ordenService.updateOrden(id, ordenCompra);
            return ResponseEntity.ok(ordenActualizada); // Devuelve la orden actualizada con un código de estado 200
        } catch (EntityNotFoundException e) {
            // Manejo de la excepción si no se encuentra la orden de compra
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Devuelve un código 404
        } catch (Exception e) {
            // Manejo de cualquier otra excepción
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Devuelve un código 500
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrden(@PathVariable Long id) {
        try {
            ordenService.deleteOrden(id);
            return ResponseEntity.noContent().build(); // Retorna 204 No Content si se elimina correctamente
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Retorna 404 Not Found si no se encuentra la orden de compra
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Manejo de otras excepciones
        }
    }

    @GetMapping("/Estado/{estado}")
    public List<OrdenCompra> filtrarOrdenesPorEstado(@PathVariable String estado) {
        return ordenService.filtrarOrdenesPorEstado(estado);
    }
}