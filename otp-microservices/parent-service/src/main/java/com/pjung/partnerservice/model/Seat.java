package com.pjung.partnerservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatId;
    private int price;
    private String currency;
    private boolean reserved;
    @ManyToMany
    @JsonBackReference
    private List<Event> event;


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Seat{");
        sb.append("seatId=").append(seatId);
        sb.append(", price=").append(price);
        sb.append(", currency='").append(currency).append('\'');
        sb.append(", reserved=").append(reserved);
        sb.append(", event=").append(event);
        sb.append('}');
        return sb.toString();
    }
}
