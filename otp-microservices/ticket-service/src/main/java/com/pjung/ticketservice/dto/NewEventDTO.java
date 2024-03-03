package com.pjung.ticketservice.dto;

import java.time.LocalDateTime;
import java.util.List;

public record NewEventDTO(String title, String location, LocalDateTime startTimeStamp, LocalDateTime endTimeStamp, List<NewSeatDTO> seats) {
}
