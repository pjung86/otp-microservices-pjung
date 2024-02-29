package com.pjung.partnerservice.service.builder;

import com.pjung.partnerservice.dto.NewEventDTO;
import com.pjung.partnerservice.dto.NewSeatDTO;
import com.pjung.partnerservice.model.Event;
import com.pjung.partnerservice.model.Seat;
import com.pjung.partnerservice.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeatBuilder {
    private final SeatRepository seatRepository;

    @Autowired
    public SeatBuilder(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public Seat seatBuilder(NewSeatDTO newSeatDTO) {
        Seat seat = Seat
                .builder()
                .price(newSeatDTO.price())
                .currency(newSeatDTO.currency())
                .reserved(newSeatDTO.reserved())
                .build();
        seatRepository.save(seat);
        return seat;
    }
}
