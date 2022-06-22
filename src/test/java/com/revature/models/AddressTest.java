package com.revature.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class AddressTest
{
	private final String address = "404 Example Rd, New York, NY 10017";
	private Address a;
	
	@Before
	public void resetAddress()
	{
		a = new Address("404 Example Rd", "New York", "NY", "10017");
	}
	
	@Test
	public void toStringShouldReturnValidString()
	{
		Assert.assertEquals(address, a.toString());
	}
}
