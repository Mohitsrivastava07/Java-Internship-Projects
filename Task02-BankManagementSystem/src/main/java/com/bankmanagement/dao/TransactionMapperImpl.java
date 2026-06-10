package com.bankmanagement.dao;

import com.bankmanagement.entities.TransactionHistory;
import com.bankmanagement.entities.Withdrawals;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionMapperImpl implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        TransactionHistory transaction = new TransactionHistory();
        transaction.setTransactionId(rs.getString(1));
        transaction.setAccountNumber(rs.getLong(2));
        transaction.setOtherAccountNumber(rs.getLong(3));
        transaction.setTransactionType(rs.getString(4));
        transaction.setTransactionAmount(rs.getDouble(5));
        return transaction;
    }
}
