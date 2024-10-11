package com.patrones.patrones.service;
import com.patrones.patrones.model.Producto;
import java.util.List;

public interface IProductoService {
    Producto guardarProducto(Producto producto);
    List<Producto> obtenerProductos();
    Producto actualizarProducto(Long id, Producto productoActualizado);
    Producto obtenerProductoPorId(Long id);
    void eliminarProducto(Long id);
   // List<Producto> listarProductos();
}