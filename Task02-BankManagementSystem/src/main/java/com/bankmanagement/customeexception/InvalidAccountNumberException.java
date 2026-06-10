package com.bankmanagement.customeexception;

public class InvalidAccountNumberException extends Exception {
    public InvalidAccountNumberException(String message) {
        super(message);
    }
}
