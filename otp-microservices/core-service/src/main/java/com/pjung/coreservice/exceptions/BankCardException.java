package com.pjung.coreservice.exceptions;

import org.springframework.http.HttpStatus;

public record BankCardException (String incident, String message, String errorCode, HttpStatus httpStatus){
}
