package com.revature.dao;

import java.util.List;

import com.revature.models.Account;

public interface AccountDao
{
	Account get(long id);
	
	List<Account> getUserAccounts(long id);
	
	boolean delete(Account account);
	
	boolean update(Account account);
	
	long add(long user_id, Account account);
	
	List<Account> getAll();
}
