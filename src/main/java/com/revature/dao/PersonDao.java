package com.revature.dao;

import com.revature.models.Person;

public interface PersonDao
{
	Person get(long id);
	
	Person getByUsername(String username);
	
	boolean delete(long id);
	
	boolean update(Person person);

	long add(Person person);
}
