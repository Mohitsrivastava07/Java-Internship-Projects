package com.bankmanagement.dao;

import com.bankmanagement.entities.CreateAccount;
import com.bankmanagement.entities.Deposits;
import com.bankmanagement.entities.TransactionHistory;
import com.bankmanagement.entities.Withdrawals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

public class BankDaoImpl implements BankDao {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private PlatformTransactionManager transactionManager;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public PlatformTransactionManager getTransactionManager() {
        return transactionManager;
    }
    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Override
    public int createAccount(CreateAccount bank) {
        //insertion of createAccount
        String sqlQuery = "insert into createaccount(account_number, name, age, mobile_number, address, account_type, aadhaar_number, balance)values(?,?,?,?,?,?,?,?)";
        int result = this.jdbcTemplate.update(sqlQuery, bank.getAccountNumber(), bank.getName(), bank.getAge(), bank.getMobileNumber(), bank.getAddress(), bank.getAccountType(), bank.getAadhaarNumber(), bank.getBalance());
        return result;
    }
    @Override
    public int deposits(Deposits deposit) {
        // Step 1: Check if account exists in createaccount
        String checkQuery = "SELECT COUNT(*) FROM createaccount WHERE account_number = ?";
        int count = this.jdbcTemplate.queryForObject(checkQuery, Integer.class, deposit.getAccountNumber());

        if (count > 0) {
            // Step 2: Insert into deposits (trigger auto-generates deposit_id)
            String insertQuery = "INSERT INTO deposits (account_number, deposits_amount) VALUES (?, ?)";
            int result = this.jdbcTemplate.update(insertQuery,
                    deposit.getAccountNumber(),
                    deposit.getDepositsAmount());

            // Step 3: Update balance in createaccount
            if (result > 0) {
                String updateQuery = "UPDATE createaccount SET balance = balance + ? WHERE account_number = ?";
                this.jdbcTemplate.update(updateQuery,
                        deposit.getDepositsAmount(),
                        deposit.getAccountNumber());
            }
            return result;
        } else {
            // Account not found
            return 0;
        }
    }

    @Override
    public int withdrawals(Withdrawals withdrawal) {
        // Step 1: Check if account exists AND get current balance
        String checkQuery = "SELECT balance FROM createaccount WHERE account_number = ?";

        List<Double> results = this.jdbcTemplate.query(checkQuery,
                (rs, rowNum) -> rs.getDouble("balance"),
                withdrawal.getAccountNumber());

        if (!results.isEmpty()) {
            double currentBalance = results.get(0);

            // Step 2: Check sufficient balance
            if (currentBalance < withdrawal.getWithdrawalAmount()) {
                return -1; // Insufficient funds
            }

            // Step 3: Insert into WITHDRAWALS table
            String insertQuery = "INSERT INTO withdrawals (account_number, withdrawal_amount) VALUES (?, ?)";
            int result = this.jdbcTemplate.update(insertQuery,
                    withdrawal.getAccountNumber(),
                    withdrawal.getWithdrawalAmount());

            // Step 4: SUBTRACT balance (not add)
            if (result > 0) {
                String updateQuery = "UPDATE createaccount SET balance = balance - ? WHERE account_number = ?";
                this.jdbcTemplate.update(updateQuery,
                        withdrawal.getWithdrawalAmount(),
                        withdrawal.getAccountNumber());
            }
            return result;
        } else {
            return 0; // Account not found
        }
    }

    @Override
    public int transactionHistory(TransactionHistory transactionHistory) {
        String checkAccountQuery = "SELECT COUNT(*) FROM createaccount WHERE account_number = ?";
        String debitQuery        = "UPDATE createaccount SET balance = balance - ? WHERE account_number = ?";
        String creditQuery       = "UPDATE createaccount SET balance = balance + ? WHERE account_number = ?";

        String insertHistoryQuery =
                "INSERT INTO transactionhistory (transaction_id, account_number, other_account_number, transaction_type, transaction_amount) " +
                        "VALUES (?, ?, ?, ?, ?)";

        // Step 1: Check if otherAccountNumber exists in createaccount table
        Integer count = jdbcTemplate.queryForObject(
                checkAccountQuery, Integer.class,
                transactionHistory.getOtherAccountNumber()
        );

        if (count == null || count == 0) {
            System.out.println("Receiver account not found!");
            return 0; // Stop here if account doesn't exist
        }

        // Step 2: Execute debit, credit & insert atomically
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);

        return transactionTemplate.execute(status -> {
            try {
                // Debit from YOUR account (sender)
                jdbcTemplate.update(debitQuery,
                        transactionHistory.getTransactionAmount(),
                        transactionHistory.getAccountNumber()
                );

                // Credit to OTHER account (receiver)
                jdbcTemplate.update(creditQuery,
                        transactionHistory.getTransactionAmount(),
                        transactionHistory.getOtherAccountNumber()
                );

                // Insert record into transactionhistory table
                jdbcTemplate.update(insertHistoryQuery,
                        transactionHistory.getTransactionId(),
                        transactionHistory.getAccountNumber(),
                        transactionHistory.getOtherAccountNumber(),
                        transactionHistory.getTransactionType(),
                        transactionHistory.getTransactionAmount()
                );

                System.out.println("Transaction Successful!");
                return 1; // Success

            } catch (Exception e) {
                status.setRollbackOnly();
                e.printStackTrace();
                return -1; // Failure
            }
        });
    }

    @Override
    public CreateAccount viewAccountDetails(long accountNumber) {
        String sqlQuery = "SELECT * FROM createaccount WHERE account_number = ?";

        try {
            AccountMapperImpl accountMapper = new AccountMapperImpl();
            return (CreateAccount) jdbcTemplate.queryForObject(sqlQuery, accountMapper, accountNumber);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Deposits viewDepositsDetails(long accountNumber) {
        String sqlQuery = "select * from deposits WHERE account_number = ?";

        try {
            DepositMapperImpl depositMapper = new DepositMapperImpl();
            return (Deposits) jdbcTemplate.queryForObject(sqlQuery, depositMapper, accountNumber);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Withdrawals viewWithdrawalsDetails(long accountNumber) {
        String sqlQuery = "select * from withdrawals WHERE account_number = ?";

        try {
            WithdrawalMapperImpl withdrawalMapper = new WithdrawalMapperImpl();
            return (Withdrawals) jdbcTemplate.queryForObject(sqlQuery, withdrawalMapper, accountNumber);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public TransactionHistory viewTransactionDetails(long accountNumber) {
        String sqlQuery = "select * from transactionhistory WHERE account_number = ?";

        try {
            TransactionMapperImpl transactionMapper = new TransactionMapperImpl();
            return (TransactionHistory) jdbcTemplate.queryForObject(sqlQuery, transactionMapper, accountNumber);
        } catch (Exception e) {
            return null;
        }
    }
}
