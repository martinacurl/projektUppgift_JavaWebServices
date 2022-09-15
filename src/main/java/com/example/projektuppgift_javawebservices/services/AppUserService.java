package com.example.projektuppgift_javawebservices.services;

import com.example.projektuppgift_javawebservices.dto.DtoRequest;
import com.example.projektuppgift_javawebservices.dto.DtoResponse;
import com.example.projektuppgift_javawebservices.dto.Post;
import com.example.projektuppgift_javawebservices.entities.AppUser;
import com.example.projektuppgift_javawebservices.repositories.AppUserRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class AppUserService {

    private final AppUserRepo appUserRepo;
    private final WebClient webClient;

    // dependency injection så här?
    public AppUserService(AppUserRepo appUserRepo, WebClient webClient) {
        this.appUserRepo = appUserRepo;
        this.webClient = webClient;
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
        AppUser newAppUser = appUserRepo.save(new AppUser(dtoRequest.username()));

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

    public List<Post> findPostsByUser(int id) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder.path("/posts")
                        .queryParam("userId", id)
                        .build())
                .exchangeToFlux(clientResponse -> clientResponse.bodyToFlux(Post.class))
                .buffer()
                .blockLast();

    }
}
