package com.patrones.patrones.service;

import com.patrones.patrones.model.DetalleOrdenCompra;
import com.patrones.patrones.model.OrdenCompra;
import com.patrones.patrones.model.Producto;
import com.patrones.patrones.repository.DetalleOrdenCompraRepository;
import com.patrones.patrones.repository.OrdenCompraRepository;
import com.patrones.patrones.repository.ProductoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleOrdenCompraService {

    @Autowired
    private DetalleOrdenCompraRepository detalleOrdenCompraRepository;

    @Autowired
    private OrdenCompraRepository ordenCompraRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public DetalleOrdenCompra guardarDetalleOrden(DetalleOrdenCompra detalle) {
        // Verifica que la orden de compra exista
        OrdenCompra ordenCompra = ordenCompraRepository.findById(detalle.getOrdenCompra().getId())
                .orElseThrow(() -> new EntityNotFoundException("Orden de compra no encontrada con id: " + detalle.getOrdenCompra().getId()));

        // Verifica que el producto exista
        Producto producto = productoRepository.findById(detalle.getProducto().getId())
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con id: " + detalle.getProducto().getId()));

        // Asigna la orden de compra y el producto al detalle
        detalle.setOrdenCompra(ordenCompra);
        detalle.setProducto(producto);

        // Guarda el detalle de la orden de compra
        return detalleOrdenCompraRepository.save(detalle);
    }

    public List<DetalleOrdenCompra> listarTodos() {
        List<DetalleOrdenCompra> detalles = detalleOrdenCompraRepository.findAll();

        // Validación: podrías agregar más lógica aquí si es necesario.
        return detalles;
    }

    public DetalleOrdenCompra buscarPorId(Long id) {
        return detalleOrdenCompraRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Detalle de orden de compra no encontrado con ID: " + id));
    }

    // Método para eliminar por ID
    public void eliminarPorId(Long id) {
        if (!detalleOrdenCompraRepository.existsById(id)) {
            throw new EntityNotFoundException("Detalle de orden de compra no encontrado con ID: " + id);
        }
        detalleOrdenCompraRepository.deleteById(id);
    }

    public DetalleOrdenCompra actualizarDetalleOrden(Long id, DetalleOrdenCompra detalleActualizado) {
        DetalleOrdenCompra detalleExistente = buscarPorId(id);

        // Actualiza solo los campos que no son nulos y no están vacíos
        if (detalleActualizado.getCantidad() > 0) {
            detalleExistente.setCantidad(detalleActualizado.getCantidad());
        }

        if (detalleActualizado.getPrecioUnitario() > 0) {
            detalleExistente.setPrecioUnitario(detalleActualizado.getPrecioUnitario());
        }

        // Si se proporciona un nuevo producto, se actualiza la relación
        if (detalleActualizado.getProducto() != null && detalleActualizado.getProducto().getId() != null) {
            Producto producto = productoRepository.findById(detalleActualizado.getProducto().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con id: " + detalleActualizado.getProducto().getId()));
            detalleExistente.setProducto(producto);
        }

        // Si se proporciona una nueva orden de compra, se actualiza la relación
        if (detalleActualizado.getOrdenCompra() != null && detalleActualizado.getOrdenCompra().getId() != null) {
            OrdenCompra ordenCompra = ordenCompraRepository.findById(detalleActualizado.getOrdenCompra().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Orden de compra no encontrada con id: " + detalleActualizado.getOrdenCompra().getId()));
            detalleExistente.setOrdenCompra(ordenCompra);
        }

        // Guarda el detalle actualizado
        return detalleOrdenCompraRepository.save(detalleExistente);
    }


}