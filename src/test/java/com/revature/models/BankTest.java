package com.revature.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.revature.util.SequenceGenerator;

public class BankTest
{
	private Bank testBank;
	
	@Before
	public void before()
	{
		testBank = new Bank();
		SequenceGenerator.setCounter(0L);
	}
	
	@Before
	public void reset()
	{
		testBank = new Bank();
		SequenceGenerator.setCounter(0L);
	}
	
	@Test
	public void bankShouldBeAbleToAddAUser()
	{
		Customer testCustomer = new Customer("John", "Doe", "jdoe45", "646-911-0101", "404 Example Rd, New York, NY 10017");
		
		testBank.addCustomer(testCustomer);
		
		Person u = testBank.getUser("jdoe45");
		
		Assert.assertEquals("jdoe45", u.getUserName());
	}
	
	@Test
	public void bankShouldBeAbleToAddAnAccount()
	{
		Account acc = new Account();
		
		testBank.addAccount(acc);
		
		String accountNum = "00000000000000001";
		
		Account a = testBank.getAccount(accountNum);
		
		Assert.assertEquals(accountNum, a.getNumber());
	}
	
	@Test
	public void bankShouldBeAbleToRemoveUser()
	{
		Customer testCustomer = new Customer("John", "Doe", "jdoe45", "646-911-0101", "404 Example Rd, New York, NY 10017");
		
		testBank.addCustomer(testCustomer);
		
		Customer testCustomer2 = new Customer("Joe", "Doe", "jdoe55", "646-911-0209", "403 Example Rd, New York, NY 10017");
		
		testBank.addCustomer(testCustomer2);
		
		testBank.removeCustomer("jdoe45");
		
		Assert.assertNull(testBank.getUser("jdoe45"));
		Assert.assertEquals("jdoe55", testBank.getUser("jdoe55").getUserName());
	}
	
	@Test
	public void bankShouldBeAbleToRemoveAccount()
	{
		Account acc = new Account();
		Account acc2 = new Account();
		
		testBank.addAccount(acc);
		testBank.addAccount(acc2);
		
		String accountNum = "00000000000000001";
		String accountNum2 = "00000000000000002";
		
		testBank.removeAccount(accountNum2);
		
		Assert.assertNull(testBank.getAccount(accountNum2));
		Assert.assertEquals(accountNum, testBank.getAccount(accountNum).getNumber());
	}
}
