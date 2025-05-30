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
public class DataLoader {
}
