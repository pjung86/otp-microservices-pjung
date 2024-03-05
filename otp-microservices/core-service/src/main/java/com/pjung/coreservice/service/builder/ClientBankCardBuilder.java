package com.pjung.coreservice.service.builder;

import com.pjung.coreservice.dto.ClientBankCardDTO;
import com.pjung.coreservice.model.ClientBankCard;
import com.pjung.coreservice.repository.BankCardRepository;
import org.springframework.stereotype.Component;

@Component
public class ClientBankCardBuilder {

    private final BankCardRepository bankCardRepository;

    public ClientBankCardBuilder(BankCardRepository bankCardRepository) {
        this.bankCardRepository = bankCardRepository;
    }

    public ClientBankCard bankCardBuilder (ClientBankCardDTO clientBankCardDTO) {
        ClientBankCard clientBankCard = ClientBankCard.builder()
                .cardNumber(clientBankCardDTO.cardNumber())
                .cvcCode(clientBankCardDTO.cvcCode())
                .name(clientBankCardDTO.name())
                .amount(clientBankCardDTO.amount())
                .currency(clientBankCardDTO.currency())
                .client(clientBankCardDTO.client())
                .build();
        bankCardRepository.save(clientBankCard);
        return clientBankCard;
    }
}
