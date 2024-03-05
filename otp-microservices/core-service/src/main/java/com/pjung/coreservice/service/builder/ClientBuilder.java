package com.pjung.coreservice.service.builder;

import com.netflix.discovery.converters.Auto;
import com.pjung.coreservice.dto.ClientDTO;
import com.pjung.coreservice.model.Client;
import com.pjung.coreservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientBuilder {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientBuilder(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client clientBuilder (ClientDTO clientDTO){
        Client client =  Client.builder()
                .name(clientDTO.name())
                .email(clientDTO.email())
                .build();
        clientRepository.save(client);
        return client;
    }
}
