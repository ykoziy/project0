package com.revature.service;

import java.util.List;

import com.revature.dao.AccountDao;
import com.revature.dao.PsqlAccountDao;
import com.revature.models.Account;

public class AccountService
{
	private AccountDao adao = new PsqlAccountDao();

	public boolean deposit(int accountId)
	{
		return false;
	}
	
	public boolean withdraw(int accountId)
	{
		return false;
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
	
	public Account update(Account acc)
	{
		return null;
	}
	
	public List<Account> getAccountsByUserId()
	{
		return null;
	}
}
