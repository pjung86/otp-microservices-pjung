package com.pjung.ticketservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

import static java.lang.Integer.parseInt;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    private String title;
    private String location;
    private LocalDateTime startTimeStamp;
    private LocalDateTime endTimeStamp;
    @ManyToMany
    @JoinTable(name = "Event_Seat",
            joinColumns = @JoinColumn(name = "event_id", referencedColumnName = "eventId"),
            inverseJoinColumns = @JoinColumn(name = "seat_id", referencedColumnName = "seatId"))
    @JsonManagedReference
    private List<Seat> seats;

    public boolean isItExpired (LocalDateTime timeStamp) {
        return this.startTimeStamp.isBefore(timeStamp);
    }
}
