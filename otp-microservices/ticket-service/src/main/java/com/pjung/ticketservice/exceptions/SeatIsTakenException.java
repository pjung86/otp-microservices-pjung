package com.pjung.ticketservice.exceptions;

public class SeatIsTakenException extends RuntimeException{

    public SeatIsTakenException (String message) {
        super(message);
    }
}
