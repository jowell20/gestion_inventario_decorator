package com.patrones.patrones.service;
import com.patrones.patrones.model.Producto;

import java.util.List;

public class ProductoConNotificacionDecorator extends ProductoDecorator{



    public ProductoConNotificacionDecorator(IProductoService productoService) {
        super(productoService);
    }

    @Override
    public Producto guardarProducto(Producto producto) {
        Producto productoGuardado = super.guardarProducto(producto);
        enviarNotificacion(productoGuardado,"guardado");
        return productoGuardado;
    }

    @Override
    public Producto actualizarProducto(Long id, Producto productoActualizado) {
        Producto producto = super.actualizarProducto(id, productoActualizado);
        enviarNotificacion(producto, "actualizado");
        return producto;
    }

    @Override
    public Producto obtenerProductoPorId(Long id) {
        Producto producto = super.obtenerProductoPorId(id); // Llama al método del decorador base
        enviarNotificacion(producto, "obtenido"); // Enviar notificación
        return producto;
    }

    @Override
    public void eliminarProducto(Long id) {
        super.eliminarProducto(id); // Llama al método de la clase base para eliminar el producto
        System.out.println("Notificación: El producto con ID " + id + " ha sido eliminado.");
    }

    @Override
    public List<Producto> obtenerProductos() {
        return super.obtenerProductos();
    }

    private void enviarNotificacion(Producto producto, String accion) {
        System.out.println("Notificación: El producto '" + producto.getNombre() + "' ha sido " + accion + ".");
    }

}
