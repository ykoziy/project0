package com.revature.models;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class Customer extends User
{
	private Account account;
	
	private String firstName;
	
	private String lastName;
	
	private String userName;
	
	private String phoneNumber;
	
	private String address;

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
		info += "Account Number: " + account.getNumber() + "\n";
		info += "=======================================================\n";
		return info;
	}
	
	public String getAccountInfo()
	{
		Locale usa = new Locale("en", "US");
		Currency dollars = Currency.getInstance(usa);
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

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
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

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}
}
