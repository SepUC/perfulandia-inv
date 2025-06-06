package com.perfulandia.inventory.controller;

import com.perfulandia.inventory.model.Venta;
import com.perfulandia.inventory.service.VentaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ventas")
@Tag(name = "Venta", description = "Operaciones relacionadas con las ventas")
public class VentasController {

    @Autowired
    private VentaService ventaService;

    @GetMapping
    @Operation(summary = "Listar todas las ventas", description = "Obtiene una lista de todas las ventas registradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de ventas obtenida exitosamente"),
            @ApiResponse(responseCode = "204", description = "No se encontraron ventas")
    })
    public ResponseEntity<List<Venta>> listarVentas() {
        List<Venta> ventas = ventaService.findAll();
        if (ventas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ventas);
    }

    @PostMapping
    @Operation(summary = "Guardar una venta", description = "Registra una nueva venta en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Venta creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al crear la venta")
    })
    public ResponseEntity<Venta> guardar(@RequestBody Venta venta) {
        Venta ventaNueva = ventaService.save(venta);
        return ResponseEntity.status(HttpStatus.CREATED).body(ventaNueva);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una venta", description = "Actualiza la información de una venta existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venta actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    })
    public ResponseEntity<Venta> actualizar(@PathVariable Long id, @RequestBody Venta venta) {
        try {
            Venta ven = ventaService.findById(id);
            ven.setId_venta(id);
            ven.setFecha_venta(venta.getFecha_venta());
            ven.setTotal_venta(Integer.parseInt(venta.getRun_cli()));
            ventaService.save(ven);
            return ResponseEntity.ok(ven);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una venta", description = "Elimina una venta del sistema por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Venta eliminada exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Venta.class))),
            @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    })
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            ventaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

    


