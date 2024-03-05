package com.pjung.partnerservice.service;

import com.pjung.partnerservice.model.Event;
import com.pjung.partnerservice.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {
    @InjectMocks
    private EventService eventService;

    @Mock
    private EventRepository eventRepository;
    @Test
    void getEventById() {
        Long id = 1L;
        Event expectedEvent = new Event();
        when(eventRepository.findEventByEventId(id)).thenReturn(expectedEvent);

        Event actualEvent = eventService.getEventById(id);

        assertEquals(expectedEvent, actualEvent);
    }
}