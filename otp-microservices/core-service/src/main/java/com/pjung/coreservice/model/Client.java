package com.pjung.coreservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Client {

    @Id
    private UUID Id;
    private String name;
    private String email;
    @OneToMany(mappedBy = "client")
    @JsonManagedReference
    private List<ClientDevice> clientDevices;
    @OneToOne(mappedBy = "client")
    private ClientBankCard clientBankCard;
    @OneToMany(mappedBy = "client")
    private List<ClientToken> clientTokens;

}
