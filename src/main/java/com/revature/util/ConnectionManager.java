package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager
{
	private static Connection connection = null;
	
	static 
	{		
		try {
			connection = DriverManager.getConnection(System.getenv("DB_URL"),System.getenv("DB_USER"), System.getenv("DB_PASSWORD"));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static Connection getConnection()
	{
		return connection;
	}
}