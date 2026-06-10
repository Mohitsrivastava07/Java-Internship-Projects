package com.bankmanagement.entities;

import com.bankmanagement.customeexception.InvalidAccountNumberException;

import java.util.Scanner;

public class AccountView {
    private long accountNumber;

    public AccountView() {
        super();
    }
    public AccountView(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public long getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public static AccountView accountShow() {
        Scanner input = new Scanner(System.in);
        AccountView view = new AccountView();

        String accountNumber;

        System.out.println("\n======ACCOUNT DETAILS=======");
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
