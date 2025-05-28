package com.perfulandia.ventas.repository;

import java.util.List;

import com.perfulandia.ventas.model.Venta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface VentasRepository extends org.springframework.data.jpa.repository.JpaRepository<Venta, Long> {

    @Query("SELECT v FROM Venta v WHERE v.run_cli = ?1")
    List<Venta> buscarVentasPorRunCliente(@Param("run_cli") String run_cli); //JPQL

    @Query(value = "SELECT * FROM venta WHERE id_dist = :id_dist", nativeQuery = true) //SQL Nativo
    List<Venta> buscarVentasPorIdDistribuidor(@Param("id_dist") Integer id_dist);

    @Query("SELECT v FROM Venta v WHERE v.id_venta = :id_venta")
    List<Venta> buscarVentaPorId(@Param("id_venta") Integer id_venta);

}
