package com.pjung.ticketservice.exceptions;

public class SeatNotFoundException extends RuntimeException{

    public SeatNotFoundException (String message) {
        super(message);
    }
}
