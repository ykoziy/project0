package com.revature.models;

import org.junit.Assert;
import org.junit.Test;

public class AccountTest
{

	@Test
	public void depositShouldIncreateBalance()
	{
		Account acc = new Account();
		
		acc.deposit(100.18);
		
		Assert.assertEquals(100.18, acc.getBalance(), 0.1);
	}
}