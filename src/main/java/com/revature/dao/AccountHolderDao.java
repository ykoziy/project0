package com.revature.dao;

public interface AccountHolderDao
{
	boolean delete(long userId, long accountId);
	boolean add(long userId, long accountId);
}
