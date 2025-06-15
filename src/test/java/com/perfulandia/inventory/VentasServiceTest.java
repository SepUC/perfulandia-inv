package com.perfulandia.inventory;


import com.perfulandia.inventory.model.Venta;
import com.perfulandia.inventory.repository.VentasRepository;
import com.perfulandia.inventory.service.VentaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;


@Profile("test")
@SpringBootTest
public class VentasServiceTest {

    @Autowired
    private VentaService ventasService;

    @MockBean
    private VentasRepository ventasRepository;

    @Test
    public void testVentasService() {
        when(ventasRepository.findAll()).thenReturn(List.of(new Venta(17L,new Date(),54789,"24.869.789-K",1)));
        List<Venta> ventas = ventasService.findAll();
        assertNotNull(ventas);
        assertEquals(1,ventas.size());
    }

}
