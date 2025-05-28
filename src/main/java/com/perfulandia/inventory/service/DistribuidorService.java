package com.perfulandia.inventory.service;

import com.perfulandia.inventory.model.Distribuidor;
import com.perfulandia.inventory.repository.DistribuidorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional




public class DistribuidorService {

    @Autowired
    private DistribuidorRepository distribuidorRepository;

    public List<Distribuidor> findAll() {
        return distribuidorRepository.findAll();
    }

    public Distribuidor findByRut(long rut) {
        return distribuidorRepository.findById(rut).get();
    }

    public Distribuidor save(Distribuidor distribuidor) {
        return distribuidorRepository.save(distribuidor);
    }

    public void delete(Long distribuidor) {
        distribuidorRepository.deleteById(distribuidor);
    }

}
