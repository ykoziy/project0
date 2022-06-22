package com.revature.models;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

import com.revature.enums.UserRole;

public abstract class Person implements Serializable
{
	protected Map<String, Account> accounts;
	
	private long id;
	
	private String firstName;
	
	private String lastName;
	
	private String userName;
	
	private char[] password;
	
	private String email;
	
	private String phoneNumber;
	
	private Address address;
	
	private UserRole userRole;
	
	public Person() 
	{
		
	}
		
	public Person(long id, String firstName, String lastName, String userName, String email, String phoneNumber, Address address, UserRole userRole)
	{
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.userRole = userRole;
	}
	
	public Person(long id, String firstName, String lastName, String userName, char[] password, String email, String phoneNumber, Address address, UserRole userRole)
	{
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.userRole = userRole;
	}
	
	public String getAccountInfo(String accountNumber)
	{
		
		Account account = accounts.get(accountNumber);
		Locale usa = new Locale("en", "US");
		NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(usa);
		double balance = account.getBalance();
		String info = "\n=======================================================\n";
		info += "Account Number: " + account.getAccountNumber() + "\n";
		info += "Account Balance: " + dollarFormat.format(balance) + "\n";
		info += "=======================================================\n";
		return info;		
	}
	
	public double getAccountBalance(String accountNumber)
	{
		
		Account account = accounts.get(accountNumber);
		return account.getBalance();
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
	
	public Address getAddress()
	{
		return address;
	}

	public void setAddress(Address address)
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

	public UserRole getUserRole()
	{
		return userRole;
	}

	public void setUserRole(UserRole userRole)
	{
		this.userRole = userRole;
	}
	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public char[] getPassword()
	{
		return password;
	}

	public void setPassword(char[] password)
	{
		this.password = password;
	}
}
