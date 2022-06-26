package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.revature.models.Address;
import com.revature.util.ConnectionManager;

public class PsqlAddressDao implements AddressDao
{

	@Override
	public long addPersonAddress(Address address, long personId)
	{
		String sql = "INSERT INTO address (user_id, street, city, state, zip)" + 
		" VALUES (?, ?, ?, ?, ?) RETURNING address.id";
		long id = 0L;
		
		try (Connection conn = ConnectionManager.getConnection())
		{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, personId);
			pstmt.setString(2, address.getStreet());
			pstmt.setString(3, address.getCity());
			pstmt.setString(4, address.getState());
			pstmt.setString(5, address.getZip());
			
			ResultSet rs = pstmt.executeQuery();
			
			//get primary key
			if (rs.next()) 
			{
				id = rs.getInt(1); 
				return id;
			}			
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public Address getPersonAddress(long personId)
	{
		String sql = "SELECT id, user_id, street, city, state, zip " +
					 "FROM address WHERE user_id = ?";
		try (Connection conn = ConnectionManager.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, personId);
			
			ResultSet rs = pstmt.executeQuery();
			
			String street = "";
			String city = "";
			String state = "";
			String zip = "";
			
			long id = 0L;

			while (rs.next()) {
				id = rs.getLong("id");
				street = rs.getString("street");
				city = rs.getString("city");
				state = rs.getString("state");
				zip = rs.getString("zip");
				
			}
			return new Address(id, id, street, city, state, zip);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean update(Address address, long personId)
	{
		String sql = "UPDATE address " + 
		"SET user_id = ?, street = ?, city = ?," +
		"state = ?, zip = ?" +
		"WHERE id = ?";
		
		try(Connection conn = ConnectionManager.getConnection())
		{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, personId);
			pstmt.setString(2, address.getStreet());
			pstmt.setString(3, address.getCity());
			pstmt.setString(4, address.getState());
			pstmt.setString(5, address.getZip());
			pstmt.setLong(6, address.getId());		
			
			int rowsAffected = pstmt.executeUpdate();
			
			if (rowsAffected != 0) {
				return true;
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}

}
