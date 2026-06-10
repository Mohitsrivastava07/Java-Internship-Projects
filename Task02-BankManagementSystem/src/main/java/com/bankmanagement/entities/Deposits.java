package com.bankmanagement.entities;

import com.bankmanagement.customeexception.InvalidAccountNumberException;

import java.util.Scanner;

public class Deposits {
    private String depositId;
    private long accountNumber;
    private double depositsAmount;

    public Deposits() {
        super();
    }
    public Deposits(String depositId, long accountNumber, double depositsAmount) {
        this.depositId = depositId;
        this.accountNumber = accountNumber;
        this.depositsAmount = depositsAmount;
    }

    public String getDepositId() {
        return depositId;
    }
    public void setDepositId(String depositId) {
        this.depositId = depositId;
    }

    public long getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getDepositsAmount() {
        return depositsAmount;
    }
    public void setDepositsAmount(double depositsAmount) {
        this.depositsAmount = depositsAmount;
    }

    public static Deposits depositsInput() {
        Scanner input = new Scanner(System.in);
        Deposits deposit = new Deposits();

        String accountNumber = "";
        double depositsAmount = 0.0;

        System.out.println("\n======DEPOSITS AMOUNTS=======");
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
                            depositsAmount = input.nextDouble();
                            input.nextLine();

                            deposit.setAccountNumber(Long.parseLong(accountNumber));
                            deposit.setDepositsAmount(depositsAmount);
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

        return deposit;
    }
}
