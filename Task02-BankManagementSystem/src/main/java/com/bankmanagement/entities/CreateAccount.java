package com.bankmanagement.entities;

import java.util.Scanner;

import com.bankmanagement.customeexception.*;

public class CreateAccount {
    enum AccountType {
        saving, current
    }

    private long accountNumber;
    private String name;
    private int age;
    private String mobileNumber;
    private String address;
    private String accountType;
    private String aadhaarNumber;
    private double balance;

    public CreateAccount() {
        super();
    }
    public CreateAccount(long accountNumber, String name, int age, String mobileNumber, String address, String accountType, String aadhaarNumber, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.age = age;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.accountType = accountType;
        this.aadhaarNumber = aadhaarNumber;
        this.balance = balance;
    }

    public long getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getAccountType() {
        return accountType;
    }
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAadhaarNumber() {
        return aadhaarNumber;
    }
    public void setAadhaarNumber(String aadhaarNumber) {
        this.aadhaarNumber = aadhaarNumber;
    }

    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public static CreateAccount createAccountInput() {
        Scanner input = new Scanner(System.in);
        CreateAccount bank = new CreateAccount();

        String accountNumber;
        String name, mobileNumber, address, accountType, aadhaarNumber;
        int age;
        double balances;

        System.out.println("\n======CREATE CUSTOMER ACCOUNT=======");
        while (true) {
            try {
                System.out.print("Enter account number: ");
                accountNumber = input.nextLine();
                if (accountNumber.length() != 14 || accountNumber.isEmpty()) {
                    throw new InvalidAccountNumberException("Invalid Account Number! Account Number must be length exactly 14...");
                } else {
                    while (true) {
                        try {
                            System.out.print("Enter account holder name: ");
                            name = input.nextLine();
                            if ((name.matches(".*\\d.*")) || (name.isEmpty()) || (!name.matches("[a-zA-Z ]+"))) {
                                throw new InvalidAccountHolderNameException("Invalid Account Holder Name! Account holder name does not contains empty input, any number and any special character...");
                            } else {
                                while (true) {
                                    System.out.print("Enter account holder age: ");
                                    if (input.hasNextInt()) {
                                        age = input.nextInt();
                                        input.nextLine();

                                        while (true) {
                                            try {
                                                System.out.print("Enter account holder mobile number: ");
                                                mobileNumber = input.nextLine();
                                                if (!(mobileNumber.matches("[0-9]+")) || (mobileNumber.length() != 10) || (mobileNumber.isEmpty())) {
                                                    throw new InvalidMobileNumberException("Invalid Account Holder Mobile Number! Mobile number must be length of 10 not followed by characters value only number...");
                                                } else {
                                                    while (true) {
                                                        try {
                                                            System.out.print("Enter account holder address: ");
                                                            address = input.nextLine();
                                                            if (address.isEmpty() || !address.matches("[a-zA-Z0-9 ]+")) {
                                                                throw new InvalidAddressException("Invalid Address! Address must be only alphabetic(String) within number but not include any special character...");
                                                            } else {
                                                                while (true) {
                                                                    System.out.print("Enter account type: ");
                                                                    accountType = input.nextLine();
                                                                    try {
                                                                        AccountType accountType1 = AccountType.valueOf(accountType);

                                                                        while (true) {
                                                                            try {
                                                                                System.out.print("Enter account holder aadhaar number: ");
                                                                                aadhaarNumber = input.nextLine();
                                                                                if (aadhaarNumber.length() != 12 || aadhaarNumber.isEmpty()) {
                                                                                    throw new InvalidAadhaarNumberException("Invalid Aadhaar Number! Aadhaar Number must be length of 14...");
                                                                                } else {
                                                                                    while (true) {
                                                                                        try {
                                                                                            System.out.print("Enter your amount: ");
                                                                                            String input_balances = input.nextLine().trim();
                                                                                            if (input_balances.isEmpty()) {
                                                                                                balances = 0.0;
                                                                                            } else {
                                                                                                balances = Double.parseDouble(input_balances);
                                                                                                if (balances < 0) {
                                                                                                    throw new InvalidAmountNegativeException("Invalid Amount! Amount cannot be negative!");
                                                                                                } else {
                                                                                                    System.out.println();
                                                                                                    System.out.println("Balance set to: " + balances);
                                                                                                    break;
                                                                                                }
                                                                                            }
                                                                                            break;
                                                                                        } catch (InvalidAmountNegativeException e) {
                                                                                            System.out.println(e.getMessage());
                                                                                        }
                                                                                    }
                                                                                    break;
                                                                                }
                                                                            } catch (InvalidAadhaarNumberException e) {
                                                                                System.out.println(e.getMessage());
                                                                            }
                                                                        }
                                                                        break;
                                                                    } catch (Exception e) {
                                                                        System.out.println(e.getMessage());
                                                                    }
                                                                }
                                                                break;
                                                            }
                                                        } catch (InvalidAddressException e) {
                                                            System.out.println(e.getMessage());
                                                        }
                                                    }
                                                    break;
                                                }
                                            } catch (InvalidMobileNumberException e) {
                                                System.out.println(e.getMessage());
                                            }
                                        }
                                        break;
                                    } else {
                                        System.out.println("Invalid account holder age! Age must be in number not string...");
                                        input.nextLine();
                                    }
                                }
                                break;
                            }
                        } catch (InvalidAccountHolderNameException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                }
            } catch (InvalidAccountNumberException e) {
                System.out.println(e.getMessage());
            }
        }

        bank.setAccountNumber(Long.parseLong(accountNumber));
        bank.setName(name);
        bank.setAge(age);
        bank.setMobileNumber(mobileNumber);
        bank.setAddress(address);
        bank.setAccountType(accountType);
        bank.setAadhaarNumber(aadhaarNumber);
        bank.setBalance(balances);

        return bank;
    }
}
