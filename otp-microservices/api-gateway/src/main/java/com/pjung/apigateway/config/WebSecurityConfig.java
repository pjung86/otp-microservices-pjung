package com.pjung.apigateway.config;

import com.pjung.apigateway.security.TokenAuthenticationFilter;
import com.pjung.apigateway.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebSecurityConfig {

    private final WebClient.Builder webClientBuilder;
    @Autowired
    public WebSecurityConfig(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/eureka/web/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .addFilterAfter(tokenAuthenticationFilter(), ExceptionTranslationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter(){
        return new TokenAuthenticationFilter(apiService());
    }

    @Bean
    public ApiService apiService() {
        return new ApiService(webClientBuilder.build());
    }


}
