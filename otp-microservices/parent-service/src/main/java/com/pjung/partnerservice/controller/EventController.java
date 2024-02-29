package com.pjung.partnerservice.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pjung.partnerservice.dto.NewEventDTO;
import com.pjung.partnerservice.dto.ReservationDTO;
import com.pjung.partnerservice.model.Event;
import com.pjung.partnerservice.model.Seat;
import com.pjung.partnerservice.repository.ReservationRepository;
import com.pjung.partnerservice.repository.SeatRepository;
import com.pjung.partnerservice.service.EventService;
import com.pjung.partnerservice.service.ReservationService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.NoSuchElementException;

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
    public List<Event> getEvents () {
        return eventService.getAllEvents();
    }

    @GetMapping (value = "getEvent/{id}")
    @ResponseBody
    public ResponseEntity<Event> getEventById (@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(eventService.getEventById(id));

        } catch (NoSuchElementException e) {
            HttpStatus httpStatus = HttpStatus.valueOf(90001);
            return  new ResponseEntity(httpStatus);
        }
    }

    @PostMapping(value = "reserve")
    public ResponseEntity<Object> reserveSeat (@RequestParam Long eventId, @RequestParam Long seatId, @RequestBody ReservationDTO reservationDTO) {
        Event event = eventService.getEventById(eventId);
        if (event == null) {
            return ResponseEntity.badRequest().body("Nem létezik ilyen esemény!");
        }

        Seat seat = event.getSeats().stream().filter(seat1 -> seat1.getSeatId().equals(seatId)).findFirst().orElseThrow(NoSuchElementException::new);
        System.out.println(seat.toString());
        if (seat == null) {
            return ResponseEntity.badRequest().body("Nem létezik ilyen szék!");
        }

        if (seat.isReserved()) {
            return ResponseEntity.badRequest().body("Már foglalt székre nem lehet jegyet foglalni!");
        }


        // Return the success response
        return ResponseEntity.ok(reservationService.addReservation(reservationDTO));
    }


    public List<Event> addEventFromJson() {
        try {
            InputStream inputStream = new ClassPathResource("getEvents.json").getInputStream();
            List<NewEventDTO> events = objectMapper.readValue(inputStream, new TypeReference<List<NewEventDTO>>() {});
            List<Event> addedEvents = eventService.addEvents(events);
            return ResponseEntity.ok(addedEvents).getBody();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}


