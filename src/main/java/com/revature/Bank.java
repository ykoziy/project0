package com.revature;

import com.revature.models.Address;
import com.revature.models.Person;
import com.revature.service.PersonService;

public class Bank
{
	private Person currentUser;
	
	private PersonService ps;
	
	public Bank()
	{
		ps = new PersonService();
	}

	public Person getCurrentUser()
	{
		return currentUser;
	}

	public void setCurrentUser(Person currentUser)
	{
		this.currentUser = currentUser;
	}
	
	public boolean signIn(String username, String password)
	{
		currentUser = ps.login(username, password);
		if (currentUser != null)
		{
			return true;
		}
		return false;
	}
	
	public boolean signUp(Person person, Address address)
	{
		currentUser = ps.register(person, address);
		if (currentUser != null)
		{
			return true;
		}
		return false;
	}
}
