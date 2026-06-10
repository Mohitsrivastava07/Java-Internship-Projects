package com.bankmanagement.customeexception;

public class InvalidMobileNumberException extends Exception {
    public InvalidMobileNumberException(String message) {
        super(message);
    }
}
