package com.revature.models;

import java.text.NumberFormat;
import java.util.Locale;

public class Customer extends User
{
	public Customer()
	{
		account = new Account();
	}
	
	public Customer(String firstName, String lastName, String userName, String phoneNumber, String address)
	{
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		account = new Account();
	}
	
	public Account getAccount()
	{
		return account;
	}
	
	public String getCustomerInfo()
	{
		String info = "\n=======================================================\n";
		info += "Name: " + firstName + " " + lastName + "\n";
		info += "User Name: " + userName + "\n";
		info += "Address: " + address + "\n";
		info += "Phone Number: " + phoneNumber + "\n";
		info += "=======================================================\n";
		return info;
	}
	
	public String getAccountInfo()
	{
		Locale usa = new Locale("en", "US");
		NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(usa);
		double balance = account.getBalance();
		
		String info = "\n=======================================================\n";
		info += "Account Number: " + account.getNumber() + "\n";
		info += "Account Balance: " + dollarFormat.format(balance) + "\n";
		info += "=======================================================\n";
		return info;		
	}
	
	public void setAccount(Account account)
	{
		this.account = account;
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
