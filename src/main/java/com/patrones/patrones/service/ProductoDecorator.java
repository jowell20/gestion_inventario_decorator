package com.patrones.patrones.service;
import com.patrones.patrones.model.Producto;

import java.util.List;


public abstract class ProductoDecorator implements IProductoService {
    protected IProductoService productoService; // Referencia al servicio original

    public ProductoDecorator(IProductoService productoService) {
        this.productoService = productoService;
    }

    @Override
    public Producto guardarProducto(Producto producto) {
        return productoService.guardarProducto(producto); // Delegar la llamada al servicio original
    }

    @Override
    public Producto actualizarProducto(Long id, Producto productoActualizado) {
        return productoService.actualizarProducto(id, productoActualizado);
    }

    @Override
    public List<Producto> obtenerProductos() {
        return productoService.obtenerProductos(); // Delegar la llamada al servicio original
    }

    @Override
    public Producto obtenerProductoPorId(Long id) {
        return productoService.obtenerProductoPorId(id); // Implementación delegada
    }
    @Override

    public void eliminarProducto(Long id) {
        productoService.eliminarProducto(id); // Implementación delegada
    }
    // Implementa otros métodos de la interfaz según sea necesario
}