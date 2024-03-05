package com.pjung.coreservice.service.builder;

import com.pjung.coreservice.dto.ClientTokenDTO;
import com.pjung.coreservice.model.ClientToken;
import com.pjung.coreservice.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientTokenBuilder {

    private final TokenRepository tokenRepository;

    @Autowired
    public ClientTokenBuilder(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public ClientToken tokenBuilder (ClientTokenDTO clientTokenDTO) {
        ClientToken clientToken =  ClientToken.builder()
                .token(clientTokenDTO.token())
                .client(clientTokenDTO.client())
                .build();
        tokenRepository.save(clientToken);
        return clientToken;
    }
}
