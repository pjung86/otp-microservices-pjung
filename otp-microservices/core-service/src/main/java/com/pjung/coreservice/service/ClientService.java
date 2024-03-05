package com.pjung.coreservice.service;

import com.pjung.coreservice.dto.ClientDTO;
import com.pjung.coreservice.model.Client;
import com.pjung.coreservice.repository.ClientRepository;
import com.pjung.coreservice.service.builder.ClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private  final ClientBuilder clientBuilder;

    @Autowired
    public ClientService(ClientRepository clientRepository, ClientBuilder clientBuilder) {
        this.clientRepository = clientRepository;
        this.clientBuilder = clientBuilder;
    }

    public List<Client> getAllClients() {
        return  clientRepository.findAll();
    }


    public Client getClientById (Long id) {
        return clientRepository.getClientById(id);
    }

    public List<Client> addClients(List<ClientDTO> clients) {
        List<Client> clientList = new ArrayList<>();
        for (ClientDTO client : clients) {
            Client addedEvent = addClient(client);
            clientList.add(addedEvent);
        }
        return clientList;
    }

    public Client addClient(ClientDTO client) {

        return clientBuilder.clientBuilder(client);
    }
}
