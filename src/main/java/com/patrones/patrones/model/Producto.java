package com.patrones.patrones.model;


import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@DynamicInsert

@Entity
@Table(name = "producto")
@Data
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "precio")
    private Double precio;

    @Column(name = "cantidad")
    private int cantidad;

    @ManyToOne (fetch = FetchType.EAGER )
    @JoinColumn(name= "id_proveedor")
    private Proveedor proveedor;

    @ManyToOne (fetch = FetchType.EAGER )
    @JoinColumn(name= "id_categoria")

    private Categoria categoria;

    @OneToMany(mappedBy = "producto")
    @JsonBackReference
    @JsonIgnore
    private List<DetalleOrdenCompra> detalleOrdenCompras;

}
