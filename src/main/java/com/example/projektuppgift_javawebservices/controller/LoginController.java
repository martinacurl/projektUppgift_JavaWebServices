package com.example.projektuppgift_javawebservices.controller;

import com.example.projektuppgift_javawebservices.dto.JwtRequestDto;
import com.example.projektuppgift_javawebservices.services.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authenticate")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<String> authenticate(@RequestBody JwtRequestDto jwtRequestDto){
        return loginService.authenticate(jwtRequestDto.username(), jwtRequestDto.password());
    }

    @GetMapping
    public Boolean validate(@RequestParam String token){
        return loginService.validate(token);
    }

}
