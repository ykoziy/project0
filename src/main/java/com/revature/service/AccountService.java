package com.revature.service;

import java.util.List;
import org.apache.log4j.Logger;
import com.revature.dao.AccountDao;
import com.revature.dao.AccountHolderDao;
import com.revature.dao.PsqlAccountDao;
import com.revature.dao.PsqlAccountHolderDao;
import com.revature.enums.Status;
import com.revature.exceptions.CreateAccountFailedException;
import com.revature.models.Account;
import com.revature.models.Person;

public class AccountService
{
	public AccountDao adao = new PsqlAccountDao();
	public AccountHolderDao ahdao = new PsqlAccountHolderDao();
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
			logger.info("created an account with id: " + newAccountIndex);
		} else
		{
			throw new CreateAccountFailedException("Account ID was either -1 or did not change after insertion");
		}
		return account;
	}

	public boolean deposit(long accountId, double amount)
	{
		Account acc = null;
		if (amount <= 0 || accountId <= 0)
		{
			return false;
		} else
		{
			acc = adao.get(accountId);
			if (acc == null)
			{
				return false;
			}
			long amt = Math.round(amount * 100);
			acc.setBalance(acc.getBalance() + amt);
			boolean status = adao.update(acc);
			logger.info("depositing: $" + amt / 100.0 + " to the account id: " + accountId);
			return status;
		}
	}

	public boolean withdraw(long accountId, double amount)
	{
		Account acc = null;
		if (accountId <= 0)
		{
			return false;
		}
		acc = adao.get(accountId);
		if (acc == null)
		{
			return false;
		}
		long amt = Math.round(amount * 100);
		if (amt > acc.getBalance())
		{
			return false;
		} else
		{
			acc.setBalance(acc.getBalance() - amt);
			boolean status = adao.update(acc);
			logger.info("withdrawing: $" + amt + " from the account id: " + accountId);
			return status;
		}
	}

	public boolean transfer(long fromId, long toId, double amount)
	{
		if (fromId == toId)
		{
			return false;
		}
		logger.info("transfering: $" + amount + " from id: " + fromId + " to id: " + toId);
		return adao.transfer(fromId, toId, Math.round(amount * 100));
	}

	public boolean delete(long id)
	{
		if (id > 0)
		{
			logger.info("deleteding account with id: " + id);
			return adao.delete(id);
		} else
		{
			return false;
		}
	}

	public boolean update(Account acc)
	{
		if (acc == null || acc.getId() <= 0 || acc.getOwnerId() <= 0)
		{
			return false;
		} else
		{
			logger.info("updating account id: " + acc.getId());
			boolean status = adao.update(acc);
			return status;
		}
	}

	public List<Account> getAccountsByUserId(long id)
	{
		if (id <= 0)
		{
			return null;
		} else
		{
			logger.info("fetching all accounts for user id: " + id);
			return adao.getUserAccounts(id);
		}
	}

	public List<Account> getAccountsByUserName(String username)
	{
		if (username.equals(""))
		{
			return null;
		} else
		{
			logger.info("fetching all accounts by for user: " + username);
			return adao.getUserAccounts(username);
		}
	}

	public List<Account> getAccountsByUserName(String username, Status status)
	{
		if (username.equals(""))
		{
			return null;
		} else
		{
			logger.info("fetching all accounts by for user: " + username + " with status: " + status);
			return adao.getUserAccounts(username, status);
		}
	}

	public List<Account> getAll()
	{
		logger.info("fetching all accounts");
		return adao.getAll();
	}

	public List<Account> getAll(Status status)
	{
		logger.info("fetching all accounts with status: " + status);
		return adao.getAll(status);
	}

	public boolean checkUserAccess(long userId, long accountId)
	{
		if (userId <= 0 || accountId <= 0)
		{
			return false;
		} else
		{
			logger.info("checking if user id: " + userId + " can access account id: " + accountId);
			return adao.checkUserAccess(userId, accountId);
		}
	}

	public Account getAccount(long id)
	{
		if (id <= 0)
		{
			return null;
		} else
		{
			logger.info("fetching account with id: " + id);
			return adao.get(id);
		}
	}

	public boolean addAccountUser(long userId, long accountId)
	{
		if (userId <= 0 || accountId <= 0)
		{
			return false;
		} else
		{
			logger.info("adding user id: " + userId + " access to the account id: " + accountId);
			return ahdao.add(userId, accountId);
		}
	}
	
	public boolean deleteAccountUser(long userId, long accountId)
	{
		if (userId <= 0 || accountId <= 0)
		{
			return false;
		} else
		{
			logger.info("deliting user id: " + userId + " access to the account id: " + accountId);
			return ahdao.delete(userId, accountId);
		}
	}
}
