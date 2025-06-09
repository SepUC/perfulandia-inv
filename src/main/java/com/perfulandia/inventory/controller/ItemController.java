package com.perfulandia.inventory.controller;


import com.perfulandia.inventory.Assamblers.ItemModelAssembler;
import com.perfulandia.inventory.model.Item;
import com.perfulandia.inventory.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/item")

public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
     private ItemModelAssembler itemModelAssembler;

    @GetMapping
    public List<EntityModel<Item>> Listar() {
        List<EntityModel<Item>> items = itemService.findAll().stream().map(itemModelAssembler::toModel).collect(Collectors.toList());
        return items;
    }

    @PostMapping
    public ResponseEntity<Item> guardar(@RequestBody Item item) {
        Item itemNuevo = itemService.save(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(itemNuevo);
    //  return new ResponseEntity<>(itemNuevo, HtppStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public EntityModel<Item> buscar(@PathVariable Integer id) {

            Item item = itemService.findById(id);
            return itemModelAssembler.toModel(item);

    }
    @PutMapping("/{id}")
    public ResponseEntity<Item> actualizar(@PathVariable Integer id, @RequestBody Item item) {
        try{
            Item it = itemService.findById(id);
            it.setId(id);
            it.setNombre(item.getNombre());
            it.setPrecio(item.getPrecio());


            itemService.save(it);
            return ResponseEntity.ok(item);

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            itemService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
