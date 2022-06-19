package com.revature.models;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.revature.util.SequenceGenerator;

public class CustomerTest
{
	private Customer testCustomer;
	
	@Before
	public void resetCustomer()
	{
		SequenceGenerator.setCounter(0L);
		testCustomer = new Customer("John", "Doe", "jdoe45", "646-911-0101", "404 Example Rd, New York, NY 10017");
		Map<String, Account> accounts = new HashMap<>();
		Account acc1 = new Account();
		Account acc2 = new Account();
		
		accounts.put(acc1.getNumber(), acc1);
		accounts.put(acc2.getNumber(), acc1);
		
		testCustomer.setAccounts(accounts);
	}
	
	@Test
	public void customerShouldBeAbleToDebitAccount()
	{	
		String accountNum = "00000000000000001";
		testCustomer.debitAccount(accountNum, 50.05);
		
		Assert.assertEquals(50.05, testCustomer.getAccountBalance(accountNum), 0.1);
	}
	
	@Test
	public void customerShouldNotBeAbleToDebitnegativeAmount()
	{
		String accountNum = "00000000000000001";
		testCustomer.debitAccount(accountNum, -50.05);
		
		Assert.assertNotEquals(-50.05, testCustomer.getAccountBalance(accountNum), 0.1);		
	}
	
	@Test
	public void customerShouldBeAbleToCreditAccount()
	{
		String accountNum = "00000000000000001";
		testCustomer.debitAccount(accountNum, 100.01);
		
		testCustomer.creditAccount(accountNum, 45.35);
		
		Assert.assertEquals(54.66, testCustomer.getAccountBalance(accountNum), 0.1);
	}
	
	@Test
	public void customerShouldNotBeAbleToOverdraft()
	{
		String accountNum = "00000000000000001";
		testCustomer.debitAccount(accountNum, 100.01);
		
		testCustomer.creditAccount(accountNum, 1000000);
		
		Assert.assertEquals(100.01, testCustomer.getAccountBalance(accountNum), 0.1);		
	}
	
	@Test
	public void getCustomerInfoShouldReturnCustomerInfo()
	{
		String info = "\n=======================================================\n";
		info += "Name: John Doe\n";
		info += "User Name: jdoe45\n";
		info += "Address: 404 Example Rd, New York, NY 10017\n";
		info += "Phone Number: 646-911-0101\n";
		info += "=======================================================\n";
		
		Assert.assertEquals(info, testCustomer.getCustomerInfo());
	}
	
	@Test
	public void getAccountInfoShouldReturnAccountInfo()
	{
		String accountNum = "00000000000000001";
		String info = "\n=======================================================\n";
		info += "Account Number: 00000000000000001\n";
		info += "Account Balance: $1,032.45\n";
		info += "=======================================================\n";
		
		testCustomer.debitAccount(accountNum, 1032.45);
		
		Assert.assertEquals(info, testCustomer.getAccountInfo(accountNum));
	}
}
