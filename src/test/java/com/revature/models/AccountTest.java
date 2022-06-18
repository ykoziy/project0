package com.revature.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AccountTest
{
	private Account testAccount;
	
	@Before
	public void setUpAccount()
	{
		testAccount = new Account();
	}

	@Test
	public void depositShouldIncreateBalance()
	{
		testAccount.deposit(100.18);
		
		Assert.assertEquals(100.18, testAccount.getBalance(), 0.1);
	}
	
	@Test
	public void withdrawShouldDecreaseBalance()
	{
		
		// if we use double for balance 2.15 i 1.10 would return 1.0499999999999998
		// for banking application we want exact calculations
		testAccount.deposit(2.15);
		
		testAccount.withdraw(1.10);
		
		Assert.assertEquals(1.05, testAccount.getBalance(), 0.1);
	}
}