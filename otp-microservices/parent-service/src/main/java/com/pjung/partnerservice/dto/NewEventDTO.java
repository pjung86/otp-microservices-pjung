package com.pjung.partnerservice.dto;

import com.pjung.partnerservice.model.Seat;

import java.util.List;

public record NewEventDTO (String title, String location, String startTimeStamp, String endTimeStamp, List<NewSeatDTO> seats) {
}
