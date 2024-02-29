package com.pjung.partnerservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

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
    private String startTimeStamp;
    private String endTimeStamp;
    @ManyToMany
    @JoinTable(name = "Event_Seat",
            joinColumns = @JoinColumn(name = "event_id", referencedColumnName = "eventId"),
            inverseJoinColumns = @JoinColumn(name = "seat_id", referencedColumnName = "seatId"))
    @JsonManagedReference
    private List<Seat> seats;
}
