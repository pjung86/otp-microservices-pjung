package com.pjung.coreservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CoreServiceExceptionHandler {

    @ExceptionHandler(value = BankCardIsNotClientsException.class)
    public ResponseEntity<Object> handleBankCardIsNotClientsException(BankCardIsNotClientsException bankCardIsNotClientsException) {
        BankCardException bankCardException = new BankCardException("A beérkezett kérésben szereplő bankkártya nem az adott felhasználóhoz tartozik", bankCardIsNotClientsException.getMessage(), "10100", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(bankCardException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InsufficientFundException.class)
    public ResponseEntity<Object> handleInsufficientFundException (InsufficientFundException insufficientFundException) {
        BankCardException bankCardException = new BankCardException("A beérkezett kérésben szereplő bankkártyán nem áll rendelkezésre a megfelelő összeg", insufficientFundException.getMessage(), "10101", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(bankCardException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = TokenMissingException.class)
        public ResponseEntity<Object> handleTokenMissingException (TokenMissingException tokenMissingException) {
            TokenException tokenException = new TokenException("A beérkezett kérésben a felhasználó token nem szerepel", tokenMissingException.getMessage(), "10050", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(tokenException, HttpStatus.BAD_REQUEST);
        }
}
