package com.pjung.ticketservice.exceptions;

public class EventIsExpiredException extends RuntimeException{

    public EventIsExpiredException (String message) {
        super(message);
    }
}
