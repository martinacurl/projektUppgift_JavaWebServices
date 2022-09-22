package com.example.projektuppgift_javawebservices.services;

import com.example.projektuppgift_javawebservices.entities.AppUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public boolean idMatchesUserId(int id){
        AppUser appUser = (AppUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return (appUser.getId() == id);
    }

}
