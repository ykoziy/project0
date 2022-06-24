package com.revature;

import com.revature.models.Person;
import com.revature.service.PersonService;

public class Bank
{
	private Person currentUser;
	
	private PersonService ps;
	
	public Bank()
	{
		
	}

	public Person getCurrentUser()
	{
		return currentUser;
	}

	public void setCurrentUser(Person currentUser)
	{
		this.currentUser = currentUser;
	}
}
