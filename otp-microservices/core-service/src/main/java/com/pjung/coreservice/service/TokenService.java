package com.pjung.coreservice.service;

import com.pjung.coreservice.dto.ClientDeviceDTO;
import com.pjung.coreservice.dto.ClientTokenDTO;
import com.pjung.coreservice.exceptions.TokenMissingException;
import com.pjung.coreservice.model.ClientDevice;
import com.pjung.coreservice.model.ClientToken;
import com.pjung.coreservice.repository.TokenRepository;
import com.pjung.coreservice.service.builder.ClientTokenBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public boolean validateToken (String token) {
        ClientToken currentToken = getTokenByToken(token);
        System.out.println("This is token from DB: " + currentToken);
        return currentToken.getToken().equals(token);
    }
}
