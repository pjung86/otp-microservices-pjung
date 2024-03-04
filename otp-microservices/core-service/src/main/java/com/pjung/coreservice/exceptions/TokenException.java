package com.pjung.coreservice.exceptions;

import org.springframework.http.HttpStatus;

public record TokenException(String incident, String message, String errorCode, HttpStatus httpStatus) {
}
