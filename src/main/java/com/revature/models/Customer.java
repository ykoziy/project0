package com.revature.models;

public class Customer extends Person
{
	public Customer(String firstName, String lastName, String userName, String phoneNumber, String address)
	{
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}
	
	public Account getAccount(String accountNumber)
	{
		return accounts.get(accountNumber);
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
}
