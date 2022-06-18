package com.revature.models;

public class Customer extends User
{
	private Account account;
	
	private String firstName;
	
	private String lastName;
	
	private String userName;
	
	private String phoneNumber;
	
	private String address;

	public Account getAccount()
	{
		return account;
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

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}
}
