package com.bankmanagement.customeexception;

public class InvalidAadhaarNumberException extends Exception {
    public InvalidAadhaarNumberException (String message) {
        super(message);
    }
}
