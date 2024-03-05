package com.pjung.apigateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;

@Service
public class ApiService {

    public ApiService() {
    }

    public boolean validateToken(String token) throws ExecutionException, InterruptedException {
        RestTemplate restTemplate = new RestTemplate();
        Boolean result = restTemplate.getForObject("http://localhost:8080/core/validateToken/" + token, Boolean.class);

        return result;
    }
}
