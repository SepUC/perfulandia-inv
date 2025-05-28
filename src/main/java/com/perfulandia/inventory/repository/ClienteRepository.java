package com.perfulandia.inventory.repository;

import com.perfulandia.inventory.model.Cliente;
import  org.springframework.data.jpa.repository.JpaRepository;
import  org.springframework.data.jpa.repository.Query;
import  org.springframework.data.repository.query.Param;
import  org.springframework.stereotype.Repository;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query(value = "SELECT * FROM Cliente WHERE run = :run", nativeQuery = true)
    Cliente buscarPorPrecio(@Param("run") int precio);

    @Query(value = "SELECT * FROM Cliente WHERE dv_run = :dv_run", nativeQuery = true)
    Cliente buscarPorNombre(@Param("dv_run") String dv_run);

}
