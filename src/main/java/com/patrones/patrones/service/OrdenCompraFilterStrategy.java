package com.patrones.patrones.service;

import com.patrones.patrones.model.OrdenCompra;

public interface OrdenCompraFilterStrategy {
    boolean filter(OrdenCompra ordenCompra);
}