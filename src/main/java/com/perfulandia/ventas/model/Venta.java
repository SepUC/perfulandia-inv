package com.perfulandia.ventas.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Table(name= "ventas")
@NoArgsConstructor
@AllArgsConstructor
@Data


public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_venta;

    @Column(nullable = false)
    private Date fecha_venta;

    @Column(nullable = false) // name = "total_venta"  ?
    private int total_venta;

    @Column(nullable = false,length = 13)
    private String run_cli;

    @Column(nullable = false)
    private Integer id_dist; // Distribuidor asociado a la venta 



}
