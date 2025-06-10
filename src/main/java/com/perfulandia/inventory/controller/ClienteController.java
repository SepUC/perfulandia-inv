package com.perfulandia.inventory.controller;

import com.perfulandia.inventory.model.Cliente;
import com.perfulandia.inventory.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse; // Import de anotaciones de Swagger con OpenAPI
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
@Tag(name = "Cliente", description = "Operaciones relacionadas con clientes") // Anotación para Swagger/OpenAPI


public class ClienteController {

    @Autowired
    private ClienteService clienteService;


     @GetMapping
    @Operation(summary = "Listar todos los clientes", description = "Obtiene una lista de todos los clientes registrados")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Lista de clientes obtenida exitosamente"),
                            @ApiResponse(responseCode = "204", description = "No se encontraron clientes")}) // Anotaciones para documentar respuestas de la API
    public ResponseEntity<List<Cliente>> Listar() {
        List<Cliente> clientes =clienteService.findAll();
        if(clientes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        return ResponseEntity.ok(clientes);

    }


    @PostMapping
    @Operation(summary = "Guardar un cliente", description = "Crea un nuevo cliente en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente creado exitosamente"),  // Anotaciones para documentar respuestas de la API
        @ApiResponse(responseCode = "400", description = "Error al crear el cliente")
    })
    public ResponseEntity<Cliente> Save(@RequestBody Cliente cliente) {
        Cliente clienteNuevo = clienteService.save(cliente);
        return new ResponseEntity<>(clienteNuevo, HttpStatus.CREATED);
    }


    @GetMapping("/{run}")
    @Operation(summary = "Buscar cliente por RUN", description = "Obtiene un cliente específico por su RUN")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente encontrado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public ResponseEntity<Cliente> Buscar(@PathVariable Integer run) {
        try{
            Cliente cliente = clienteService.findByRun(run);
            return ResponseEntity.ok(cliente);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{run}")
    @Operation(summary = "Actualizar cliente", description = "Actualiza la información de un cliente existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public ResponseEntity<Cliente> Update(@PathVariable Integer run, @RequestBody Cliente cliente) {
        try {
            Cliente cli = clienteService.findByRun(run);
            cli.setRun(run);
            cli.setDv_run(cliente.getDv_run());
            cli.setNombre(cliente.getNombre());
            cli.setApellido(cliente.getApellido());
            cli.setCorreo(cliente.getCorreo());
            clienteService.save(cli);
            return ResponseEntity.ok(cli);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("{run}")
    @Operation(summary = "Eliminar cliente", description = "Elimina un cliente del sistema por su RUN")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente eliminado exitosamente",
        content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Cliente.class))), // Detalle de datos en caso de éxito
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public ResponseEntity<Cliente> Delete(@PathVariable Long run) {
        try{
            clienteService.delete(run);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}


