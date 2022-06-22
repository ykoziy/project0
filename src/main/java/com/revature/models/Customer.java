package com.revature.models;

public class Customer extends Person
{
	private static final long serialVersionUID = 1L;

	public Customer()
	{
		super();
	}
	
	public Customer(String firstName, String lastName, String userName, String email, String phoneNumber, String address, String userRole)
	{
		super(firstName, lastName, userName, email, phoneNumber, address, userRole);
	}
	
	public Customer(String firstName, String lastName, String userName, char[] pwd, String email, String phoneNumber, String address, String userRole)
	{
		super(firstName, lastName, userName, pwd, email, phoneNumber, address, userRole);
	}
	
	public Customer(long id, String firstName, String lastName, String userName, String email, String phoneNumber, String address, String userRole)
	{
		super(id, firstName, lastName, userName, email, phoneNumber, address, userRole);
	}
	
	public Customer(long id, String firstName, String lastName, String userName, char[] pwd, String email, String phoneNumber, String address, String userRole)
	{
		super(id, firstName, lastName, userName, pwd, email, phoneNumber, address, userRole);
	}
	
	public Account getAccount(String accountNumber)
	{
		return accounts.get(accountNumber);
	}
	
	public String getCustomerInfo()
	{
		String info = "\n=======================================================\n";
		info += "Name: " + this.getFirstName() + " " + this.getLastName() + "\n";
		info += "User Name: " + this.getUserName() + "\n";
		info += "Email: " + this.getEmail() + "\n";
		info += "Address: " + this.getAddress() + "\n";
		info += "Phone Number: " + this.getPhoneNumber() + "\n";
		info += "=======================================================\n";
		return info;
	}
}
