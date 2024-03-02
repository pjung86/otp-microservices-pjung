package com.pjung.ticketservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ClientToken {

    @Id
    private Long id;
    private String token;
    @ManyToOne
    private Client client;
}
