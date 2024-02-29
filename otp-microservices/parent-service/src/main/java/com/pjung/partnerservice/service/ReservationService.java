package com.pjung.partnerservice.service;

import com.pjung.partnerservice.dto.ReservationDTO;
import com.pjung.partnerservice.model.Reservation;
import com.pjung.partnerservice.repository.ReservationRepository;
import com.pjung.partnerservice.service.builder.ReservationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private final ReservationBuilder reservationBuilder;

    private final ReservationRepository reservationRepository;
    @Autowired
    public ReservationService(ReservationBuilder reservationBuilder, ReservationRepository reservationRepository) {
        this.reservationBuilder = reservationBuilder;
        this.reservationRepository = reservationRepository;
    }

    public Reservation addReservation (ReservationDTO reservationDTO) {
        return reservationBuilder.reservationBuilder(reservationDTO);
    }
}
