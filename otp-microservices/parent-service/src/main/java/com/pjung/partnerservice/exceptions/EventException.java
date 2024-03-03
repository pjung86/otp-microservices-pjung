package com.pjung.partnerservice.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;


public record EventException(String message, String errorCode, HttpStatus httpStatus) {

}
