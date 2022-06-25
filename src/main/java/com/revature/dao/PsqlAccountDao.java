package com.revature.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.revature.enums.Status;
import com.revature.models.Account;
import com.revature.util.ConnectionManager;

public class PsqlAccountDao implements AccountDao
{
	@Override
	public Account get(long id)
	{
		String sql = "SELECT * FROM account WHERE id = ?";
		try (Connection conn = ConnectionManager.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			long user_id = 0;
			long ownerId = 0;
			long balance = 0;
			Status status = null;

			while (rs.next()) {
				user_id = rs.getLong("id");
				ownerId = rs.getLong("user_id");
				balance = rs.getLong("balance");
				status = Status.valueOf(rs.getString("status"));
				
			}
			return new Account(user_id, ownerId, balance, status);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Account> getUserAccounts(long id)
	{
		List<Account> accList = new ArrayList<>();
		String sql = "SELECT a.id, a.user_id, a.balance, a.status " +
				"FROM account_holder ah " +
				"LEFT JOIN account a ON ah.account_id = a.id " +
				"WHERE ah.user_id = ?";
		try (Connection conn = ConnectionManager.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				long user_id = rs.getLong("id");
				long ownerId = rs.getLong("user_id");
				long balance = rs.getLong("balance");
				Status status = Status.valueOf(rs.getString("status"));
				
				Account a = new Account(user_id, ownerId, balance, status);
				
				accList.add(a);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean delete(Account account)
	{
		String sql = "DELETE FROM account WHERE id = ?";
		
		try (Connection conn = ConnectionManager.getConnection())
		{
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, account.getId());
			int result  = stmt.executeUpdate();
			if (result != 0) {
				return true;
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Account account)
	{
		String sql = "UPDATE account " +
				"SET user_id = ?, balance = ?, status = ? " +
				"WHERE id = ?";
		try(Connection conn = ConnectionManager.getConnection())
		{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, account.getOwnerId());
			pstmt.setLong(2, account.getBalance());
			pstmt.setObject(3, account.getStatus(), Types.OTHER);
			pstmt.setLong(4, account.getId());
			
			int rowsAffected = pstmt.executeUpdate();
			
			if (rowsAffected != 0) {
				return true;
			}
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public long add(long user_id, Account account)
	{
		String sql = "INSERT INTO account (user_id, balance, status)" + 
		" VALUES (?, ?, ?) RETURNING account.id;";
		long id = 0L;
		
		try (Connection conn = ConnectionManager.getConnection())
		{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, account.getOwnerId());
			pstmt.setLong(2, account.getBalance());
			pstmt.setObject(3, account.getStatus(), Types.OTHER);
			
			ResultSet rs = pstmt.executeQuery();
			
			//get primary key
			if (rs != null) {
				
				rs.next();
				id = rs.getInt(1); 
				return id;
			}			
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public List<Account> getAll()
	{
		String sql = "SELECT * FROM account";
		List<Account> accList = new ArrayList<>();
		
		try (Connection conn = ConnectionManager.getConnection())
		{
			Statement stmt = conn.createStatement();

			long user_id = 0;
			long ownerId = 0;
			long balance = 0;
			Status status = null;
			
			ResultSet rs = stmt.executeQuery(sql);			
			while (rs.next())
			{
				user_id = rs.getLong("id");
				ownerId = rs.getLong("user_id");
				balance = rs.getLong("balance");
				status = Status.valueOf(rs.getString("status"));				
				Account a = new Account(user_id, ownerId, balance, status);		
				accList.add(a);				
			}
			return accList;
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return null;
	}
}
