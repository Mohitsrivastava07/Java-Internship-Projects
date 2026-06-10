package com.bankmanagement.dao;

import com.bankmanagement.entities.Withdrawals;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WithdrawalMapperImpl implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Withdrawals withdrawals = new Withdrawals();
        withdrawals.setWithdrawalId(rs.getString(1));
        withdrawals.setAccountNumber(rs.getLong(2));
        withdrawals.setWithdrawalAmount(rs.getDouble(3));
        return withdrawals;
    }
}
