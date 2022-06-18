package com.revature.models;

public class Customer extends User
{
	private Account account;

	public Customer()
	{
		account = new Account();
	}

	public double getAccountBalance()
	{
		return account.getBalance();
	}
	
	public void debitAccount(double amount)
	{
		if (amount > 0.0) 
		{
			account.deposit(amount);			
		}
	}
	
	public void creditAccount(double amount)
	{
		if (amount <= account.getBalance())
		{
			account.withdraw(amount);
		}
	}
}
