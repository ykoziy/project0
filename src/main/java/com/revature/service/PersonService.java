package com.revature.service;

import com.revature.dao.PersonDao;
import com.revature.dao.AddressDao;
import com.revature.dao.PsqlAddressDao;
import com.revature.dao.PsqlPersonDao;
import com.revature.models.Address;
import com.revature.models.Person;

public class PersonService
{
	public PersonDao pdao = new PsqlPersonDao();
	public AddressDao adao = new PsqlAddressDao();
	
	public Person register(Person person, Address address)
	{
		if (person.getId() != 0) 
		{
			//do something.... exception?
		}
		
		long newPersonIndex = pdao.add(person);
		
		long newAddressIndex = adao.addPersonAddress(address, newPersonIndex);
		
		if (newPersonIndex != -1 && newPersonIndex != person.getId()) 
		{
			person.setId(newPersonIndex );
		} else {
			//do something.... exception?
		}
		
		if (newAddressIndex != -1 && newAddressIndex != address.getId()) 
		{
			address.setId(newAddressIndex);
		} else {
			//do something.... exception?
		}
		
		return person;
	}
	
	public Person login(String username, String password)
	{
		//plain string login......
		Person returnedPerson = pdao.getByUsername(username);
		if (new String(returnedPerson.getPassword()).equals(password)) 
		{
			return returnedPerson;
		}
				
		return null;
	}
	
	public Person update()
	{
		return null;
	}
	
	public boolean delete()
	{
		return false;
	}
}
