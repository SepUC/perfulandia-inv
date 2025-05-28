package com.perfulandia.ventas.service;

import org.springframework.stereotype.Service;
import com.perfulandia.ventas.repository.VentasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.perfulandia.ventas.model.Venta;
import jakarta.transaction.Transactional;

import java.util.List;



@Service
@Transactional
public class VentaService {

    @Autowired
    private VentasRepository ventasRepository;

    public List<Venta> findAll() {
        return ventasRepository.findAll();
    }

    public Venta findById(Long id_venta) {
        return ventasRepository.findById(id_venta).get();
    }

    public Venta save(Venta venta) {
        return ventasRepository.save(venta);
    }

    public void deleteById(Long id_venta) {
        ventasRepository.deleteById(id_venta);
    }
}
