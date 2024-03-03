package com.pjung.partnerservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PartnerServiceExceptionHandler {

    @ExceptionHandler(value = {EventNotFoundException.class})
    public ResponseEntity<Object> handleEventNotFoundException (EventNotFoundException eventNotFoundException) {
        EventException eventException = new EventException(eventNotFoundException.getMessage(), "90001", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(eventException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {SeatNotFoundException.class})
    public ResponseEntity<Object> handleSeatNotFoundException (SeatNotFoundException seatNotFoundException) {
        SeatException seatException = new SeatException(seatNotFoundException.getMessage(), "90002", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(seatException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {SeatIsTakenException.class})
    public ResponseEntity<Object> handleSeatIsTakenException (SeatIsTakenException seatIsTakenException) {
        SeatException seatException = new SeatException(seatIsTakenException.getMessage(), "90003", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(seatException, HttpStatus.BAD_REQUEST);
    }
}
