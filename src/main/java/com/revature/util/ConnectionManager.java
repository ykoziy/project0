package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager
{
	private static Connection connection = null;
	
	static 
	{
		final String url = "jdbc:postgresql://localhost:5432/bank";
		final String user = "postgres";
		// NEVER STORE PASSWORD IN CLASS, VISIBLE ON GITHUB
		final String password = "postgres";
		
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static Connection getConnection()
	{
		return connection;
	}
}
