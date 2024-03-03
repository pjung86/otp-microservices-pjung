package com.pjung.partnerservice.exceptions;

import org.springframework.http.HttpStatus;

public record SeatException(String message, String errorCode, HttpStatus httpStatus) {
}
