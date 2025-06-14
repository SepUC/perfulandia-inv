package com.perfulandia.inventory.controller;

import com.perfulandia.inventory.model.Distribuidor;
import com.perfulandia.inventory.service.DistribuidorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse; // Import de anotaciones de Swagger con OpenAPI
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;

import java.util.List;
@RestController
@RequestMapping("/api/v1/distribuidor")
@Tag(name = "Distribuidor", description = "Operaciones relacionadas con distribuidores") // Anotación para Swagger/OpenAPI


public class DistribuidorController {
    @Autowired
    private DistribuidorService distribuidorService;

    @GetMapping
    @Operation(summary = "Listar todos los distribuidores", description = "Obtiene una lista de todos los distribuidores registrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de distribuidores obtenida exitosamente"),
        @ApiResponse(responseCode = "204", description = "No se encontraron distribuidores")        // Anotaciones para documentar respuestas de la API
    })
    public ResponseEntity<List<Distribuidor>> listar() {
        List<Distribuidor> distribuidores = distribuidorService.findAll();
        if (distribuidores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(distribuidores);

    }
    @PostMapping
    @Operation(summary = "Guardar un distribuidor", description = "Crea un nuevo distribuidor en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Distribuidor creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Error al crear el distribuidor")
    })
    public ResponseEntity<Distribuidor>guardar(@RequestBody Distribuidor distribuidor) {
        Distribuidor distribuidorNuevo = distribuidorService.save(distribuidor);
        return ResponseEntity.status(HttpStatus.CREATED).body(distribuidorNuevo);
    }

    @GetMapping("/{rut}")
    @Operation(summary = "Buscar distribuidor por RUT", description = "Obtiene un distribuidor específico por su RUT")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Distribuidor encontrado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Distribuidor no encontrado")
    })
    public ResponseEntity<Distribuidor> buscar(@PathVariable Integer rut) {
    try{
        Distribuidor distribuidor = distribuidorService.findByRut(rut);
        return ResponseEntity.ok(distribuidor);
        } catch (Exception e){
        return ResponseEntity.notFound().build();}
    }
    @PutMapping("/{rut}")
    @Operation(summary = "Actualizar distribuidor", description = "Actualiza la información de un distribuidor existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Distribuidor actualizado exitosamente",
            content = @Content(schema = @Schema(implementation = Distribuidor.class))),
        @ApiResponse(responseCode = "400", description = "Error al actualizar el distribuidor"),
        @ApiResponse(responseCode = "404", description = "Distribuidor no encontrado")
    })
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
    @Operation(summary = "Eliminar distribuidor", description = "Elimina un distribuidor del sistema por su RUT")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Distribuidor eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Distribuidor no encontrado")
    })
    public ResponseEntity<?> eliminar(@PathVariable Long rut) {
        try {
            distribuidorService.delete(rut);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
