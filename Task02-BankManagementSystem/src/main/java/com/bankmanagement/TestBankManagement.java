package com.bankmanagement;

import com.bankmanagement.dao.BankDao;
import com.bankmanagement.entities.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.Scanner;

public class TestBankManagement {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        CreateAccount bank = null;
        Deposits deposits = null;
        Withdrawals withdrawals = null;
        TransactionHistory transactionHistory = null;
        AccountView accountView = null;
        DepositsView depositsView = null;
        WithdrawalView withdrawalView = null;
        TransactionView transactionView = null;

        ApplicationContext context = new ClassPathXmlApplicationContext("bank.xml");
        BankDao bankDao = context.getBean("bankDao", BankDao.class);

        System.out.println("\nProgram is started....");
        System.out.println("BANK MANAGEMENT SYSTEM");

        while (true) {
            System.out.println();
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Amounts");
            System.out.println("3. Withdrawal Amounts");
            System.out.println("4. Transaction History");
            System.out.println("5. Account Details");
            System.out.println("6. Deposits Details");
            System.out.println("7. Withdrawal Details");
            System.out.println("8. Transaction Details");
            System.out.println("0. Exit");

            System.out.print("Choose any option: ");
            if (input.hasNextInt()) {
                int choice = input.nextInt();
                input.nextLine();

                if (choice == 1) {
                    bank = CreateAccount.createAccountInput();
                    if (bank != null) {
                        int result = bankDao.createAccount(bank);
                        if (result > 0) {
                            System.out.println("Account Created Successfully!");
                        } else {
                            System.out.println("Account Creation Failed!");
                        }
                    }

                } else if (choice == 2) {
                    deposits = Deposits.depositsInput();
                    if (deposits != null) {
                        int result = bankDao.deposits(deposits);
                        if (result > 0) {
                            System.out.println("Amount Deposited Successfully! Balance Updated!");
                        } else {
                            System.out.println("FAILED: Account Number Not Found!");
                        }
                    }

                } else if (choice == 3) {
                    withdrawals = Withdrawals.withdrawalsInput();
                    if (withdrawals != null) {
                        int result = bankDao.withdrawals(withdrawals);
                        if (result > 0) {
                            System.out.println("Amount Withdrawal Successfully! Balance Updated!");
                        } else {
                            System.out.println("FAILED: Account Number Not Found!");
                        }
                    }

                } else if (choice == 4) {
                    transactionHistory = TransactionHistory.transactionInput();
                    if (transactionHistory != null) {
                        int result = bankDao.transactionHistory(transactionHistory);
                        if (result > 0) {
                            System.out.println("Amount Transacted Successfully! Balances Updated!");
                        } else {
                            System.out.println("Amount Transacted Failed!");
                        }
                    }

                } else if (choice == 5) {
                    accountView = AccountView.accountShow();
                    if (accountView != null) {
                        CreateAccount account = bankDao.viewAccountDetails(accountView.getAccountNumber());

                        if (account != null) {
                            System.out.println("\n--------------------------------");
                            System.out.println("Account Number : " + account.getAccountNumber());
                            System.out.println("Name           : " + account.getName());
                            System.out.println("Age            : " + account.getAge());
                            System.out.println("Mobile Number  : " + account.getMobileNumber());
                            System.out.println("Address        : " + account.getAddress());
                            System.out.println("Account Type   : " + account.getAccountType());
                            System.out.println("Aadhaar Number : " + account.getAadhaarNumber());
                            System.out.println("Balance        : " + account.getBalance());
                        } else {
                            System.out.println("Account Not Found!");
                        }
                    }

                } else if (choice == 6) {
                    depositsView = DepositsView.depositsShow();
                    if (depositsView != null) {
                        Deposits deposit = bankDao.viewDepositsDetails(depositsView.getAccountNumber());

                        if (deposit != null) {
                            System.out.println("\n-------------------------------------------------");
                            System.out.println("Deposit Id       : " + deposit.getDepositId());
                            System.out.println("Account Number   : " + deposit.getAccountNumber());
                            System.out.println("Deposit Amount   : " + deposit.getDepositsAmount());
                        } else {
                            System.out.println("Account Deposit Not Found!");
                        }
                    }

                } else if (choice == 7) {
                    withdrawalView = WithdrawalView.withdrawalShow();
                    if (withdrawalView != null) {
                        Withdrawals withdrawal = bankDao.viewWithdrawalsDetails(depositsView.getAccountNumber());

                        if (withdrawal != null) {
                            System.out.println("\n---------------------------------------------------------");
                            System.out.println("Withdrawal Id       : " + withdrawal.getWithdrawalId());
                            System.out.println("Account Number      : " + withdrawal.getAccountNumber());
                            System.out.println("Withdrawal Amount   : " + withdrawal.getWithdrawalAmount());
                        } else {
                            System.out.println("Account Withdrawal Not Found!");
                        }
                    }

                } else if (choice == 8) {
                    transactionView = TransactionView.transactionShow();
                    if (transactionView != null) {
                        TransactionHistory transaction = bankDao.viewTransactionDetails(depositsView.getAccountNumber());

                        if (transaction != null) {
                            System.out.println("\n--------------------------------------------------------------");
                            System.out.println("Deposit Id            : " + transaction.getTransactionId());
                            System.out.println("Account Number        : " + transaction.getAccountNumber());
                            System.out.println("Other Account Number  : " + transaction.getOtherAccountNumber());
                            System.out.println("Transaction Type      : " + transaction.getTransactionType());
                            System.out.println("Transaction Amount    : " + transaction.getTransactionAmount());
                        } else {
                            System.out.println("Account Transaction Not Found!");
                        }
                    } else if (choice == 0) {
                        System.out.println("Thank you for using Bank Management System!");
                        System.exit(0);
                    } else {
                        System.out.println("Invalid choice! Try Again...");
                    }
                } else {
                    System.out.println("Invalid Choice! Please enter any one number...");
                    input.nextLine();
                }
            }
        }
    }
}
