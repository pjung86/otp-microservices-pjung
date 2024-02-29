package com.pjung.partnerservice.repository;

import com.pjung.partnerservice.dto.ReservationDTO;
import com.pjung.partnerservice.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
