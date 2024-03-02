package com.pjung.coreservice.service;

import com.pjung.coreservice.model.ClientBankCard;
import com.pjung.coreservice.repository.BankCardRepository;
import com.pjung.coreservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankCardService {

    private final BankCardRepository bankCardRepository;

    @Autowired
    public BankCardService(BankCardRepository bankCardRepository) {
        this.bankCardRepository = bankCardRepository;
    }

    public ClientBankCard getClientBankCardById (Long id) {
        return bankCardRepository.getClientBankCardByClientId(id);
    }
}
