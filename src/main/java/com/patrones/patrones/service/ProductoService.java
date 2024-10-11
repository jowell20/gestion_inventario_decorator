package com.patrones.patrones.service;

import com.patrones.patrones.model.Categoria;
import com.patrones.patrones.model.Producto;


import com.patrones.patrones.model.Proveedor;
import com.patrones.patrones.repository.CategoriaRepository;
import com.patrones.patrones.repository.ProductoRepository;
import com.patrones.patrones.repository.ProveedorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
   @Autowired
    private ProductoRepository productoRepository;
   @Autowired
    private ProveedorRepository proveedorRepository;
   @Autowired
    private CategoriaRepository categoriaRepository;

    public Producto guardarProducto(Producto producto) {
        Long idProveedor = producto.getProveedor().getId();
        Long idCategoria = producto.getCategoria().getId();

        Proveedor proveedor = proveedorRepository.findById(idProveedor).orElse(null);
        Categoria categoria = categoriaRepository.findById(idCategoria).orElse(null);

        if (proveedor != null && categoria != null) {
            producto.setProveedor(proveedor);
            producto.setCategoria(categoria);
            return productoRepository.save(producto);
        } else {
            throw new RuntimeException("Proveedor o categoría no encontrados");
        }
    }

    public List<Producto> obtenerProductos() {
        return productoRepository.findAll();
    }

   /* public Producto guardarProducto(Producto producto) {
        // Verifica que los IDs de proveedor y categoría existan
        Long id_proveedor = producto.getProveedor().getId();

        Proveedor proveedor = proveedorRepository.findById(producto).orElse(null);
        Categoria categoria = categoriaRepository.findById(producto).orElse(null);

       if (proveedor != null && categoria != null) {
           producto.setProveedor(proveedor);
            producto.setCategoria(categoria);
            return productoRepository.save(producto);
        } else {
            // Maneja el caso en que no se encuentren los IDs
            return null;
        }
        return productoRepository.save(producto);
    }*/

    public List<Producto> listarProductos() {
        return productoRepository.findAll(); // Método para listar todos los productos.
    }

    public Producto actualizarProducto(Long id, Producto productoActualizado) {
        System.out.println("Nombre del producto actualizado a: " );
        Optional<Producto> optProducto = productoRepository.findById(id);
        if (optProducto.isPresent()) {
            Producto p = optProducto.get();

            // Actualiza el nombre solo si no es null y no está vacío
            if (productoActualizado.getNombre() != null && !productoActualizado.getNombre().isEmpty()) {
                p.setNombre(productoActualizado.getNombre());
            }

            // Actualiza el precio solo si no es null
            if (productoActualizado.getPrecio() != null) {
                p.setPrecio(productoActualizado.getPrecio());
            }

            // Actualiza la cantidad solo si no es null
            if (productoActualizado.getCantidad() > 0) { // Asegúrate de que la cantidad sea positiva
                p.setCantidad(productoActualizado.getCantidad());
            }

            // Actualiza el proveedor solo si no es null
            if (productoActualizado.getProveedor() != null) {
                p.setProveedor(productoActualizado.getProveedor());
            }

            // Actualiza la categoría solo si no es null
            if (productoActualizado.getCategoria() != null) {
                p.setCategoria(productoActualizado.getCategoria());
            }

            // Guarda el producto actualizado
            return productoRepository.save(p);
        } else {
            // Lanzar una excepción si el producto no existe
            throw new EntityNotFoundException("Producto no encontrado con id: " + id);
        }
    }

    public Producto obtenerProductoPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con id: " + id));
    }


    public void eliminarProducto(Long id) {
        Optional<Producto> optProducto = productoRepository.findById(id);

        if (optProducto.isPresent()) {
            productoRepository.delete(optProducto.get());
        } else {
            throw new EntityNotFoundException("Producto no encontrado con id: " + id);
        }
    }
   /* public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }*/

}
