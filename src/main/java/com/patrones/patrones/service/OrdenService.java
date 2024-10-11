package com.patrones.patrones.service;

import com.patrones.patrones.model.OrdenCompra;
import com.patrones.patrones.repository.OrdenCompraRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class OrdenService {

    @Autowired
    private OrdenCompraRepository ordenCompraRepository;

    @Autowired
    private OrdenCompraSubject ordenCompraSubject; // Agregar el sujeto para notificar cambios

    @Autowired
    private ProveedorObserver proveedorObserver; // Inyecta el observador

    @PostConstruct // Se ejecuta después de que se inicializa el bean
    public void init() {
        ordenCompraSubject.agregarObservador(proveedorObserver); // Registra el observador
    }

    public List<OrdenCompra> listar() {
        return ordenCompraRepository.findAll();
    }

    public OrdenCompra obtenerOrdenPorId(Long id) {
        return ordenCompraRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Orden de compra no encontrada con id: " + id));
    }

    public OrdenCompra saveOrden(OrdenCompra ordenCompra) {
        OrdenCompra nuevaOrden = ordenCompraRepository.save(ordenCompra);
        // Notificar a los observadores al crear una nueva orden
        ordenCompraSubject.notificarObservadores(nuevaOrden);
        return nuevaOrden;
    }

    public OrdenCompra updateOrden(Long id, OrdenCompra ordenCompra) {
        OrdenCompra ordenCompraExistente = ordenCompraRepository.findById(id).orElse(null);
        if (ordenCompraExistente != null) {
            if (ordenCompra.getFecha() != null) {
                ordenCompraExistente.setFecha(ordenCompra.getFecha());
            }
            if (ordenCompra.getEstado() != null) {
                // Cambiar el estado de la orden
                ordenCompraExistente.setEstado(ordenCompra.getEstado());
                // Notificar a los observadores del cambio de estado
                ordenCompraSubject.cambiarEstadoOrden(ordenCompraExistente, ordenCompra.getEstado());
            }
            if (ordenCompra.getProveedor() != null) {
                ordenCompraExistente.setProveedor(ordenCompra.getProveedor());
            }
            // Guardar la orden de compra actualizada
            return ordenCompraRepository.save(ordenCompraExistente);
        } else {
            throw new EntityNotFoundException("Orden de compra no encontrada con id: " + id);
        }
    }

    public void deleteOrden(Long id) {
        // Verificar si la orden de compra existe antes de eliminar
        if (!ordenCompraRepository.existsById(id)) {
            throw new EntityNotFoundException("Orden de compra no encontrada con id: " + id);
        }
        // Eliminar la orden de compra si existe
        ordenCompraRepository.deleteById(id);
    }

    public List<OrdenCompra> filtrarOrdenesPorEstado(String estado) {
        List<OrdenCompra> todasLasOrdenes = ordenCompraRepository.findAll();
        OrdenCompraFilterContext context = new OrdenCompraFilterContext();
        context.setStrategy(new EstadoFilterStrategy(estado));

        return context.filtrar(todasLasOrdenes);
    }


}