package com.pjung.ticketservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ClientBankCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String cardNumber;
    private int cvcCode;
    private String name;
    private int amount;
    private String currency;
    @OneToOne
    @JsonBackReference
    private Client client;

    public boolean checkFunds(int amount) {
        return this.amount > amount;
    }
}
