package com.pjung.coreservice.model;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ClientToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    @ManyToOne
    @Cascade(CascadeType.PERSIST)
    private Client client;
}
