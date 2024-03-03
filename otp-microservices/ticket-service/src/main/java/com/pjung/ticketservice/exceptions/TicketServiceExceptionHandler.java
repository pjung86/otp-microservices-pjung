package com.pjung.ticketservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TicketServiceExceptionHandler {

    @ExceptionHandler(value = {EventNotFoundException.class})
    public ResponseEntity<Object> handleEventNotFoundException (EventNotFoundException eventNotFoundException) {
        EventException eventException = new EventException("Ha nem létezik a beérkezett eventId", eventNotFoundException.getMessage(), "20001", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(eventException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {SeatNotFoundException.class})
    public ResponseEntity<Object> handleSeatNotFoundException (SeatNotFoundException seatNotFoundException) {
        SeatException seatException = new SeatException("Ha nem létezik a beérkezett seatId!", seatNotFoundException.getMessage(), "20002", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(seatException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {SeatIsTakenException.class})
    public ResponseEntity<Object> handleSeatIsTakenException (SeatIsTakenException seatIsTakenException) {
        SeatException seatException = new SeatException("Ha az adott seatId már foglalt!", seatIsTakenException.getMessage(), "20010", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(seatException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {EventIsExpiredException.class})
    public ResponseEntity<Object> handleEventIsExpiredException (EventIsExpiredException eventIsExpiredException) {
        EventException eventException = new EventException("Ha az esemény startTimeStamp-je már kisebb mint a szerveridő", eventIsExpiredException.getMessage(), "20011", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(eventException, HttpStatus.BAD_REQUEST);
    }
}
