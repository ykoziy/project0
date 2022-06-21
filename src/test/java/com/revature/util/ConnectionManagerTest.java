package com.revature.util;

import java.sql.Connection;

import org.junit.Test;

import org.junit.Assert;

public class ConnectionManagerTest
{
	@Test
	public void getConnectionShouldReturnValidConnection() 
	{
		Connection con = ConnectionManager.getConnection();
		Assert.assertNotNull(con);
	}
}
