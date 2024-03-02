package com.pjung.coreservice.controller;

import com.pjung.coreservice.model.Client;
import com.pjung.coreservice.model.ClientBankCard;
import com.pjung.coreservice.service.BankCardService;
import com.pjung.coreservice.service.ClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/core")
public class CoreController {
    private final ClientService clientService;

    private final BankCardService bankCardService;

    public CoreController(ClientService clientService, BankCardService bankCardService) {
        this.clientService = clientService;
        this.bankCardService = bankCardService;
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
}
