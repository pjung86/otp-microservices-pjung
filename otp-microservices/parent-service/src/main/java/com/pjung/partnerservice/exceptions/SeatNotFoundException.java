package com.pjung.partnerservice.exceptions;

public class SeatNotFoundException extends RuntimeException{

    public SeatNotFoundException (String message) {
        super(message);
    }
}
