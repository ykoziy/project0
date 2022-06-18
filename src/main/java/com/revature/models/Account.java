package com.revature.models;

import java.math.BigDecimal;
import com.revature.util.SequenceGenerator;

public class Account
{
	private BigDecimal balance;
	
	private String number;
	
	private static final int NUMBER_LENGTH = 17;
	
	public Account() {
		balance = new BigDecimal(0.0);
		number = generateAccountNumber();
	}

	public void deposit(double amount)
	{
		balance = balance.add(BigDecimal.valueOf(amount));
	}
	
	public void withdraw(double amount)
	{
		balance = balance.subtract(BigDecimal.valueOf(amount));
	}
	
	public double getBalance()
	{
		return balance.doubleValue();
	}

	public String getNumber()
	{
		return number;
	}
	
	private String generateAccountNumber() {
		long accountCount = SequenceGenerator.getNext();
		StringBuffer sb = new StringBuffer();
		int padZeroCount = NUMBER_LENGTH - Long.toString(accountCount).length();
		
		for (int i = 0; i < padZeroCount; i++) {
			sb.append("0");
		}
		
		sb.append(accountCount);
		return sb.toString();
	}
	
}
