package com.patrones.patrones.service;

import com.patrones.patrones.model.OrdenCompra;
import org.springframework.stereotype.Component;

@Component
public class ProveedorObserver implements OrdenCompraObserver {
    @Override
    public void actualizarEstado(OrdenCompra ordenCompra) {
        // Aquí puedes implementar la lógica que deseas cuando el estado cambie
        System.out.println("Notificación a proveedor: La orden de compra con ID " + ordenCompra.getId() +
                " ha cambiado de estado a " + ordenCompra.getEstado());
    }
}