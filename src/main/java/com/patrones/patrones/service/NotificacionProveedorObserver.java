package com.patrones.patrones.service;

// Ubicación: src/main/java/com/patrones/patrones/service/NotificacionProveedorObserver.java

import com.patrones.patrones.model.OrdenCompra;
import com.patrones.patrones.model.Proveedor;
import org.springframework.stereotype.Service;

// Esta clase es un observador que implementa la interfaz OrdenCompraObserver
// y se encarga de notificar a los proveedores cuando el estado de la orden cambia.
@Service
public class NotificacionProveedorObserver implements OrdenCompraObserver {

    @Override
    public void actualizarEstado(OrdenCompra ordenCompra) {
        // Aquí puedes implementar la lógica de notificación.
        // Por ejemplo, podrías enviar un correo electrónico al proveedor.

        Proveedor proveedor = ordenCompra.getProveedor();
        String estado = ordenCompra.getEstado();

        // Simulación de una notificación
        System.out.println("Notificación al proveedor " + proveedor.getNombre() +
                ": La orden de compra " + ordenCompra.getId() +
                " ha cambiado de estado a " + estado);
    }
}