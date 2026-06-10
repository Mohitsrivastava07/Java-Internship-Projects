package com.bankmanagement.customeexception;

public class InvalidAddressException extends Exception {
    public InvalidAddressException (String message) {
        super(message);
    }
}
