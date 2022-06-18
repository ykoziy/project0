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
		Customer client = new Customer();
		SequenceGenerator.setCounter(0L);
	}
	
	@Test
	public void customerShouldBeAbleToDebitAccount()
	{
		Customer client = new Customer();
		
		client.debitAccount(50.05);
		
		Assert.assertEquals(50.05, client.getAccountBalance(), 0.1);
	}
	
	@Test
	public void customerShouldNotBeAbleToDebitnegativeAmount()
	{
		Customer client = new Customer();
		
		client.debitAccount(-50.05);
		
		Assert.assertNotEquals(-50.05, client.getAccountBalance(), 0.1);		
	}
	
	@Test
	public void customerShouldBeAbleToCreditAccount()
	{
		Customer client = new Customer();
		
		client.debitAccount(100.01);
		
		client.creditAccount(45.35);
		
		Assert.assertEquals(54.66, client.getAccountBalance(), 0.1);
	}
	
	@Test
	public void customerShouldNotBeAbleToOverdraft()
	{
		Customer client = new Customer();
		
		client.debitAccount(100.01);
		
		client.creditAccount(1000000);
		
		Assert.assertEquals(100.01, client.getAccountBalance(), 0.1);		
	}
}
