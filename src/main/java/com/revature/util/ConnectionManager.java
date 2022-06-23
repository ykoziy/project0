package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager
{
	
	private static Connection conn = null;
	
	private ConnectionManager() {
	}
	
	public static Connection getConnection() {
		try {
			if (conn != null && !conn.isClosed()) {
				return conn;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		try {
			conn = DriverManager.getConnection(System.getenv("DB_URL"), System.getenv("DB_USER"), System.getenv("DB_PASSWORD"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}

}