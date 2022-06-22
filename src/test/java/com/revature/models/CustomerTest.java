package com.revature.models;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.revature.enums.UserRole;
import com.revature.util.SequenceGenerator;

public class CustomerTest
{
	private Customer testCustomer;
	
	private final String firstName = "John";
	private final String lastName = "Doe";
	private final String userName = "jdoe45";
	private final String email = "jdoe@google.com";
	private final String phoneNumber = "646-911-0101";
	private final Address address = new Address("404 Example Rd", "New York", "NY", "10017");
	
	private String userRole = UserRole.CUSTOMER.toString();
	
	@Before
	public void resetCustomer()
	{
		SequenceGenerator.setCounter(0L);
		testCustomer = new Customer(1, firstName, lastName, userName, email, phoneNumber, address, userRole);
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
		info += "Name: " + firstName + " " + lastName + "\n";
		info += "User Name: " + userName + "\n";
		info += "Email: " + email + "\n";
		info += "Address: " + address + "\n";
		info += "Phone Number: " + phoneNumber + "\n";
		info += "=======================================================\n";
		
		System.out.println(testCustomer.getCustomerInfo());
		
		System.out.println(info);
		
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
