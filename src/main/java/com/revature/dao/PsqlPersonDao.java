package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.revature.enums.UserRole;
import com.revature.models.Address;
import com.revature.models.Person;
import com.revature.util.ConnectionManager;

public class PsqlPersonDao implements PersonDao
{
	@Override
	public Person get(long id) 
	{
		String sql = 
		"SELECT p.id, p.username, p.email, p.pwd, p.first_name, p.last_name, p.phone, p.user_role, a.street, a.city, a.state, a.zip" +
		" FROM person p" +
		" LEFT JOIN address a ON p.id = a.id" +
		" WHERE p.id = ?";
		
		try(Connection conn = ConnectionManager.getConnection())
		{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			String userName = "";
			String email = "";
			String firstName = "";
			String lastName = "";
			String phone = "";
			UserRole role = null;
			
			String street = "";
			String city = "";
			String state = "";
			String zip = "";
			
			long idFromDb = 0L;
			
			while(rs.next()) {
				id = rs.getLong("id");
				userName = rs.getString("username");
				email = rs.getString("email");
				firstName = rs.getString("first_name");
				lastName = rs.getString("last_name");
				phone = rs.getString("phone");
				role = UserRole.valueOf(rs.getString("user_role"));
				
				street = rs.getString("street");
				city = rs.getString("city");
				state = rs.getString("state");
				zip = rs.getString("zip");
			}
			if (idFromDb != 0)
			{
				Address address = new Address(street, city, state, zip);
				return new Person(id, firstName, lastName, userName, email, phone, address, role);		
			}


		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;		
	}

	@Override
	public boolean delete(long id)
	{
		String sql = "DELETE FROM person WHERE id = ?";
		
		try(Connection conn = ConnectionManager.getConnection())
		{
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, id);
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
	public boolean update(Person person)
	{
		String sql = "UPDATE person " + 
		"SET username = ?, email = ?, pwd = ?, first_name = ?, " +
		"last_name = ?, phone = ?, user_role = ?" +
		"WHERE id = ?";
		
		try(Connection conn = ConnectionManager.getConnection())
		{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, person.getUserName());
			pstmt.setString(2, person.getEmail());
			pstmt.setString(3, new String(person.getPassword()));
			pstmt.setString(4, person.getFirstName());
			pstmt.setString(5, person.getLastName());
			pstmt.setString(6, person.getPhoneNumber());
			pstmt.setObject(7, person.getUserRole(), Types.OTHER);			
			
			
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

	@Override
	public long add(Person person)
	{
		String sql = "INSERT INTO person (username, email, pwd, first_name, last_name, phone, user_role)" + 
		" VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING person.id";
		long id = 0L;
		
		try (Connection conn = ConnectionManager.getConnection())
		{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, person.getUserName());
			pstmt.setString(2, person.getEmail());
			pstmt.setString(3, new String(person.getPassword()));
			pstmt.setString(4, person.getFirstName());
			pstmt.setString(5, person.getLastName());
			pstmt.setString(6, person.getPhoneNumber());
			pstmt.setObject(7, person.getUserRole(), Types.OTHER);
			
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
	public Person getByUsername(String username)
	{
		String sql = 
		"SELECT p.id, p.username, p.email, p.pwd, p.first_name, p.last_name, p.phone, p.user_role, a.street, a.city, a.state, a.zip" +
		" FROM person p" +
		" LEFT JOIN address a ON p.id = a.id" +
		" WHERE p.username = ?";
		
		try(Connection conn = ConnectionManager.getConnection())
		{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			
			String userName = "";
			String pwd = "";
			String email = "";
			String firstName = "";
			String lastName = "";
			String phone = "";
			UserRole role = null;
			
			String street = "";
			String city = "";
			String state = "";
			String zip = "";
			long id = 0L;
			
			while(rs.next()) {
				id = rs.getLong("id");
				userName = rs.getString("username");
				pwd = rs.getString("pwd");
				email = rs.getString("email");
				firstName = rs.getString("first_name");
				lastName = rs.getString("last_name");
				phone = rs.getString("phone");
				role = UserRole.valueOf(rs.getString("user_role"));
				
				street = rs.getString("street");
				city = rs.getString("city");
				state = rs.getString("state");
				zip = rs.getString("zip");
			}
			if (id != 0)
			{
				Address address = new Address(street, city, state, zip);
				return new Person(id, firstName, lastName, userName, pwd.toCharArray(), email, phone, address, role);			
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;	
	}

	@Override
	public List<Person> getAll()
	{
		String sql = "SELECT * FROM person p LEFT JOIN address a ON p.id = a.id";
		
		List<Person> users = new ArrayList<>();
		
		try(Connection conn = ConnectionManager.getConnection())
		{
			Statement stmt = conn.createStatement();

			String userName = "";
			String email = "";
			String firstName = "";
			String lastName = "";
			String phone = "";
			UserRole role = null;
			
			String street = "";
			String city = "";
			String state = "";
			String zip = "";
			
			long id = 0L;
			
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) 
			{
				id = rs.getLong("id");
				userName = rs.getString("username");
				email = rs.getString("email");
				firstName = rs.getString("first_name");
				lastName = rs.getString("last_name");
				phone = rs.getString("phone");
				role = UserRole.valueOf(rs.getString("user_role"));
				
				street = rs.getString("street");
				city = rs.getString("city");
				state = rs.getString("state");
				zip = rs.getString("zip");
				
				Address address = new Address(street, city, state, zip);
				Person person =  new Person(id, firstName, lastName, userName, email, phone, address, role);
				
				users.add(person);
			}

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return users;		
	}
}
