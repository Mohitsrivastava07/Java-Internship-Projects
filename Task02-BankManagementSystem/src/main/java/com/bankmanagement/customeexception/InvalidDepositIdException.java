package com.bankmanagement.customeexception;

public class InvalidDepositIdException extends Exception {
    public InvalidDepositIdException (String message) {
        super(message);
    }
}
