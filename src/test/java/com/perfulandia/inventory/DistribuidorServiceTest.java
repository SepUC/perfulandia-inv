package com.perfulandia.inventory;

import com.perfulandia.inventory.model.Distribuidor;
import com.perfulandia.inventory.repository.DistribuidorRepository;
import com.perfulandia.inventory.service.DistribuidorService;
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
public class DistribuidorServiceTest {

    @Autowired
    private DistribuidorService distribuidorService;

    @MockBean
    private DistribuidorRepository distribuidorRepository;

    @Test
    public void testFindDistribuidorServiceAll() {
        when(distribuidorService.findAll()).thenReturn(List.of(new Distribuidor(780549687,"8","Balatrezes SPA","Av. Siempreviva 742")));
        List<Distribuidor> distribuidors = distribuidorService.findAll();

        assertNotNull(distribuidors);
        assertEquals(1, distribuidors.size());
    }
}
