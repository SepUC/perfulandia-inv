package com.perfulandia.inventory.service;



import com.perfulandia.inventory.model.Item;
import com.perfulandia.inventory.repository.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional

public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item findById(long id) {
        return itemRepository.findById(id).get();

    }

    public Item save(Item item) {
        return itemRepository.save(item);
    }

    public void delete(Long item) {
        itemRepository.deleteById(item);
    }

}
