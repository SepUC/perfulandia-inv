package com.perfulandia.inventory;

import com.perfulandia.inventory.model.Cliente;
import com.perfulandia.inventory.repository.ClienteRepository;
import com.perfulandia.inventory.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import java.util.List;




@SpringBootTest
public class ClienteServiceTest {

    @Autowired
    private ClienteService clienteService;

    @MockBean
    private ClienteRepository clienteRepository;

    @Test
    public void testfindClienteAll() {
        when(clienteRepository.findAll()).thenReturn(List.of(new Cliente(21372831,"7","Lamine Yamal","Ronaldo","Etezech@INACAP.cl")));

        List<Cliente> clientes = clienteService.findAll();

        assertNotNull(clientes);
        assertEquals(1,clientes.size());
    }
}
