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

    @GetMapping(value = "validatePayment/{cardId}/{clientId}/{price}")
    public boolean validatePayment(@PathVariable("cardId") Long cardId, @PathVariable("clientId") Long clientId, @PathVariable("price") int price) {
        return bankCardService.isItMyCard(cardId, clientId) && bankCardService.isCardBalanceEnough(cardId, price);
    }

    @GetMapping(value = "validateToken/{token}")
    public boolean getToken(@PathVariable("token") String token) {
        return tokenService.validateToken(token);
    }

}
