package com.revature.models;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public abstract class Person implements Serializable
{
	protected Map<String, Account> accounts;
	
	private String firstName;
	
	protected String lastName;
	
	protected String userName;
	
	protected String email;
	
	protected String phoneNumber;
	
	protected String address;
	
	public Person() 
	{
		
	}
	
	public Person(String firstName, String lastName, String userName, String email, String phoneNumber, String address)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;		
	}
	
	public String getAccountInfo(String accountNumber)
	{
		Account account = accounts.get(accountNumber);
		Locale usa = new Locale("en", "US");
		NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(usa);
		double balance = account.getBalance();
		
		String info = "\n=======================================================\n";
		info += "Account Number: " + account.getNumber() + "\n";
		info += "Account Balance: " + dollarFormat.format(balance) + "\n";
		info += "=======================================================\n";
		return info;		
	}
	
	public double getAccountBalance(String accountNumber)
	{
		
		Account account = accounts.get(accountNumber);
		return account.getBalance();
	}
	
	public void debitAccount(String accountNumber, double amount)
	{
		Account account = accounts.get(accountNumber);
		if (amount > 0.0) 
		{
			account.deposit(amount);			
		}
	}
	
	public void creditAccount(String accountNumber, double amount)
	{
		Account account = accounts.get(accountNumber);
		if (amount <= account.getBalance())
		{
			account.withdraw(amount);
		}
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
	
	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}
	
	public void setAccounts(Map<String, Account> accounts)
	{
		this.accounts = accounts;
	}
	
	public Map<String, Account> getAccounts()
	{
		return accounts;
	}
	
	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

}
