package com.perfulandia.inventory.Assamblers;

import com.perfulandia.inventory.controller.ItemController;
import com.perfulandia.inventory.model.Item;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

    @Component
    public class ItemModelAssembler implements RepresentationModelAssembler <Item, EntityModel<Item>> {

        @Override
        public EntityModel<Item> toModel(Item item) {
            return EntityModel.of(item,
                    linkTo(methodOn(ItemController.class).buscar(item.getId())).withSelfRel()
                    );
        }
    }

