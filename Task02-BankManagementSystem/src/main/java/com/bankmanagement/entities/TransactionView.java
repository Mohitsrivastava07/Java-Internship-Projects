package com.bankmanagement.entities;

import com.bankmanagement.customeexception.InvalidAccountNumberException;

import java.util.Scanner;

public class TransactionView {
    private long accountNumber;

    public TransactionView() {
        super();
    }
    public TransactionView(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public long getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public static TransactionView transactionShow() {
        Scanner input = new Scanner(System.in);
        TransactionView view = new TransactionView();

        String accountNumber;

        System.out.println("\n======TRANSACTION DETAILS=======");
        while (true) {
            try {
                System.out.print("Enter account number: ");
                accountNumber = input.nextLine();
                if (accountNumber.length() != 14 || accountNumber.isEmpty()) {
                    throw new InvalidAccountNumberException("Invalid Account Number! Account Number must be length exactly 14...");
                }

                view.setAccountNumber(Long.parseLong(accountNumber));
                break;
            } catch (InvalidAccountNumberException e) {
                System.out.println(e.getMessage());
            }
        }
        return view;
    }
}
