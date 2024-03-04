package com.pjung.coreservice.exceptions;

public class TokenMissingException extends RuntimeException{

    public TokenMissingException (String message) {
        super(message);
    }
}
