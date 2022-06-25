package com.revature.service;

import java.util.List;
import org.apache.log4j.Logger;
import com.revature.dao.AccountDao;
import com.revature.dao.PsqlAccountDao;
import com.revature.exceptions.CreateAccountFailedException;
import com.revature.models.Account;
import com.revature.models.Person;

public class AccountService
{
	public AccountDao adao = new PsqlAccountDao();
	
	private Logger logger = Logger.getLogger(AccountService.class);
	
	public Account createAccountForPerson(Person person, Account account)
	{
		if (person.getId() < 1) 
		{
			throw new CreateAccountFailedException("Account not valid to register because person ID was invalid");
		}
		
		if (account.getId() != 0) 
		{
			throw new CreateAccountFailedException("Account not valid to register because account ID is not 0");
		}

		if (account.getOwnerId() != person.getId()) 
		{
			throw new CreateAccountFailedException("Account owner ID does not match person ID");
		}
		
		long newAccountIndex = adao.add(person.getId(), account);
		
		if (newAccountIndex != -1 && newAccountIndex != account.getId()) 
		{
			account.setId(newAccountIndex);
		} else {
			throw new CreateAccountFailedException("Account ID was either -1 or did not change after insertion");
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
			logger.info("updating account with id: " + acc.getId());
			boolean status = adao.update(acc);
			return status;
		}
	}
	
	public List<Account> getAccountsByUserId(long id)
	{
		if (id <= 0)
		{
			return null;
		} else {
			return adao.getUserAccounts(id);
		}
	}
	
	public List<Account> getAll()
	{
		logger.info("fetching all accounts");
		return adao.getAll();
	}
}
