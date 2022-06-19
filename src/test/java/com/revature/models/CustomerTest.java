package com.revature.models;

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
		testCustomer = new Customer();
		SequenceGenerator.setCounter(0L);
	}
	
	@Test
	public void customerShouldBeAbleToDebitAccount()
	{	
		testCustomer.debitAccount(50.05);
		
		Assert.assertEquals(50.05, testCustomer.getAccountBalance(), 0.1);
	}
	
	@Test
	public void customerShouldNotBeAbleToDebitnegativeAmount()
	{
		testCustomer.debitAccount(-50.05);
		
		Assert.assertNotEquals(-50.05, testCustomer.getAccountBalance(), 0.1);		
	}
	
	@Test
	public void customerShouldBeAbleToCreditAccount()
	{
		testCustomer.debitAccount(100.01);
		
		testCustomer.creditAccount(45.35);
		
		Assert.assertEquals(54.66, testCustomer.getAccountBalance(), 0.1);
	}
	
	@Test
	public void customerShouldNotBeAbleToOverdraft()
	{
		testCustomer.debitAccount(100.01);
		
		testCustomer.creditAccount(1000000);
		
		Assert.assertEquals(100.01, testCustomer.getAccountBalance(), 0.1);		
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
		
		testCustomer = new Customer("John", "Doe", "jdoe45", "646-911-0101", "404 Example Rd, New York, NY 10017");
		
		Assert.assertEquals(info, testCustomer.getCustomerInfo());
	}
	
	@Test
	public void getAccountInfoShouldReturnAccountInfo()
	{
		String info = "\n=======================================================\n";
		info += "Account Number: 00000000000000001\n";
		info += "Account Balance: $1,032.45\n";
		info += "=======================================================\n";
		
		testCustomer = new Customer("John", "Doe", "jdoe45", "646-911-0101", "404 Example Rd, New York, NY 10017");
		testCustomer.debitAccount(1032.45);
		
		Assert.assertEquals(info, testCustomer.getAccountInfo());
	}
}
