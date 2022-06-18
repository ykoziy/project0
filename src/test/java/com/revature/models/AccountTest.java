package com.revature.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.revature.util.SequenceGenerator;

public class AccountTest
{
	private Account testAccount;
	
	@Before
	public void setUpAccount()
	{
		testAccount = new Account();
		SequenceGenerator.setCounter(0);
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
	
	@Test
	public void creatingAccountShouldGenerateValidAccountNumber()
	{
		// valid account number:
		// 17 long, contains only digits, in needed padded with zeros
		
		Account newAccount = new Account();
		
		Assert.assertEquals("00000000000000001", newAccount.getNumber());
		
	}
	
	@Test
	public void creatingThreeAcountShouldGenerateCorrectAccountNumber()
	{
		Account acc1 = new Account();
		Account acc2 = new Account();
		Account acc3 = new Account();
		
		Assert.assertEquals("00000000000000001", acc1.getNumber());
		Assert.assertEquals("00000000000000002", acc2.getNumber());
		Assert.assertEquals("00000000000000003", acc3.getNumber());
	}
		
}