package com.pjung.coreservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ClientBankCard {

    @Id
    private UUID Id;
    private String cardNumber;
    private int cvcCode;
    private String name;
    private int amount;
    private String currency;
    @OneToOne
    private Client client;
}
