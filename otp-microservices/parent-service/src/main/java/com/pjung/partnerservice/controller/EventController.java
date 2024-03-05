package com.pjung.partnerservice.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pjung.partnerservice.dto.NewEventDTO;
import com.pjung.partnerservice.dto.ReservationDTO;
import com.pjung.partnerservice.exceptions.SeatIsTakenException;
import com.pjung.partnerservice.exceptions.SeatNotFoundException;
import com.pjung.partnerservice.model.Event;
import com.pjung.partnerservice.model.Reservation;
import com.pjung.partnerservice.model.Seat;
import com.pjung.partnerservice.repository.ReservationRepository;
import com.pjung.partnerservice.repository.SeatRepository;
import com.pjung.partnerservice.service.EventService;
import com.pjung.partnerservice.service.ReservationService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;

    private final SeatRepository seatRepository;

    private final ReservationService reservationService;

    private final ObjectMapper objectMapper;

    public EventController(EventService eventService, SeatRepository seatRepository, ReservationService reservationService, ObjectMapper objectMapper) {
        this.eventService = eventService;
        this.seatRepository = seatRepository;
        this.reservationService = reservationService;
        this.objectMapper = objectMapper;
        addEventFromJson();
    }

    @GetMapping(value = "getEvents")
    public List<Event> getEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping(value = "getEvent/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    @Transactional
    @PostMapping(value = "reserve")
    public ResponseEntity<Object> reserveSeat(@RequestParam Long eventId, @RequestParam Long seatId) {
        Event event = eventService.getEventById(eventId);

        Seat seat = event.getSeats().stream().filter(seat1 -> seat1.getSeatId().equals(seatId)).findFirst().orElseThrow(() -> new SeatNotFoundException("Nem létezik ilyen szék!"));

        if (seat.isReserved()) {
            throw new SeatIsTakenException("Már lefoglalt székre nem lehet jegyet eladni!");
        }
         ReservationDTO reservation = new ReservationDTO(event.getEventId() + seat.getSeatId(), true );
        // Return the success response
        return ResponseEntity.ok(reservationService.addReservation(reservation));
    }


    public List<Event> addEventFromJson() {
        try {
            InputStream inputStream = new ClassPathResource("getEvents.json").getInputStream();
            List<NewEventDTO> events = objectMapper.readValue(inputStream, new TypeReference<List<NewEventDTO>>() {
            });
            List<Event> addedEvents = eventService.addEvents(events);
            return ResponseEntity.ok(addedEvents).getBody();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}


