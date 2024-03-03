package com.pjung.ticketservice.service;

import com.pjung.ticketservice.dto.NewEventDTO;
import com.pjung.ticketservice.dto.ReservationDTO;
import com.pjung.ticketservice.exceptions.EventIsExpiredException;
import com.pjung.ticketservice.exceptions.EventNotFoundException;
import com.pjung.ticketservice.exceptions.SeatIsTakenException;
import com.pjung.ticketservice.exceptions.SeatNotFoundException;
import com.pjung.ticketservice.model.ClientBankCard;
import com.pjung.ticketservice.model.Event;
import com.pjung.ticketservice.model.Seat;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class TicketService {
    private final WebClient.Builder webClientBuilder;

    public TicketService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }


    public List<Event> getAllPartnerEvents() {
        Event[] partnerEvents = webClientBuilder.build().get()
                .uri("http://partner-service/events/getEvents")
                .retrieve()
                .bodyToMono(Event[].class)
                .block();
        return Arrays.stream(partnerEvents).toList();
    }

    public Event getPartnerEventById(Long id) {
        Event partnerEvent = webClientBuilder.build().get()
                .uri("http://partner-service/events/getEvent/{id}", id)
                .retrieve()
                .bodyToMono(Event.class)
                .block();
        if(partnerEvent == null) {
            throw new EventNotFoundException("Nem létezik ilyen esemény!");
        }
        return partnerEvent;
    }

    public ClientBankCard getClientBankCardById (Long id) {
        ClientBankCard clientCard = webClientBuilder.build().get()
                .uri("http://core-service/core/bankcard/{id}", id)
                .retrieve()
                .bodyToMono(ClientBankCard.class)
                .block();
        return clientCard;
    }

    public ReservationDTO payForReservation (Long eventId, Long seatId) {
        Event currentEvent = getPartnerEventById(eventId);
        LocalDateTime currentTime = LocalDateTime.now();
        if(currentEvent == null) {
            throw new EventNotFoundException("Nem létezik ilyen esemény!");
        }

        if (currentEvent.isItExpired(currentTime)) {
            throw new EventIsExpiredException("Olyan eseményre ami már elkezdődött nem lehet jegyet eladni!");
        }
        Seat seat = currentEvent.getSeats().stream()
                .filter(seat1 -> seat1.getSeatId().equals(seatId))
                .findFirst()
                .orElseThrow(() -> new SeatNotFoundException("Nem létezik ilyen szék!"));
        if(seat.isReserved()) {
            throw new SeatIsTakenException("Már lefoglalt székre nem lehet jegyet eladni!");
        }
        return null;
    }
}
