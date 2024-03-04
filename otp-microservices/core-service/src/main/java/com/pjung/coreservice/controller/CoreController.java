package com.pjung.coreservice.controller;

import com.pjung.coreservice.model.Client;
import com.pjung.coreservice.model.ClientBankCard;
import com.pjung.coreservice.model.ClientToken;
import com.pjung.coreservice.service.BankCardService;
import com.pjung.coreservice.service.ClientService;
import com.pjung.coreservice.service.TokenService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/core")
public class CoreController {
    private final ClientService clientService;

    private final BankCardService bankCardService;

    private final TokenService tokenService;

    public CoreController(ClientService clientService, BankCardService bankCardService, TokenService tokenService) {
        this.clientService = clientService;
        this.bankCardService = bankCardService;
        this.tokenService = tokenService;
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

    @GetMapping(value = "getToken/{token}")
    public boolean getToken(@PathVariable("token") String token) {
        ClientToken clientToken = tokenService.getTokenByToken(token);
        return clientToken.getToken().equals(token);
    }
}
