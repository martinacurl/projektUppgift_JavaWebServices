package com.example.projektuppgift_javawebservices.repositories;

import com.example.projektuppgift_javawebservices.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepo extends JpaRepository<AppUser, Integer> {
}
