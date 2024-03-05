package com.pjung.apigateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;
@Component
public class RouteValidator {

    public static final List<String> openApiEndPoints = List.of(
            "/core/clients",
            "/core/client/{id}",
            "/eureka/web"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndPoints.stream().noneMatch(uri -> request.getURI().getPath().contains(uri));
}
