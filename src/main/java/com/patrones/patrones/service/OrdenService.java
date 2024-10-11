package com.patrones.patrones.service;

import com.patrones.patrones.model.OrdenCompra;
import com.patrones.patrones.repository.OrdenCompraRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdenService {
    @Autowired
    private OrdenCompraRepository ordenCompraRepository;

    public List<OrdenCompra> listar() {return ordenCompraRepository.findAll();
    }

    public OrdenCompra obtenerOrdenPorId(Long id) {
        return ordenCompraRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Orden de compra no encontrada con id: " + id));
    }

    public OrdenCompra saveOrden(OrdenCompra ordenCompra) {
        return ordenCompraRepository.save(ordenCompra);
    }

    public OrdenCompra updateOrden(Long id, OrdenCompra ordenCompra) {
        OrdenCompra ordenCompraExistente = ordenCompraRepository.findById(id).orElse(null);
        if (ordenCompraExistente != null) {
            // Verificar cada campo individualmente y actualizar solo si no es null
            if (ordenCompra.getFecha() != null) {
                ordenCompraExistente.setFecha(ordenCompra.getFecha());
            }
            if (ordenCompra.getEstado() != null) {
                ordenCompraExistente.setEstado(ordenCompra.getEstado());
            }
            if (ordenCompra.getProveedor() != null) {
                ordenCompraExistente.setProveedor(ordenCompra.getProveedor());
            }

            // Aquí puedes agregar validaciones adicionales para otros campos
            return ordenCompraRepository.save(ordenCompraExistente);
        } else {
            // Manejar el caso en que no se encuentra la orden
            return null;
        }
    }

   /* public OrdenCompra updateOrden(Long id, OrdenCompra ordenCompra) {
        OrdenCompra ordenCompraExistente = ordenCompraRepository.findById(id).orElse(null);
        if (ordenCompraExistente != null) {
            // Actualiza los campos según los atributos de la entidad OrdenCompra
            ordenCompraExistente.setFecha(ordenCompra.getFecha()); // Reemplaza 'setNombre' con los métodos reales de la entidad
            ordenCompraExistente.setEstado(ordenCompra.getEstado()); // Reemplaza 'setDescripcion' con atributos reales
            // Agrega más atributos si es necesario
            return ordenCompraRepository.save(ordenCompraExistente);
        } else {
            return null;
        }
    }*/

    public void deleteOrden(Long id) {
        // Verificar si la orden de compra existe antes de eliminar
        if (!ordenCompraRepository.existsById(id)) {
            throw new EntityNotFoundException("Orden de compra no encontrada con id: " + id);
        }
        // Eliminar la orden de compra si existe
        ordenCompraRepository.deleteById(id);
    }
   /* public void deleteOrden(Long id) {
        ordenCompraRepository.deleteById(id);
    }*/
}