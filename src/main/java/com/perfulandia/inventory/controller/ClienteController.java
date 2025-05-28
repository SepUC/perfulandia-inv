package com.perfulandia.inventory.controller;

import com.perfulandia.inventory.model.Cliente;
import com.perfulandia.inventory.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> Listar() {
        List<Cliente> clientes =clienteService.findAll();
        if(clientes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        return ResponseEntity.ok(clientes);

    }

    @PostMapping
    public ResponseEntity<Cliente> Save(@RequestBody Cliente cliente) {
        Cliente clienteNuevo = clienteService.save(cliente);
        return new ResponseEntity<>(clienteNuevo, HttpStatus.CREATED);
    }
    @GetMapping("/{run}")
    public ResponseEntity<Cliente> Buscar(@PathVariable Integer run) {
        try{
            Cliente cliente = clienteService.findByRun(run);
            return ResponseEntity.ok(cliente);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{run}")
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
    public ResponseEntity<Cliente> Delete(@PathVariable Long run) {
        try{
            clienteService.delete(run);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}


