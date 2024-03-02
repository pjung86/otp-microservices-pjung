package com.pjung.ticketservice.controller;

import com.pjung.ticketservice.dto.NewEventDTO;
import com.pjung.ticketservice.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.events.Event;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping(value = "events")
    public List<NewEventDTO> getPartnerEvents() {
        return ticketService.getAllPartnerEvents();
    }

    @GetMapping(value = "event/{id}")
    public NewEventDTO getPartnerEventById (@PathVariable("id") Long id) {
        return ticketService.getPartnerEventById(id);
    }
}
