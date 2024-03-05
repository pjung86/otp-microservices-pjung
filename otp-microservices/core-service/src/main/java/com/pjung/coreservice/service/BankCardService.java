package com.pjung.coreservice.service;

import com.pjung.coreservice.dto.ClientBankCardDTO;
import com.pjung.coreservice.dto.ClientTokenDTO;
import com.pjung.coreservice.exceptions.BankCardIsNotClientsException;
import com.pjung.coreservice.exceptions.InsufficientFundException;
import com.pjung.coreservice.model.ClientBankCard;
import com.pjung.coreservice.model.ClientToken;
import com.pjung.coreservice.repository.BankCardRepository;
import com.pjung.coreservice.repository.ClientRepository;
import com.pjung.coreservice.service.builder.ClientBankCardBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankCardService {

    private final BankCardRepository bankCardRepository;
    private final ClientBankCardBuilder clientBankCardBuilder;

    @Autowired
    public BankCardService(BankCardRepository bankCardRepository, ClientBankCardBuilder clientBankCardBuilder) {
        this.bankCardRepository = bankCardRepository;
        this.clientBankCardBuilder = clientBankCardBuilder;
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

    public List<ClientBankCard> addBankCards(List<ClientBankCardDTO> clientBankCardDTOS) {
        List<ClientBankCard> clientBankCardList = new ArrayList<>();
        for (ClientBankCardDTO clientBankCardDTO : clientBankCardDTOS) {
            ClientBankCard addedClientBankCard = addCard(clientBankCardDTO);
            clientBankCardList.add(addedClientBankCard);
        }
        return clientBankCardList;
    }

    public ClientBankCard addCard(ClientBankCardDTO clientBankCardDTO) {
        return clientBankCardBuilder.bankCardBuilder(clientBankCardDTO);
    }
}
