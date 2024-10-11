package com.patrones.patrones.service;

import com.patrones.patrones.model.OrdenCompra;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// Esta clase es el "Sujeto" del patrón Observer.
// Es responsable de gestionar la lista de observadores y notificarles cuando el estado de una orden de compra cambie.
@Component
public class OrdenCompraSubject {

    // Lista de observadores que serán notificados cuando el estado cambie.
    private List<OrdenCompraObserver> observadores = new ArrayList<>();

    // Método para agregar un observador.
    public void agregarObservador(OrdenCompraObserver observador) {
        observadores.add(observador);
    }

    // Método para eliminar un observador.
    public void eliminarObservador(OrdenCompraObserver observador) {
        observadores.remove(observador);
    }

    // Método para cambiar el estado de una orden de compra
    public void cambiarEstadoOrden(OrdenCompra ordenCompra, String nuevoEstado) {
        ordenCompra.setEstado(nuevoEstado); // Cambiar el estado
        notificarObservadores(ordenCompra); // Notificar a los observadores
    }

    // Método para notificar a todos los observadores que el estado de una orden de compra ha cambiado.
    public void notificarObservadores(OrdenCompra ordenCompra) {
        for (OrdenCompraObserver observador : observadores) {
            observador.actualizarEstado(ordenCompra);
        }
    }
}
