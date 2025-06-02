package com.perfulandia.inventory.controller;

import com.perfulandia.inventory.model.Distribuidor;
import com.perfulandia.inventory.service.DistribuidorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/distribuidor")

public class DistribuidorController {
    @Autowired
    private DistribuidorService distribuidorService;

    @GetMapping
    public ResponseEntity<List<Distribuidor>> listar() {
        List<Distribuidor> distribuidores = distribuidorService.findAll();
        if (distribuidores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(distribuidores);

    }
    @PostMapping
    public ResponseEntity<Distribuidor>guardar(@RequestBody Distribuidor distribuidor) {
        Distribuidor distribuidorNuevo = distribuidorService.save(distribuidor);
        return ResponseEntity.status(HttpStatus.CREATED).body(distribuidorNuevo);
    }

    @GetMapping("/{rut}")
    public ResponseEntity<Distribuidor> buscar(@PathVariable Integer rut) {
    try{
        Distribuidor distribuidor = distribuidorService.findByRut(rut);
        return ResponseEntity.ok(distribuidor);
        } catch (Exception e){
        return ResponseEntity.notFound().build();}
    }
    @PutMapping("/{rut}")
    public ResponseEntity<Distribuidor> actualizar(@PathVariable Integer rut, @RequestBody Distribuidor distribuidor) {

        try{
            Distribuidor it = distribuidorService.findByRut(rut);
            it.setRut(rut);
            it.setDvrut(distribuidor.getDvrut());
            it.setNombre(distribuidor.getNombre());
            it.setDireccion(distribuidor.getDireccion());

            distribuidorService.save(it);
            return ResponseEntity.ok(distribuidor);

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{rut}")
    public ResponseEntity<?> eliminar(@PathVariable Long rut) {
        try {
            distribuidorService.delete(rut);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
