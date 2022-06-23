package com.revature.dao;

import com.revature.models.Address;

public interface AddressDao
{
	long addPersonAddress(Address address, long personId);
	
	Address getPersonAddress(long personId);
	
	boolean update(Address address, long personId);
}
