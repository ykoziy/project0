package com.revature.models;

import java.util.HashMap;
import java.util.Map;

public class Bank
{
	private Map<String, User> users;
	private Map<String, Account> accounts;
	
	public Bank() 
	{
		users = new HashMap<>();
		accounts = new HashMap<>();
	}
	
	public Bank(Map<String, User> users, Map<String, Account> accounts)
	{
		this.users = users;
		this.accounts = accounts;
	}
	
	public void addCustomer(Customer customer)
	{
		users.put(customer.getUserName(), customer);
	}
	
	public void removeCustomer(String userName)
	{
		users.remove(userName);
	}
	
	public void addAccount(Account account)
	{
		accounts.put(account.getNumber(), account);
	}
	
	public void removeAccount(String accountNumber)
	{
		accounts.remove(accountNumber);
	}

	public Map<String, User> getUsers()
	{
		return users;
	}

	public void setUsers(Map<String, User> users)
	{
		this.users = users;
	}

	public Map<String, Account> getAccounts()
	{
		return accounts;
	}

	public void setAccounts(Map<String, Account> accounts)
	{
		this.accounts = accounts;
	}
	
	public User getUser(String userName) 
	{
		return users.get(userName);
	}
	
	public Account getAccount(String accountNumber) 
	{
		return accounts.get(accountNumber);
	}
}
