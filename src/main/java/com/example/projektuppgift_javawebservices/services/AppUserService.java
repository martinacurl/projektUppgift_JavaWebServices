package com.example.projektuppgift_javawebservices.services;

import com.example.projektuppgift_javawebservices.dto.DtoRequest;
import com.example.projektuppgift_javawebservices.dto.DtoResponse;
import com.example.projektuppgift_javawebservices.entities.AppUser;
import com.example.projektuppgift_javawebservices.repositories.AppUserRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class AppUserService {

    private final AppUserRepo appUserRepo;

    public AppUserService(AppUserRepo appUserRepo) {
        this.appUserRepo = appUserRepo;
    }

    public List<DtoResponse> findAll() {
        return appUserRepo.findAll()
                .stream()
                .map(appUser -> new DtoResponse(appUser.getId(), appUser.getUsername()))
                .toList();
    }

    public DtoResponse findUserById(int id) {
        AppUser existingAppUser = appUserRepo
                .findById(id)
                .orElseThrow();

        return new DtoResponse(existingAppUser.getId(), existingAppUser.getUsername());
    }

    public DtoResponse createUser(DtoRequest dtoRequest) {
        AppUser newAppUser = appUserRepo.save(new AppUser(dtoRequest.username(), dtoRequest.password()));

        return new DtoResponse(newAppUser.getId(), newAppUser.getUsername());
    }

    public DtoResponse updateUserById(int id, DtoRequest dtoRequest) {
        AppUser existingAppUser = appUserRepo
                .findById(id)
                .orElseThrow();

        if (existingAppUser.getUsername() != null) {
            existingAppUser.setUsername(dtoRequest.username());
        }

        AppUser updatedAppUser = appUserRepo.save(existingAppUser);
        return new DtoResponse(updatedAppUser.getId(), updatedAppUser.getUsername());
    }

    public DtoResponse deleteUserById(int id) {
        AppUser existingAppUser = appUserRepo
                .findById(id)
                .orElseThrow();

        appUserRepo.deleteById(existingAppUser.getId());

        return new DtoResponse(existingAppUser.getId(), existingAppUser.getUsername());

    }
}
