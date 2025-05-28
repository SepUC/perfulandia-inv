package com.perfulandia.clientes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cliente")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Cliente {

    @Id
    @Column(unique = true, length = 9, nullable = false)
    private int run;

    @Column(length = 1, nullable = false)
    private String dv_run;

    @Column(length = 50, nullable = false)
    private String nombre;

    @Column(length  = 60,nullable = false)
    private String apellido;

    @Column(length = 50, nullable = false)
    private String correo;
}
