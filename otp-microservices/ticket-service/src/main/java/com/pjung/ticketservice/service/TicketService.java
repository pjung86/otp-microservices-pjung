package com.pjung.ticketservice.service;

import com.pjung.ticketservice.dto.NewEventDTO;
import com.pjung.ticketservice.dto.ReservationDTO;
import com.pjung.ticketservice.model.ClientBankCard;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Service
public class TicketService {
    private final WebClient.Builder webClientBuilder;

    public TicketService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }


    public List<NewEventDTO> getAllPartnerEvents() {
        NewEventDTO[] partnerEvents = webClientBuilder.build().get()
                .uri("http://partner-service/events/getEvents")
                .retrieve()
                .bodyToMono(NewEventDTO[].class)
                .block();
        return Arrays.stream(partnerEvents).toList();
    }

    public NewEventDTO getPartnerEventById(Long id) {
        NewEventDTO partnerEvent = webClientBuilder.build().get()
                .uri("http://partner-service/events/getEvent/{id}", id)
                .retrieve()
                .bodyToMono(NewEventDTO.class)
                .block();

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
        return null;
    }
}
