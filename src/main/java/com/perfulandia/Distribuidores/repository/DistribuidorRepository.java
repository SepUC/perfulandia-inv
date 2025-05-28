package com.perfulandia.Distribuidores.repository;


import  com.perfulandia.Distribuidores.Model.Distribuidor;
import  org.springframework.data.jpa.repository.JpaRepository;
import  org.springframework.data.jpa.repository.Query;
import  org.springframework.data.repository.query.Param;
import  org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface  DistribuidorRepository extends   JpaRepository<Distribuidor, Long>{

    @Query(value = "SELECT * FROM Distribuidor WHERE rut = :rut", nativeQuery = true)
    Distribuidor buscarPorRut(@Param("rut") int rut);

    @Query(value = "SELECT * FROM Item WHERE nombre = :nombre", nativeQuery = true)
    Distribuidor buscarPorNombre(@Param("nombre") String nombre);


}
