package com.example.projektuppgift_javawebservices.controller;

import com.example.projektuppgift_javawebservices.dto.DtoRequest;
import com.example.projektuppgift_javawebservices.dto.DtoResponse;
import com.example.projektuppgift_javawebservices.dto.Post;
import com.example.projektuppgift_javawebservices.services.AppUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping
    public List<DtoResponse> findAll() {
        return appUserService.findAll();
    }

    @GetMapping("/{id}")
    public DtoResponse findUserById(@PathVariable int id) {
        return appUserService.findUserById(id);
    }

    @PostMapping
    public DtoResponse createUser(@RequestBody DtoRequest dtoRequest) {
        return appUserService.createUser(dtoRequest);
    }

    @PutMapping("/{id}")
    public DtoResponse updateUserById(
            @PathVariable int id,
            @RequestBody DtoRequest dtoRequest
    ) {
        return appUserService.updateUserById(id, dtoRequest);
    }

    @DeleteMapping("/{id}")
    public DtoResponse deleteUserById(@PathVariable int id) {
        return appUserService.deleteUserById(id);
    }

    @GetMapping("/{id}/posts")
    public List<Post> findAllByUserId(
            @PathVariable int id
    ) {
        return appUserService.findPostsByUser(id);
    }


}
