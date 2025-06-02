package com.perfulandia.inventory.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "distribuidor")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Distribuidor {

    @Id

    @Column(unique = true,length = 10, nullable = false)
    private int rut;

    @Column(length = 1, nullable = false)
    private  String dvrut;

    @Column(unique=true,length = 50, nullable = false)
    private String nombre;

    @Column (nullable = false)
    private String direccion;


}
