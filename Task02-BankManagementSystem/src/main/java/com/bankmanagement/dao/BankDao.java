package com.bankmanagement.dao;

import com.bankmanagement.entities.CreateAccount;
import com.bankmanagement.entities.Deposits;
import com.bankmanagement.entities.TransactionHistory;
import com.bankmanagement.entities.Withdrawals;

public interface BankDao {
    public int createAccount(CreateAccount bank);
    public int deposits(Deposits deposit);
    public int withdrawals(Withdrawals withdrawal);
    public int transactionHistory(TransactionHistory transactionHistory);
    public CreateAccount viewAccountDetails(long accountNumber);
    public Deposits viewDepositsDetails(long accountNumber);
    public Withdrawals viewWithdrawalsDetails(long accountNumber);
    public TransactionHistory viewTransactionDetails(long accountNumber);
}
