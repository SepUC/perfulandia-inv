package com.perfulandia.inventory.repository;

import  com.perfulandia.inventory.model.Item;
import  org.springframework.data.jpa.repository.JpaRepository;
import  org.springframework.data.jpa.repository.Query;
import  org.springframework.data.repository.query.Param;
import  org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {


    @Query(value = "SELECT * FROM Item WHERE precio = :precio", nativeQuery = true)
    Item buscarPorPrecio(@Param("precio") int precio);

    @Query(value = "SELECT * FROM Item WHERE nombre = :nombre", nativeQuery = true)
    Item buscarPorNombre(@Param("nombre") String nombre);

}
