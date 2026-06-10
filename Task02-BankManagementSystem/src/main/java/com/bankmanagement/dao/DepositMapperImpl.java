package com.bankmanagement.dao;

import com.bankmanagement.entities.CreateAccount;
import com.bankmanagement.entities.Deposits;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepositMapperImpl implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Deposits deposit = new Deposits();
        deposit.setDepositId(rs.getString(1));
        deposit.setAccountNumber(rs.getLong(2));
        deposit.setDepositsAmount(rs.getDouble(3));
        return deposit;
    }
}
