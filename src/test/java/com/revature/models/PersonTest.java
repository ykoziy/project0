package com.revature.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
	private final String phoneNumber = "6469110101";
	private final Address address = new Address("404 Example Rd", "New York", "NY", "10017");
	
	private UserRole userRole = UserRole.customer;
	
	@Before
	public void resetCustomer()
	{
		testCustomer = new Person(1, firstName, lastName, userName, email, phoneNumber, address, userRole);
		List<Account> accounts = new ArrayList<>();
		Account acc1 = new Account(1, 1, 10004, Status.active);
		Account acc2 = new Account(2, 1, 103245, Status.active);
		
		accounts.add(acc1);
		accounts.add(acc2);
		
		testCustomer.setAccounts(accounts);
	}
		
	@Test
	public void getCustomerInfoShouldReturnCustomerInfo()
	{
		String prettyPhone = "(646)-911-0101";
		String info = "\n=======================================================\n";
		info += "Name: " + firstName + " " + lastName + "\n";
		info += "User Name: " + userName + "\n";
		info += "Email: " + email + "\n";
		info += "Address: " + address + "\n";
		info += "Phone Number: " + prettyPhone + "\n";
		info += "=======================================================\n";
		
		
		Assert.assertEquals(info, testCustomer.getInfo());
	}
	
	@Test
	public void getAccountInfoShouldReturnAccountInfo()
	{
		String info = "\n=======================================================\n";
		info += "Account Number: 00000000000000002\n";
		info += "Account Balance: $1,032.45\n";
		info += "=======================================================\n";
		
		Assert.assertEquals(info, testCustomer.getAccountInfo(1));
	}
}
