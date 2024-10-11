package com.patrones.patrones.service;

import com.patrones.patrones.model.OrdenCompra;

public class EstadoFilterStrategy implements OrdenCompraFilterStrategy {

    private String estado;

    public EstadoFilterStrategy(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean filter(OrdenCompra ordenCompra) {
        return ordenCompra.getEstado().equalsIgnoreCase(estado);
    }
}