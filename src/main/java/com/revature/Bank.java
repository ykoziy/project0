package com.revature;

import java.util.List;

import com.revature.enums.Status;
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
	
	public boolean withdraw(long accountId, double amount)
	{
		return as.withdraw(accountId, amount);
	}
	
	public boolean transfer(long srctId, long destId, double amount)
	{
		return as.transfer(srctId, destId, amount);
	}
	
	public Person getUser(String username)
	{
		return ps.findByUsername(username);
	}
	
	public List<Account> getAccountsForUser(String username)
	{
		List<Account> alist = as.getAccountsByUserName(username);
		
		if (alist != null) 
		{
			return alist;
		}	
		return alist;
	}
	
	public Account createAccount(double deposit)
	{
		long balance = Math.round(deposit*100);
		Account a = new Account(0, currentUser.getId(), balance, Status.pending);
		return as.createAccountForPerson(currentUser, a);
	}

	public List<Person> getAllUsernames()
	{
		return ps.getAll();	
	}
	
	public List<Account> getAllAccounts(Status status)
	{
		return as.getAll(status);
	}
	
	public boolean deleteAccount(long id)
	{
		return as.delete(id);
	}
	
	public Account getAccountById(long id)
	{
		return as.getAccount(id);
	}
	
	public boolean updateAccount(Account acc)
	{
		return as.update(acc);
	}
	
	public boolean addAccountUser(long userId, long accountId)
	{
		return as.addAccountUser(userId, accountId);
	}

	public boolean removeAccountUser(long id, long accountId)
	{
		return as.deleteAccountUser(id, accountId);
	}
}
