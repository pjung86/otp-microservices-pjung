package com.pjung.coreservice.service;

import com.pjung.coreservice.exceptions.TokenMissingException;
import com.pjung.coreservice.model.ClientToken;
import com.pjung.coreservice.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private final TokenRepository tokenRepository;

    @Autowired
    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public ClientToken getTokenByToken(String token) {
        ClientToken clientToken = tokenRepository.getClientTokenByToken(token);
        if(clientToken == null) {
            throw new TokenMissingException("A felhasználói token nem szerepel");
        }
        return clientToken;
    }
}
