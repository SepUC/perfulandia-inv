package com.perfulandia.inventory;

import com.perfulandia.inventory.model.Item;
import com.perfulandia.inventory.repository.ItemRepository;
import com.perfulandia.inventory.service.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import java.util.List;

@Profile("test")
@SpringBootTest
public class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @MockBean
    private ItemRepository itemRepository;

    @Test
    public void testFindItemAll() {
        when(itemRepository.findAll()).thenReturn(List.of(new Item(1,"Perfume Balatrez",54782)));
        List<Item> items = itemService.findAll();
        assertNotNull(items);
        assertEquals(1, items.size());
    }
}
