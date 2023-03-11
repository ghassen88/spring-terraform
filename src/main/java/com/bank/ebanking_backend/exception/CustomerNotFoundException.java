package com.bank.ebanking_backend.exception;




public class CustomerNotFoundException extends  RuntimeException{


    public CustomerNotFoundException(String message) {
        super(message);
    }
}
