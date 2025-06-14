package com.perfulandia.inventory.controller;

import com.perfulandia.inventory.Assamblers.ItemModelAssembler;
import com.perfulandia.inventory.model.Item;
import com.perfulandia.inventory.service.ItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/item")
@Tag(name = "Item", description = "Operaciones relacionadas con los ítems del inventario")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemModelAssembler itemModelAssembler;

    @GetMapping
    @Operation(summary = "Listar todos los ítems", description = "Obtiene una lista de todos los ítems del inventario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de ítems obtenida exitosamente"),
            @ApiResponse(responseCode = "204", description = "No se encontraron ítems")
    })
    public ResponseEntity<List<EntityModel<Item>>> listar() {
        List<Item> items = itemService.findAll();
        if (items.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<EntityModel<Item>> itemsHATEOAS = items.stream()
                .map(itemModelAssembler::toModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(itemsHATEOAS);
    }

    @PostMapping
    @Operation(summary = "Guardar un ítem", description = "Crea un nuevo ítem en el inventario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ítem creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al crear el ítem")
    })
    public ResponseEntity<EntityModel<Item>> guardar(@RequestBody Item item) {
        Item itemNuevo = itemService.save(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(itemModelAssembler.toModel(itemNuevo));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar ítem por ID", description = "Obtiene un ítem específico por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ítem encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Ítem no encontrado")
    })
    public ResponseEntity<EntityModel<Item>> buscar(@PathVariable Integer id) {
        try {
            Item item = itemService.findById(id);
            return ResponseEntity.ok(itemModelAssembler.toModel(item));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un ítem", description = "Actualiza la información de un ítem existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ítem actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Ítem no encontrado")
    })
    public ResponseEntity<EntityModel<Item>> actualizar(@PathVariable Integer id, @RequestBody Item item) {
        try {
            Item existente = itemService.findById(id);
            existente.setNombre(item.getNombre());
            existente.setPrecio(item.getPrecio());
            Item actualizado = itemService.save(existente);
            return ResponseEntity.ok(itemModelAssembler.toModel(actualizado));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un ítem", description = "Elimina un ítem del inventario por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ítem eliminado exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Item.class))),
            @ApiResponse(responseCode = "404", description = "Ítem no encontrado")
    })
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            itemService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
