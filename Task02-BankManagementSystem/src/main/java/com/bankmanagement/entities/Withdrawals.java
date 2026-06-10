package com.bankmanagement.entities;

import com.bankmanagement.customeexception.InvalidAccountNumberException;

import java.util.Scanner;

public class Withdrawals {
    private String withdrawalId;
    private long accountNumber;
    private double withdrawalAmount;

    public Withdrawals() {
        super();
    }
    public Withdrawals(String withdrawalId, long accountNumber, double withdrawalAmount) {
        this.withdrawalId = withdrawalId;
        this.accountNumber = accountNumber;
        this.withdrawalAmount = withdrawalAmount;
    }

    public String getWithdrawalId() {
        return withdrawalId;
    }
    public void setWithdrawalId(String withdrawalId) {
        this.withdrawalId = withdrawalId;
    }

    public long getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getWithdrawalAmount() {
        return withdrawalAmount;
    }
    public void setWithdrawalAmount(double withdrawalAmount) {
        this.withdrawalAmount = withdrawalAmount;
    }

    public static Withdrawals withdrawalsInput() {
        Scanner input = new Scanner(System.in);
        Withdrawals withdrawal = new Withdrawals();

        String accountNumber = "";
        double withdrawalAmount = 0.0;

        System.out.println("\n======WITHDRAWAL AMOUNTS=======");
        while (true) {
            try {
                System.out.print("Enter account number: ");
                accountNumber = input.nextLine();
                if (accountNumber.length() != 14 || accountNumber.isEmpty()) {
                    throw new InvalidAccountNumberException("Invalid Account Number! Account Number must be length exactly 14...");
                } else {
                    while (true) {
                        System.out.print("Enter your deposits amount: ");
                        if (input.hasNextDouble()) {
                            withdrawalAmount = input.nextDouble();
                            input.nextLine();

                            withdrawal.setAccountNumber(Long.parseLong(accountNumber));
                            withdrawal.setWithdrawalAmount(withdrawalAmount);
                            break;
                        } else {
                            System.out.println("Invalid Amount! Try Again...");
                            input.nextLine();
                        }
                    }
                    break;
                }
            } catch (InvalidAccountNumberException e) {
                System.out.println(e.getMessage());
            }
        }

        return withdrawal;
    }
}
