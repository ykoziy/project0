package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import com.revature.util.ConnectionManager;

public class PsqlAccountHolderDao implements AccountHolderDao
{
	@Override
	public boolean delete(long userId, long accountId)
	{
		String sql = "DELETE FROM account_holder WHERE user_id = ? AND account_id = ?";
		boolean result = false;
		if (userId < 1 || accountId < 1)
		{
			return result;
		}
		try (Connection conn = ConnectionManager.getConnection())
		{
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, userId);
			stmt.setLong(2, accountId);
			
			int res  = stmt.executeUpdate();
			if (res != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean add(long userId, long accountId)
	{
		String callStr = "{? = call add_account_holder( ?, ?)}";
		boolean result = false;
		if (userId < 1 || accountId < 1)
		{
			return result;
		}
		try (Connection conn = ConnectionManager.getConnection())
		{
			CallableStatement addAccountHolder = conn.prepareCall(callStr);
			addAccountHolder.registerOutParameter(1, Types.BOOLEAN);
			addAccountHolder.setLong(2, userId);
			addAccountHolder.setLong(3, accountId);
			addAccountHolder.execute();
			result = addAccountHolder.getBoolean(1);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return result;
	}
}
