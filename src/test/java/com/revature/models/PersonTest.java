package com.revature.models;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.revature.enums.Status;
import com.revature.enums.UserRole;

public class PersonTest
{
	private Person testCustomer;
	
	private final String firstName = "John";
	private final String lastName = "Doe";
	private final String userName = "jdoe45";
	private final String email = "jdoe@google.com";
	private final String phoneNumber = "646-911-0101";
	private final Address address = new Address("404 Example Rd", "New York", "NY", "10017");
	
	private UserRole userRole = UserRole.customer;
	
	@Before
	public void resetCustomer()
	{
		testCustomer = new Person(1, firstName, lastName, userName, email, phoneNumber, address, userRole);
		Map<String, Account> accounts = new HashMap<>();
		Account acc1 = new Account(1, 1, 10004, Status.active);
		Account acc2 = new Account(2, 1, 103245, Status.active);
		
		accounts.put(acc1.getAccountNumber(), acc1);
		accounts.put(acc2.getAccountNumber(), acc1);
		
		testCustomer.setAccounts(accounts);
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
		
		System.out.println(testCustomer.getInfo());
		
		System.out.println(info);
		
		Assert.assertEquals(info, testCustomer.getInfo());
	}
	
	@Test
	public void getAccountInfoShouldReturnAccountInfo()
	{
		String accountNum = "00000000000000001";
		String info = "\n=======================================================\n";
		info += "Account Number: 00000000000000001\n";
		info += "Account Balance: $100.04\n";
		info += "=======================================================\n";
		
		System.out.println(testCustomer.getAccountInfo(accountNum));
		
		Assert.assertEquals(info, testCustomer.getAccountInfo(accountNum));
	}
}
