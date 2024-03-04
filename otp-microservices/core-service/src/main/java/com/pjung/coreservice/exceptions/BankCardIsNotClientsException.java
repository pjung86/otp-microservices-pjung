package com.pjung.coreservice.exceptions;

public class BankCardIsNotClientsException extends RuntimeException{

    public BankCardIsNotClientsException (String message) {
        super(message);
    }
}
