package com.revature.models;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.revature.enums.Status;

public class AccountTest
{
	private Account testAccount;
	
	@Before
	public void setUpAccount()
	{
		testAccount = new Account(1, 1, new BigDecimal(100.04), Status.active);
	}
	
	@Test
	public void shouldGenerateValidAccountNumber()
	{
		Assert.assertEquals("00000000000000001", testAccount.getAccountNumber());	
	}	
}