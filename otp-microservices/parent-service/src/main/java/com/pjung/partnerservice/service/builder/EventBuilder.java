package com.pjung.partnerservice.service.builder;

import com.pjung.partnerservice.dto.NewEventDTO;
import com.pjung.partnerservice.model.Event;
import com.pjung.partnerservice.model.Seat;
import com.pjung.partnerservice.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Component
public class EventBuilder {

    private EventRepository eventRepository;

    @Autowired
    public EventBuilder(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event eventBuilder(NewEventDTO newEventDTO, List<Seat> seatList){
        Event event =  Event.builder()
                .title(newEventDTO.title())
                .location(newEventDTO.location())
                .startTimeStamp(newEventDTO.startTimeStamp())
                .endTimeStamp(newEventDTO.endTimeStamp())
                .seats(seatList)
                .build();
        eventRepository.save(event);
        return event;
    }
}
