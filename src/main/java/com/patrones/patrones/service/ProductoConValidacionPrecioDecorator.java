package com.patrones.patrones.service;

import com.patrones.patrones.model.Producto;

import java.util.List;

public class ProductoConValidacionPrecioDecorator extends ProductoDecorator {

    public ProductoConValidacionPrecioDecorator(IProductoService productoService) {
        super(productoService);
    }

    @Override
    public Producto guardarProducto(Producto producto) {
        validarPrecio(producto);
        return super.guardarProducto(producto);
    }

    @Override
    public Producto actualizarProducto(Long id, Producto productoActualizado) {
        validarPrecio(productoActualizado);
        return super.actualizarProducto(id, productoActualizado);
    }

    @Override
    public Producto obtenerProductoPorId(Long id) {
        return super.obtenerProductoPorId(id);
    }

    @Override
    public void eliminarProducto(Long id) {
        super.eliminarProducto(id);
    }

    @Override
    public List<Producto> obtenerProductos() {
        return super.obtenerProductos();
    }

    private void validarPrecio(Producto producto) {
        if (producto.getPrecio() <= 0) {
            System.out.println("El precio debe ser un valor positivo."); // Imprime el mensaje de validación
            throw new IllegalArgumentException("El precio debe ser un valor positivo.");
        }
    }
}
