package com.bankmanagement.dao;

import com.bankmanagement.entities.CreateAccount;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountMapperImpl implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        CreateAccount account = new CreateAccount();
        account.setAccountNumber(rs.getLong(1));
        account.setName(rs.getString(2));
        account.setAge(rs.getInt(3));
        account.setMobileNumber(rs.getString(4));
        account.setAddress(rs.getString(5));
        account.setAccountType(rs.getString(6));
        account.setAadhaarNumber(rs.getString(7));
        account.setBalance(rs.getDouble(8));
        return account;
    }
}
