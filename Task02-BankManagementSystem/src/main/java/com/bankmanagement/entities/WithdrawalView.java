package com.bankmanagement.entities;

import com.bankmanagement.customeexception.InvalidAccountNumberException;

import java.util.Scanner;

public class WithdrawalView {
    private long accountNumber;

    public WithdrawalView() {
        super();
    }
    public WithdrawalView(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public long getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public static WithdrawalView withdrawalShow() {
        Scanner input = new Scanner(System.in);
        WithdrawalView view = new WithdrawalView();

        String accountNumber;

        System.out.println("\n======WITHDRAWAL DETAILS=======");
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
