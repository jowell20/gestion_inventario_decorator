package com.patrones.patrones.service;

import com.patrones.patrones.model.OrdenCompra;

public interface OrdenCompraObserver {

    // Este método será llamado cuando el estado de una orden de compra cambie.
    void actualizarEstado(OrdenCompra ordenCompra);
}