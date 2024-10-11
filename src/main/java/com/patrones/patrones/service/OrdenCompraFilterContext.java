package com.patrones.patrones.service;

import com.patrones.patrones.model.OrdenCompra;

import java.util.List;

public class OrdenCompraFilterContext {
    private OrdenCompraFilterStrategy strategy;

    public void setStrategy(OrdenCompraFilterStrategy strategy) {
        this.strategy = strategy;
    }

    public List<OrdenCompra> filtrar(List<OrdenCompra> ordenes) {
        return ordenes.stream()
                .filter(strategy::filter)
                .toList();
    }
}