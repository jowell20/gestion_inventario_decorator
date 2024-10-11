package com.patrones.patrones.controller;

import com.patrones.patrones.model.Producto;
import com.patrones.patrones.service.IProductoService;
import com.patrones.patrones.service.ProductoConNotificacionDecorator;
import com.patrones.patrones.service.ProductoConValidacionPrecioDecorator;
import com.patrones.patrones.service.ProductoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {


   private final IProductoService productoServiceDecorado;

    @Autowired
    private IProductoService productoService;

    // Al inicializar el servicio, lo envuelves con el decorador de validación
    @Autowired
    public ProductoController(IProductoService productoService) {
        this.productoServiceDecorado = new ProductoConNotificacionDecorator(
                new ProductoConValidacionPrecioDecorator(productoService)
        );
    }

    @PostMapping("/crear")
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto nuevoProducto) {
        try {
            Producto productoGuardado = productoServiceDecorado.guardarProducto(nuevoProducto);
            return ResponseEntity.ok(productoGuardado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // 400 Bad Request
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // 500 Internal Server Error
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto productoActualizado) {
        try {
            Producto producto = productoServiceDecorado.actualizarProducto(id, productoActualizado);
            return ResponseEntity.ok(producto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Internal Server Error
        }
    }

    @GetMapping
    public ResponseEntity<List<Producto>> listarTodosLosProductos() {
        List<Producto> productos = productoServiceDecorado.obtenerProductos();
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.ok(productos); // 200 OK
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id) {
        try {
            Producto producto = productoServiceDecorado.obtenerProductoPorId(id);
            return ResponseEntity.ok(producto); // 200 OK
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404 Not Found
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // 500 Internal Server Error
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        try {
            productoServiceDecorado.eliminarProducto(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Internal Server Error
        }
    }
}
