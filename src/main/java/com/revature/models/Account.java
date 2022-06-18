package com.revature.models;

import java.math.BigDecimal;

public class Account
{
	private BigDecimal balance;
	
	public Account() {
		balance = new BigDecimal(0.0);
	}

	public void deposit(double amount)
	{
		balance = balance.add(BigDecimal.valueOf(amount));
	}

	public double getBalance()
	{
		return balance.doubleValue();
	}

}
