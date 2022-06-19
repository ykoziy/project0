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
}
