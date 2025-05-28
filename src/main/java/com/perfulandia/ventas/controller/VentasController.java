package com.perfulandia.ventas.controller;


import com.perfulandia.ventas.model.Venta;
import com.perfulandia.ventas.service.VentaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/ventas")

public class VentasController {


    @Autowired
    private VentaService ventaService;

    @GetMapping
    public ResponseEntity<List<Venta>> listarVentas() {
        List<Venta> ventas = ventaService.findAll();
        if (ventaService.findAll().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ventas);
    }




}
