package com.revature.util;

import java.sql.Connection;

import org.junit.Test;

import org.junit.Assert;
import org.junit.Ignore;

public class ConnectionManagerTest
{
	@Ignore
	@Test
	public void getConnectionShouldReturnValidConnection() 
	{
		Connection con = ConnectionManager.getConnection();
		Assert.assertNotNull(con);
	}
}
