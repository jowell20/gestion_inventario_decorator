package com.patrones.patrones.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;
@DynamicInsert

@Entity
@Table(name = "proveedor")
@Data

public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "proveedor")
    @JsonBackReference
    @JsonIgnore
    private List<Producto> productosProveedor;

    @OneToMany(mappedBy = "proveedor")
    @JsonBackReference
    @JsonIgnore
    private List<OrdenCompra> ordenesCompra;

}