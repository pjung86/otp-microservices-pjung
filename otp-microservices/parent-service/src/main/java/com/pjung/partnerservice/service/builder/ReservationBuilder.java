package com.pjung.partnerservice.service.builder;

import com.pjung.partnerservice.dto.ReservationDTO;
import com.pjung.partnerservice.model.Reservation;
import com.pjung.partnerservice.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservationBuilder {
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationBuilder(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation reservationBuilder (ReservationDTO reservationDTO) {
        Reservation reservation = Reservation.builder()
                .reservationId(reservationDTO.reservationId())
                .success(reservationDTO.success())
                .build();

        reservationRepository.save(reservation);
        return reservation;
    }
}
