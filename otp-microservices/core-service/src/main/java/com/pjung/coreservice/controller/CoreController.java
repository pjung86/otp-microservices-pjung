package com.pjung.coreservice.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pjung.coreservice.dto.ClientBankCardDTO;
import com.pjung.coreservice.dto.ClientDTO;
import com.pjung.coreservice.dto.ClientDeviceDTO;
import com.pjung.coreservice.dto.ClientTokenDTO;
import com.pjung.coreservice.model.Client;
import com.pjung.coreservice.model.ClientBankCard;
import com.pjung.coreservice.model.ClientDevice;
import com.pjung.coreservice.model.ClientToken;
import com.pjung.coreservice.service.BankCardService;
import com.pjung.coreservice.service.ClientDeviceService;
import com.pjung.coreservice.service.ClientService;
import com.pjung.coreservice.service.TokenService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/core")
public class CoreController {
    private final ClientService clientService;

    private final BankCardService bankCardService;

    private final TokenService tokenService;
    private final ClientDeviceService clientDeviceService;

    private final ObjectMapper objectMapper;

    public CoreController(ClientService clientService, BankCardService bankCardService, TokenService tokenService, ClientDeviceService clientDeviceService, ObjectMapper objectMapper) {
        this.clientService = clientService;
        this.bankCardService = bankCardService;
        this.tokenService = tokenService;
        this.clientDeviceService = clientDeviceService;
        this.objectMapper = objectMapper;
        addClientsFromJson();
        addDevicesFromJson();
        addTokensFromJson();
        addBankCardsFromJson();
    }

    @GetMapping(value = "clients")
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping(value = "client/{id}")
    public Client getClientById (@PathVariable("id") Long id) {
        return clientService.getClientById(id);
    }

    @GetMapping(value = "bankcard/{id}")
    public ClientBankCard getClientBankCardById(@PathVariable("id") Long id) {
        return bankCardService.getClientBankCardById(id);
    }

    @GetMapping(value = "validatePayment")
    public boolean validatePayment(@RequestParam Long cardId, @RequestParam Long clientId, @RequestParam int price) {
        return bankCardService.isItMyCard(cardId, clientId) && bankCardService.isCardBalanceEnough(cardId, price);
    }

    @GetMapping(value = "validateToken")
    public boolean getToken(@RequestParam("token") String token) {
        return tokenService.validateToken(token);
    }

    public List<Client> addClientsFromJson() {
        try {
            InputStream inputStream = new ClassPathResource("getClients.json").getInputStream();
            List<ClientDTO> clients = objectMapper.readValue(inputStream, new TypeReference<List<ClientDTO>>() {
            });
            List<Client> addedClients = clientService.addClients(clients);
            return ResponseEntity.ok(addedClients).getBody();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ClientDevice> addDevicesFromJson() {
        try {
            InputStream inputStream = new ClassPathResource("getDevices.json").getInputStream();
            List<ClientDeviceDTO> devices = objectMapper.readValue(inputStream, new TypeReference<List<ClientDeviceDTO>>() {
            });
            List<ClientDevice> addedDevices = clientDeviceService.addClientDevices(devices);
            return ResponseEntity.ok(addedDevices).getBody();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ClientToken> addTokensFromJson() {
        try {
            InputStream inputStream = new ClassPathResource("getTokens.json").getInputStream();
            List<ClientTokenDTO> tokens = objectMapper.readValue(inputStream, new TypeReference<List<ClientTokenDTO>>() {
            });
            List<ClientToken> addedTokens = tokenService.addTokens(tokens);
            return ResponseEntity.ok(addedTokens).getBody();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ClientBankCard> addBankCardsFromJson() {
        try {
            InputStream inputStream = new ClassPathResource("getClientBankCards.json").getInputStream();
            List<ClientBankCardDTO> bankCards = objectMapper.readValue(inputStream, new TypeReference<List<ClientBankCardDTO>>() {
            });
            List<ClientBankCard> addedBankCards = bankCardService.addBankCards(bankCards);
            return ResponseEntity.ok(addedBankCards).getBody();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
