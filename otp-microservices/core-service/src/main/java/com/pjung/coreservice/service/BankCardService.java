package com.pjung.coreservice.service;

import com.pjung.coreservice.exceptions.BankCardIsNotClientsException;
import com.pjung.coreservice.exceptions.InsufficientFundException;
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
        return bankCardRepository.getClientBankCardById(id);
    }

    public boolean isItMyCard (Long cardId, Long clientId) {
        ClientBankCard currentCard = bankCardRepository.getClientBankCardById(cardId);
        if(!currentCard.isItMine(clientId)) {
            throw new BankCardIsNotClientsException("Ez a bankkártya nem ehhez a felhasználóhoz tartozik");
        }
        return currentCard.isItMine(clientId);
    }

    public boolean isCardBalanceEnough (Long cardId, int amount) {
        ClientBankCard currentCard = bankCardRepository.getClientBankCardById(cardId);
        if(!currentCard.checkFunds(amount)) {
            throw new InsufficientFundException("A felhasználónak nincs elegendő pénze hogy megvásárolja a jegyet!");
        }
        return currentCard.checkFunds(amount);
    }
}
