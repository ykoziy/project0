package com.revature.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.revature.util.SequenceGenerator;

public class BankTest
{
	private Bank testBank;
	private final String firstName = "John";
	private final String lastName = "Doe";
	private final String userName = "jdoe45";
	private final String email = "jdoe@google.com";
	private final String phoneNumber = "646-911-0101";
	private final String address = "404 Example Rd, New York, NY 10017";
	
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
		Person testCustomer = new Customer(firstName, lastName, userName, email, phoneNumber, address);
		
		testBank.addUser(testCustomer);
		
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
		Person testCustomer = new Customer(firstName, lastName, userName, email, phoneNumber, address);
		
		testBank.addUser(testCustomer);
		
		
		Person testCustomer2 = new Customer("joe", lastName, "jdoe55", "joey@yahoo.com", "646-911-0209", "403 Example Rd, New York, NY 10017");
		
		testBank.addUser(testCustomer2);
		
		testBank.removeUser("jdoe45");
		
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
