package com.revature.models;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.revature.enums.UserRole;

public class Person implements Serializable
{
	private static final long serialVersionUID = 1L;

	private  List<Account> accounts;
	
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
		
	public Person(String firstName, String lastName, String email, String phoneNumber)
	{;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
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
	
	public String getAccountInfo(int index)
	{
		
		Account account = accounts.get(index);
		Locale usa = new Locale("en", "US");
		NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(usa);
		double balance = ((double)account.getBalance()) / 100.0;
		String info = "\n=======================================================\n";
		info += "Account Number: " + account.getAccountNumber() + "\n";
		info += "Account Balance: " + dollarFormat.format(balance) + "\n";
		info += "=======================================================\n";
		return info;		
	}
	
	public long getAccountBalance(int index)
	{
		
		Account account = accounts.get(index);
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
	
	public void setAccounts(List<Account> accounts)
	{
		this.accounts = accounts;
	}
	
	public List<Account> getAccounts()
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
	
    public Account getAccount(int index)
    {
        return accounts.get(index);
    }
    
    public String getInfo()
    {
        String info = "\n=======================================================\n";
        info += "Name: " + this.getFirstName() + " " + this.getLastName() + "\n";
        info += "User Name: " + this.getUserName() + "\n";
        info += "Email: " + this.getEmail() + "\n";
        info += "Address: " + this.getAddress() + "\n";
        info += "Phone Number: " + prettifyPhoneNumber(this.phoneNumber) + "\n";
        info += "=======================================================\n";
        return info;
    }

	private String prettifyPhoneNumber(String phoneNumber)
	{
		StringBuilder sb = new StringBuilder();
		Pattern pattern = Pattern.compile("^(\\d{3})(\\d{3})(\\d{4})$");
		Matcher m = pattern.matcher(phoneNumber);
		if(m.find())
		{
			sb.append("(" + m.group(1) + ")-");
			sb.append(m.group(2) + "-");
			sb.append(m.group(3));
		}
		return sb.toString();
	}
}
