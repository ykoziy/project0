package com.revature.dao;

import java.util.List;

import com.revature.enums.Status;
import com.revature.models.Account;

public interface AccountDao
{
	Account get(long id);
	
	List<Account> getUserAccounts(long id);
	
	List<Account> getUserAccounts(String username);
	
	List<Account> getUserAccounts(String username, Status status);
	
	boolean delete(long id);
	
	boolean update(Account account);
	
	long add(long user_id, Account account);
	
	List<Account> getAll();
	
	boolean checkUserAccess(long userId, long accountId);
	
	boolean transfer(long srcId, long destId, long amount);
}
