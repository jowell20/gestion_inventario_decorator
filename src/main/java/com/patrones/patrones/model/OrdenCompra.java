package com.patrones.patrones.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;
import java.util.List;
@DynamicInsert

@Entity
@Table(name = "orden_compra")
@Data
public class OrdenCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha" )
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "id_proveedor")
    private Proveedor proveedor;  // Asumiendo que tienes una entidad Proveedor

    @Column(name = "estado", length = 50)
    private String estado;


    @OneToMany(mappedBy = "ordenCompra")
    @JsonBackReference
    @JsonIgnore
    private List<DetalleOrdenCompra> detalleOrdenCompras;

    public void setEstado(String estado) {
        this.estado = estado;
    }
}