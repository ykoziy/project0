package com.revature.service;

import java.util.List;

import com.revature.dao.AccountDao;
import com.revature.dao.PsqlAccountDao;
import com.revature.exceptions.RegisterPersonFailedException;
import com.revature.models.Account;
import com.revature.models.Person;

public class AccountService
{
	private AccountDao adao = new PsqlAccountDao();
	
	public Account createAccountForPerson(Person person, Account account)
	{
		if (person.getId() < 1) 
		{
			throw new RegisterPersonFailedException("Account not valid to register because person ID was invalid");
		}
		
		if (account.getId() != 0) 
		{
			throw new RegisterPersonFailedException("Account not valid to register because account ID is not 0");
		}
		
		if (account.getOwnerId() != person.getId()) 
		{
			throw new RegisterPersonFailedException("Account owner ID does not match person ID");
		}
		
		long newAccountIndex = adao.add(person.getId(), account);
		
		if (newAccountIndex != -1 && newAccountIndex != account.getId()) 
		{
			account.setId(newAccountIndex);
		} else {
			throw new RegisterPersonFailedException("Account ID was either -1 or did not change after insertion");
		}

		return account;
	}

	public boolean deposit(int accountId, double amount)
	{
		Account acc = null;
		if (amount <= 0 || accountId <= 0) {
			return false;
		} else {
			acc = adao.get(accountId);
			if (acc == null) {
				return false;
			}
			long amt = (long)(amount*100.00);
			acc.setBalance(acc.getBalance() + amt);
			boolean status = adao.update(acc);
			return status;
		}
	}
	
	public boolean withdraw(int accountId, double amount)
	{
		Account acc = null;

		if (accountId <= 0) {
			return false;
		}
		
		acc = adao.get(accountId);
		
		if (acc == null) {
			return false;
		}
		
		long amt = (long)(amount*100.00);
		
		if (amt > acc.getBalance()) {
			return false;
		} else {
			acc.setBalance(acc.getBalance() - amt);
			boolean status = adao.update(acc);
			return status;
		}
	}
	
	public boolean transfer(int fromId, int toId)
	{
		return false;
	}
	
	public boolean close()
	{
		// closing account is deleting????
		return false;
	}
	
	public boolean update(Account acc)
	{
		if (acc == null || acc.getId() <= 0 || acc.getOwnerId() <= 0) 
		{
			return false;
		} else {
			boolean status = adao.update(acc);
		}
		return false;
	}
	
	public List<Account> getAccountsByUserId()
	{
		return null;
	}
}
