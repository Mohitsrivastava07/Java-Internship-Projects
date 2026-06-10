package com.bankmanagement.entities;

import com.bankmanagement.customeexception.InvalidAccountNumberException;

import java.util.Scanner;

public class TransactionHistory {

    enum TransactionType {
        online, cash, Online, Offline, ONLINE, OFFLINE
    }

    private String transactionId;
    private long accountNumber, otherAccountNumber;
    private String transactionType;
    private double transactionAmount;

    public TransactionHistory() {
        super();
    }
    public TransactionHistory(String transactionId, long accountNumber, long otherAccountNumber, String transactionType, double transactionAmount) {
        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.otherAccountNumber = otherAccountNumber;
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public long getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public long getOtherAccountNumber() {
        return otherAccountNumber;
    }
    public void setOtherAccountNumber(long otherAccountNumber) {
        this.otherAccountNumber = otherAccountNumber;
    }

    public String getTransactionType() {
        return transactionType;
    }
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }
    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public static TransactionHistory transactionInput() {
        Scanner input = new Scanner(System.in);
        TransactionHistory transactionHistory = new TransactionHistory();

        String yourAccountNumber = "";
        String otherAccountNumber = "";
        String transactionType = "";
        double transactionAmount = 0.0;

        System.out.println("\n======TRANSACTION AMOUNTS=======");
        while (true) {
            try {
                System.out.print("Enter your account number: ");
                yourAccountNumber = input.nextLine();
                if (yourAccountNumber.length() != 14 || yourAccountNumber.isEmpty()) {
                    throw new InvalidAccountNumberException("Invalid Your Account Number! Account Number must be length exactly 14...");
                } else {
                    while (true) {
                        try {
                            System.out.print("Enter transaction account number: ");
                            otherAccountNumber = input.nextLine();
                            if (otherAccountNumber.length() != 14 || otherAccountNumber.isEmpty()) {
                                throw new InvalidAccountNumberException("Invalid Transaction Account Number! Account Number must be length exactly 14...");
                            } else {
                                while (true) {
                                    System.out.print("Enter transaction type(online, cash): ");
                                    transactionType = input.nextLine();
                                    try {
                                        TransactionType transactionType1 = TransactionType.valueOf(transactionType);

                                        while (true) {
                                            System.out.print("Enter transaction amount: ");
                                            if (input.hasNextDouble()) {
                                                transactionAmount = input.nextDouble();
                                                input.nextLine();
                                                break;
                                            } else {
                                                System.out.println("Invalid Transaction Amount! Transaction amount followed by ony number not alphabets...");
                                                input.nextLine();
                                            }
                                        }
                                        break;
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                                break;
                            }
                        } catch (InvalidAccountNumberException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                }
            } catch (InvalidAccountNumberException e) {
                System.out.println(e.getMessage());
            }
        }

        transactionHistory.setAccountNumber(Long.parseLong(yourAccountNumber));
        transactionHistory.setOtherAccountNumber(Long.parseLong(otherAccountNumber));
        transactionHistory.setTransactionType(transactionType);
        transactionHistory.setTransactionAmount(transactionAmount);

        return transactionHistory;
    }
}
