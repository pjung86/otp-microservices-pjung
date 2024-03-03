package com.pjung.ticketservice.exceptions;

import org.springframework.http.HttpStatus;


public record EventException(String incident, String message, String errorCode, HttpStatus httpStatus) {

}
