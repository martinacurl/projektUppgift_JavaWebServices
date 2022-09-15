package com.example.projektuppgift_javawebservices;

import com.example.projektuppgift_javawebservices.entities.AppUser;
import com.example.projektuppgift_javawebservices.repositories.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjektUppgiftJavaWebServicesApplication implements CommandLineRunner {

    @Autowired
    AppUserRepo appUserRepo;

    public static void main(String[] args) {
        SpringApplication.run(ProjektUppgiftJavaWebServicesApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        appUserRepo.save(new AppUser("Martina"));
    }
}
