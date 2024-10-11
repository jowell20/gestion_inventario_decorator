package com.patrones.patrones.controller;

import com.patrones.patrones.model.Producto;
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

    @Autowired
    private ProductoService productoService;


    @PostMapping("/crear")
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto nuevoProducto) {
        try {
            Producto productoGuardado = productoService.guardarProducto(nuevoProducto);
            return ResponseEntity.ok(productoGuardado);
        } catch (IllegalArgumentException e) {
            // Manejo de errores específico (puedes ajustar según tus necesidades)
            return ResponseEntity.badRequest().body(null); // 400 Bad Request
        } catch (Exception e) {
            // Manejo de errores genérico
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // 500 Internal Server Error
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto productoActualizado) {
        try {
            Producto producto = productoService.actualizarProducto(id, productoActualizado);
            return ResponseEntity.ok(producto);
        } catch (EntityNotFoundException e) {
            // Si el producto no se encuentra, responde con 404 (Not Found)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Para cualquier otro error, responde con 500 (Internal Server Error)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Producto>> listarTodosLosProductos() {
        List<Producto> productos = productoService.listarProductos();
        if (productos.isEmpty()) {
            // Si no hay productos, retorna 204 No Content
            return ResponseEntity.noContent().build();
        } else {
            // Si hay productos, retorna 200 OK con la lista de productos
            return ResponseEntity.ok(productos);
        }
    }

    // Método para obtener un producto por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id) {
        try {
            Producto producto = productoService.obtenerProductoPorId(id);
            return ResponseEntity.ok(producto); // Retorna 200 OK con el producto encontrado
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Retorna 404 si no se encuentra el producto
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Manejo de otras excepciones
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        try {
            productoService.eliminarProducto(id);
            return ResponseEntity.noContent().build(); // Retorna 204 si se eliminó correctamente
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Retorna 404 si el producto no se encuentra
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Manejo de otras excepciones
        }
    }
}
