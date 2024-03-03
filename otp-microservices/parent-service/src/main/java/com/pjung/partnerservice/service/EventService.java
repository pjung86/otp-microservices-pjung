package com.pjung.partnerservice.service;

import com.pjung.partnerservice.exceptions.EventNotFoundException;
import com.pjung.partnerservice.repository.SeatRepository;
import com.pjung.partnerservice.service.builder.EventBuilder;
import com.pjung.partnerservice.dto.NewEventDTO;
import com.pjung.partnerservice.model.Event;
import com.pjung.partnerservice.model.Seat;
import com.pjung.partnerservice.repository.EventRepository;
import com.pjung.partnerservice.service.builder.SeatBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final EventBuilder eventBuilder;

    private final SeatRepository seatRepository;
    private final SeatBuilder seatBuilder;

    @Autowired
    public EventService(EventRepository eventRepository, EventBuilder eventBuilder, SeatRepository seatRepository, SeatBuilder seatBuilder) {
        this.eventRepository = eventRepository;
        this.eventBuilder = eventBuilder;
        this.seatRepository = seatRepository;
        this.seatBuilder = seatBuilder;
    }


    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(Long id) {
        if(eventRepository.findEventByEventId(id) == null) {
            throw new EventNotFoundException("Nem létezik ilyen esemény!");
        }
        return eventRepository.findEventByEventId(id);
    }

    public List<Event> addEvents(List<NewEventDTO> events) {
        List<Event> eventList = new ArrayList<>();
        for (NewEventDTO recipe : events) {
            Event addedEvent = addEvent(recipe);
            eventList.add(addedEvent);
        }
        return eventList;
    }

    public Event addEvent(NewEventDTO event) {
        List<Seat> seats = getSeats(event);
        return eventBuilder.eventBuilder(event, seats);
    }

    private List<Seat> getSeats(NewEventDTO newEventDTO) {
        return newEventDTO.seats().stream().map(seatBuilder::seatBuilder).toList();
    }
}
