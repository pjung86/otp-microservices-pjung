package com.pjung.apigateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ApiService {

    private final WebClient.Builder webClientBuilder;

    @Autowired
    public ApiService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public boolean validateToken(String token) {
        boolean result = webClientBuilder.build().get()
                .uri("http://core-service/core/getToken/{token}", token)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
        return result;
    }
}
