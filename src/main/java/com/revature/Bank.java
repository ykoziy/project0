package com.revature;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.Address;
import com.revature.models.Person;
import com.revature.service.AccountService;
import com.revature.service.PersonService;

public class Bank
{
	private Person currentUser;
	
	private PersonService ps;
	
	private AccountService as;
	
	public Bank()
	{
		ps = new PersonService();
		as = new AccountService();
	}

	public Person getCurrentUser()
	{
		return currentUser;
	}

	public void setCurrentUser(Person currentUser)
	{
		this.currentUser = currentUser;
	}
	
	public boolean signIn(String username, String password)
	{
		currentUser = ps.login(username, password);
		if (currentUser != null)
		{
			return true;
		}
		return false;
	}
	
	public boolean signUp(Person person, Address address)
	{
		currentUser = ps.register(person, address);
		if (currentUser != null)
		{
			return true;
		}
		return false;
	}
	
	public void setUserAccounts()
	{
		long userId = currentUser.getId();
		List<Account> alist = as.getAccountsByUserId(userId);
		
		if (alist != null) {
			currentUser.setAccounts(alist);
		}
	}
	
	public boolean checkUserAccess(long userId, long accountId)
	{
		return as.checkUserAccess(userId, accountId);
	}
	
	public boolean deposit(long accountId, double amount)
	{
		return as.deposit(accountId, amount);
	}
	
	public Person getUser(String username)
	{
		return ps.findByUsername(username);
	}
}
