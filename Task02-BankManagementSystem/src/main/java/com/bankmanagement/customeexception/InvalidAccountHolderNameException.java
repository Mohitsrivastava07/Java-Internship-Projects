package com.bankmanagement.customeexception;

public class InvalidAccountHolderNameException extends Exception {
    public InvalidAccountHolderNameException(String message) {
        super(message);
    }
}
