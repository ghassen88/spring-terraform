package com.bank.ebanking_backend.exception;





public class BalanceNotSuffisantException extends  RuntimeException {


    public BalanceNotSuffisantException(String message) {
        super(message);
    }
}
