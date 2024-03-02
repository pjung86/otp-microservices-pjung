package com.pjung.ticketservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String email;
    @OneToMany(mappedBy = "client")
    @JsonManagedReference
    private List<ClientDevice> clientDevices;
    @OneToOne(mappedBy = "client")
    @JsonManagedReference
    private ClientBankCard clientBankCard;
    @OneToMany(mappedBy = "client")
    private List<ClientToken> clientTokens;

}
