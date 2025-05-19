package com.perfulandia.inventory.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "item")
@Data
@NoArgsConstructor
@AllArgsConstructor





public class Item {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique=true,length = 50, nullable = false)
    private String nombre;

    @Column (nullable = false)
    private int precio;
}
