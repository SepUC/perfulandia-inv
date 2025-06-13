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
    public ResponseEntity<List<EntityModel<Item>>> Listar() {
        List<Item> items = itemService.findAll();
        if (items.isEmpty()) {
            return ResponseEntity.noContent().build();

        }
        List<EntityModel<Item>> itemsHOAS = items.stream().map(itemModelAssembler::toModel).collect(Collectors.toList());
        return ResponseEntity.ok(itemsHOAS);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Item>> guardar(@RequestBody Item item) {
        Item itemNuevo = itemService.save(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(itemModelAssembler.toModel(itemNuevo));

    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Item>>buscar(@PathVariable Integer id) {

        try {
            Item item = itemService.findById(id);
            return ResponseEntity.ok(itemModelAssembler.toModel(item));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Item>> actualizar(@PathVariable Integer id, @RequestBody Item item) {
       try{
            Item it = itemService.findById(id);
            it.setId(id);
            it.setNombre(item.getNombre());
            it.setPrecio(item.getPrecio());


            itemService.save(it);
            return ResponseEntity.ok(itemModelAssembler.toModel(it));

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
