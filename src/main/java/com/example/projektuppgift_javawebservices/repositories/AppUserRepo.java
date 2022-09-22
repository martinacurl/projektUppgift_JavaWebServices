package com.example.projektuppgift_javawebservices.repositories;

import com.example.projektuppgift_javawebservices.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepo extends JpaRepository<AppUser, Integer> {

    Optional<AppUser> findAppUserByUsername(String username);

}
