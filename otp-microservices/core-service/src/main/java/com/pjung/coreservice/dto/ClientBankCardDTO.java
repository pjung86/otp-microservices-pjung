package com.pjung.coreservice.dto;

import com.pjung.coreservice.model.Client;

public record ClientBankCardDTO(String cardNumber, int cvcCode, String name, int amount, String currency, Client client) {
}
