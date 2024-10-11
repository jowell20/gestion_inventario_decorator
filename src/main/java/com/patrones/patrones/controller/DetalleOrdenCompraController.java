package com.patrones.patrones.controller;
import com.patrones.patrones.model.DetalleOrdenCompra;
import com.patrones.patrones.service.DetalleOrdenCompraService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles-orden")
public class DetalleOrdenCompraController {

    @Autowired
    private DetalleOrdenCompraService detalleOrdenCompraService;

    @PostMapping("/crear")
    public ResponseEntity<DetalleOrdenCompra> crearDetalleOrden(@RequestBody DetalleOrdenCompra nuevoDetalle) {
        try {
            DetalleOrdenCompra detalleGuardado = detalleOrdenCompraService.guardarDetalleOrden(nuevoDetalle);
            return ResponseEntity.status(HttpStatus.CREATED).body(detalleGuardado);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<DetalleOrdenCompra> actualizarDetalleOrden(@PathVariable Long id, @RequestBody DetalleOrdenCompra detalleActualizado) {
        try {
            DetalleOrdenCompra detalleModificado = detalleOrdenCompraService.actualizarDetalleOrden(id, detalleActualizado);
            return ResponseEntity.ok(detalleModificado);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarDetallesOrdenCompra() {
        List<DetalleOrdenCompra> detalles = detalleOrdenCompraService.listarTodos();

        // Validación: verificar si la lista está vacía
        if (detalles.isEmpty()) {
            return ResponseEntity.status(204).body("No hay detalles de órdenes de compra disponibles."); // 204 No Content
        }

        return ResponseEntity.ok(detalles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleOrdenCompra> buscarPorId(@PathVariable Long id) {
        try {
            DetalleOrdenCompra detalle = detalleOrdenCompraService.buscarPorId(id);
            return ResponseEntity.ok(detalle);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPorId(@PathVariable Long id) {
        try {
            detalleOrdenCompraService.eliminarPorId(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    // Aquí puedes agregar otros métodos como GET, PUT, DELETE si es necesario
}