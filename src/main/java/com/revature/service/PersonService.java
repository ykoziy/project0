package com.revature.service;

import com.revature.dao.PersonDao;
import com.revature.dao.AddressDao;
import com.revature.dao.PsqlAddressDao;
import com.revature.dao.PsqlPersonDao;
import com.revature.exceptions.RegisterPersonFailedException;
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
			throw new RegisterPersonFailedException("Person not valid to register because ID was not 0");
		}
		
		long newPersonIndex = pdao.add(person);
		
		long newAddressIndex = adao.addPersonAddress(address, newPersonIndex);
		
		if (newPersonIndex != -1 && newPersonIndex != person.getId()) 
		{
			person.setId(newPersonIndex );
		} else {
			throw new RegisterPersonFailedException("Person's ID was either -1 or did not change after insertion");
		}
		
		if (newAddressIndex != -1 && newAddressIndex != address.getId()) 
		{
			address.setId(newAddressIndex);
		} else {
			throw new RegisterPersonFailedException("Person's address ID was either -1 or did not change after insertion");
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
	
	public boolean update(Person person)
	{
		boolean result = pdao.update(person);
		return result;
	}
	
	public boolean delete(Person person)
	{
		if (person.getId() > 0)
		{
			return pdao.delete(person.getId());			
		} else {
			return false;		
		}
	}
}
