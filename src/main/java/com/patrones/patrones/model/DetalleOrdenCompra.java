package com.patrones.patrones.model;



import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ordenes_detalles")
public class DetalleOrdenCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_orden_compra")
    private OrdenCompra ordenCompra; // Relación con la entidad OrdenCompra

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_producto")
    private Producto producto; // Relación con la entidad Producto

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    @Column(name = "precio_unitario", nullable = false)
    private double precioUnitario;



    // Aquí puedes agregar métodos adicionales, si es necesario
}