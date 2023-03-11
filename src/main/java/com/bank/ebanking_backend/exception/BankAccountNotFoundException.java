package com.bank.ebanking_backend.exception;





public class BankAccountNotFoundException extends RuntimeException{

    public BankAccountNotFoundException(String message) {
        super(message);
    }
}
