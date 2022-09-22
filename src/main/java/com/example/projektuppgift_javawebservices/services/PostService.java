package com.example.projektuppgift_javawebservices.services;

import com.example.projektuppgift_javawebservices.dto.Post;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class PostService {

    private final WebClient webClient;

    public PostService(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<Post> findPostsByUserId(int id){
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
