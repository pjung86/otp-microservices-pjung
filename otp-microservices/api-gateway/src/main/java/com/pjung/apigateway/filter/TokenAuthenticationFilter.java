package com.pjung.apigateway.filter;

import com.pjung.apigateway.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;


@Component
public class TokenAuthenticationFilter extends AbstractGatewayFilterFactory<TokenAuthenticationFilter.Config> {

    private final ApiService apiService;

    private RouteValidator validator;


    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                if (!exchange.getRequest().getHeaders().containsKey("User-Token")) {
                    throw new RuntimeException("Missing authorization header!");
                }
                String authHeader = exchange.getRequest().getHeaders().get("User-Token").get(0);
                System.out.println(authHeader);
                try {
                    apiService.validateToken(authHeader);
                } catch (Exception e) {
                    throw new RuntimeException("Érvénytelen token!");
                }
            }
            return chain.filter(exchange);
        }));
    }

    public static class Config {

    }

    @Autowired
    public TokenAuthenticationFilter(ApiService apiService, RouteValidator validator) {
        super(Config.class);
        this.apiService = apiService;
        this.validator = validator;
    }



}
