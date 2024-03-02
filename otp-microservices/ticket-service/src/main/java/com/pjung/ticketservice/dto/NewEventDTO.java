package com.pjung.ticketservice.dto;

import java.util.List;

public record NewEventDTO(String title, String location, String startTimeStamp, String endTimeStamp, List<NewSeatDTO> seats) {
}
