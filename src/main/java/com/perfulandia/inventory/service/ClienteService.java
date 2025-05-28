package com.perfulandia.inventory.service;

import com.perfulandia.inventory.model.Cliente;
import com.perfulandia.inventory.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional

public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }
    public Cliente findByRun(long run) {
        return clienteRepository.findById(run).get();
    }
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
    public void delete(Long cliente) {
        clienteRepository.deleteById(cliente);
    }

}
