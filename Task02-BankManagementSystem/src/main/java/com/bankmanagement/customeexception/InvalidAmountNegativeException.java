package com.bankmanagement.customeexception;

public class InvalidAmountNegativeException extends Exception {
    public InvalidAmountNegativeException (String message) {
        super(message);
    }
}
