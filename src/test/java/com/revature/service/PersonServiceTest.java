package com.revature.service;

import static org.mockito.Mockito.mock;

import org.junit.After;
import org.junit.Before;

import com.revature.dao.PersonDao;
import com.revature.dao.PsqlAccountDao;
import com.revature.models.Person;

public class PersonServiceTest
{
	private PersonService ps;
	
	private PsqlAccountDao mockDao;
	
	private Person dummyPerson;
	
	@Before
	public void setup()
	{
		ps = new PersonService();
		
		mockDao = mock(PsqlAccountDao.class);
		
		ps.pdao = (PersonDao) mockDao;
		
		dummyPerson = new Person();
	}
	
	
	
	@After
	public void teardown()
	{
		
	}
}
