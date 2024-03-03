package com.pjung.partnerservice.dto;

import com.pjung.partnerservice.model.Seat;

import java.time.LocalDateTime;
import java.util.List;

public record NewEventDTO (String title, String location, LocalDateTime startTimeStamp, LocalDateTime endTimeStamp, List<NewSeatDTO> seats) {
}
