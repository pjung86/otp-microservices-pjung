package com.pjung.partnerservice.exceptions;

public class SeatIsTakenException extends RuntimeException{

    public SeatIsTakenException (String message) {
        super(message);
    }
}
