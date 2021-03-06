package com.revature.service;

import com.revature.dao.PersonDao;

import java.util.List;

import org.apache.log4j.Logger;

import com.revature.dao.AddressDao;
import com.revature.dao.PsqlAddressDao;
import com.revature.dao.PsqlPersonDao;
import com.revature.exceptions.RegisterPersonFailedException;
import com.revature.models.Address;
import com.revature.models.Person;
import com.revature.util.PasswordSecurity;

public class PersonService
{
	public PersonDao pdao = new PsqlPersonDao();
	public AddressDao adao = new PsqlAddressDao();
	private Logger logger = Logger.getLogger(PersonService.class);
	
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
			person.setId(newPersonIndex);
			logger.info("user: " + newPersonIndex + " was created");
		} else {
			throw new RegisterPersonFailedException("Person's ID was either -1 or did not change after insertion");
		}
		
		if (newAddressIndex != -1 && newAddressIndex != address.getId()) 
		{
			address.setId(newAddressIndex);
			logger.info("address: " + newAddressIndex + " was created");
		} else {
			throw new RegisterPersonFailedException("Person's address ID was either -1 or did not change after insertion");
		}
		
		return person;
	}
	
	public Person login(String username, String password)
	{
		Person returnedPerson = pdao.getByUsername(username);
		if (returnedPerson != null) 
		{
			String passwordHash = new String(returnedPerson.getPassword());
			if (PasswordSecurity.comparePassword(passwordHash, password)) 
			{
				logger.info("user: " + returnedPerson.getId() + " successfully logged in");
				return returnedPerson;
			}		
		}	
		return null;
	}
	
	public boolean update(Person person)
	{
		boolean result = pdao.update(person);
		logger.info("updating person with id: " + person.getId());
		return result;
	}
	
	public boolean delete(Person person)
	{
		if (person.getId() > 0)
		{
			logger.info("deleteding person with id: " + person.getId());
			return pdao.delete(person.getId());			
		} else {
			return false;		
		}
	}
	
	public List<Person> getAll()
	{
		logger.info("getting all users");
		return pdao.getAll();
	}
	
	public Person findByUsername(String username)
	{
		if (username.equals("")) 
		{
			return null;
		} else {
			logger.info("finding person by username");
			return pdao.getByUsername(username);
		}
	}
}
