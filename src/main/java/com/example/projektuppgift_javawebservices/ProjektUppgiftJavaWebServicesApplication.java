package com.example.projektuppgift_javawebservices;

import com.example.projektuppgift_javawebservices.entities.AppUser;
import com.example.projektuppgift_javawebservices.repositories.AppUserRepo;
import com.example.projektuppgift_javawebservices.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ProjektUppgiftJavaWebServicesApplication implements CommandLineRunner {

    @Autowired
    AppUserRepo appUserRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AppUserService appUserService;

    public static void main(String[] args) {
        SpringApplication.run(ProjektUppgiftJavaWebServicesApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        appUserRepo.save(new AppUser("Martina", passwordEncoder.encode("pass")));
        appUserRepo.save(new AppUser("Viktor", passwordEncoder.encode("pass")));
        appUserRepo.save(new AppUser("Björn", passwordEncoder.encode("pass")));
    }
}
