package com.perfulandia.inventory;

import com.perfulandia.inventory.model.*;
import com.perfulandia.inventory.repository.*;

import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Date;
import java.util.Random;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private DistribuidorRepository distribuidorRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private VentasRepository ventasRepository;

    @Override
    public void run(String... args){
        Faker faker = new Faker();
        Random random = new Random();

        //Generar clientes
        for (int i = 0; i < 20; i++) {
            Cliente cliente = new Cliente();
            cliente.setRun(faker.number().numberBetween(10000000, 27999999));
            cliente.setDv_run(String.valueOf(faker.options().option("K","1","2","3","4","5","6","7","8","9").charAt(0)));
            cliente.setNombre(faker.name().firstName());
            cliente.setApellido(faker.name().lastName());
            cliente.setCorreo(faker.internet().emailAddress());
            clienteRepository.save(cliente);
        }
        //Generar distribuidores
        for (int i = 0; i < 2; i++) {
            Distribuidor distribuidor = new Distribuidor();
            distribuidor.setRut(faker.number().numberBetween(700000000, 999999999));
            distribuidor.setDvrut(faker.options().option("1","2","3","4","5","6","7","8","9"));
            distribuidor.setNombre(faker.brand().sport());
            distribuidor.setDireccion(faker.address().fullAddress());
            distribuidorRepository.save(distribuidor);
        }
        //Generar Items
        for (int i = 0; i <5; i++) {
            Item item = new Item();
            item.setNombre(faker.brand().sport());
            item.setPrecio(faker.number().numberBetween(5000, 999999));
            itemRepository.save(item);
        }

        //Generar Ventas
        List<Cliente> clientes = clienteRepository.findAll();
        //List<Item> items = itemRepository.findAll();
        for (int i = 0; i < 10; i++) {
            Venta venta = new Venta();
            venta.setFecha_venta(new Date());
            venta.setTotal_venta(faker.number().numberBetween(10000, 999999));
            venta.setRun_cli(String.valueOf(faker.number().numberBetween(15999999,25999999)));
            venta.setId_dist(Integer.valueOf(String.valueOf(faker.number().randomNumber(2))));
            ventasRepository.save(venta);
        }
    }
}
