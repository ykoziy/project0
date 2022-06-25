package com.revature.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.revature.dao.PsqlAddressDao;
import com.revature.dao.PsqlPersonDao;
import com.revature.enums.Status;
import com.revature.enums.UserRole;
import com.revature.exceptions.RegisterPersonFailedException;
import com.revature.models.Account;
import com.revature.models.Address;
import com.revature.models.Person;

public class PersonServiceTest
{
	private PersonService ps;
	
	// Declare the dependencies of the class to be tested
	private PsqlPersonDao mockDaoPerson;
	private PsqlAddressDao mockDaoAddress;
	
	private Person dummyPerson;
	private Address dummyAddress;
	
	private final String firstName = "John";
	private final String lastName = "Doe";
	private final String userName = "jdoe45";
	private final String email = "jdoe@google.com";
	private final String phoneNumber = "646-911-0101";
	
	@Before
	public void setup()
	{
		ps = new PersonService();
		
		// Let's mock the class that's being injected
		mockDaoPerson = mock(PsqlPersonDao.class);
		mockDaoAddress = mock(PsqlAddressDao.class);
		
		// Let's set the user dao inside of the personservice to this mocked one
		ps.pdao = mockDaoPerson;
		ps.adao = mockDaoAddress;
		
		dummyPerson = new Person();
		dummyPerson.setAccounts(new HashMap<String, Account>());
		dummyPerson.setId(0);
	}
	
	@After
	public void teardown()
	{
		ps = null;
		dummyPerson = null;
		dummyAddress = null;
		mockDaoPerson = null;
		mockDaoAddress = null;
	}
	
	// testing register
	
	@Test
	public void testRegisterPersonReturnsNewPKId() 
	{
		// Let's make a user object to test
		dummyAddress = new Address("404 Example Rd", "New York", "NY", "10017");
		dummyPerson = new Person(0, firstName, lastName, userName,"pwd".toCharArray(), email, phoneNumber, dummyAddress, UserRole.admin);
		dummyPerson.setAccounts(new HashMap<String, Account>());
		
		// Let's generate a random number to pretend the DB created it
		Random r = new Random();
		long fakePK = r.nextLong();
		
		// So now before we run the actual user service method we have to set up a mock
		// to emulate the function of the insert method from the dao
		when(mockDaoPerson.add(dummyPerson)).thenReturn(fakePK);
		when(mockDaoAddress.addPersonAddress(dummyAddress, fakePK)).thenReturn(fakePK);
		
		// The registered user of our register method SHOULD have the id that's returned from insert
		Person registeredPerson = ps.register(dummyPerson, dummyAddress);
		
		// The final thing to do is call our assert method and the id of the registered user
		// to see if it matches the fakePK
		assertEquals(registeredPerson.getId(), fakePK);
	}
	
	@Test(expected = RegisterPersonFailedException.class)
	public void testRegisterPersonWithNonZeroId() 
	{
		
		dummyPerson.setId(1);
		dummyAddress = new Address("404 Example Rd", "New York", "NY", "10017");
		ps.register(dummyPerson, dummyAddress);

	}
	

	@Test(expected = RegisterPersonFailedException.class)
	public void testRegisterPersonWithFailedAddressInsertion() 
	{
		dummyAddress = new Address("404 Example Rd", "New York", "NY", "10017");
		
		when(mockDaoPerson.add(dummyPerson)).thenReturn(-1L);
		when(mockDaoAddress.addPersonAddress(dummyAddress, 1)).thenReturn(-1L);
		ps.register(dummyPerson, dummyAddress);
	}
	
	@Test(expected = RegisterPersonFailedException.class)
	public void testRegisterPersonWithFailedPersonInsertion() 
	{
		dummyAddress = new Address("404 Example Rd", "New York", "NY", "10017");
		
		when(mockDaoPerson.add(dummyPerson)).thenReturn(1L);
		when(mockDaoAddress.addPersonAddress(dummyAddress, 1)).thenReturn(-1L);
		ps.register(dummyPerson, dummyAddress);
	}
	
	@Test
	public void testLoginReturnsPerson() 
	{
		dummyAddress = new Address("404 Example Rd", "New York", "NY", "10017");
		dummyPerson = new Person(0, firstName, lastName, userName,"pwd".toCharArray(), email, phoneNumber, dummyAddress, UserRole.admin);
		
		String username = "jdoe45";
		String password = "pwd";
		
		when(mockDaoPerson.getByUsername(username)).thenReturn(dummyPerson);
		
		Person loggedIn = ps.login(username, password);
		
		assertEquals(loggedIn.getUserName(), username);
		
		
	}
	
	// testing login
	
	@Test
	public void testLoginReturnsNull() 
	{
		dummyAddress = new Address("404 Example Rd", "New York", "NY", "10017");
		dummyPerson = new Person(0, firstName, lastName, userName,"pwd".toCharArray(), email, phoneNumber, dummyAddress, UserRole.admin);
		
		String username = "jdoe45";
		String password = "pwdz";
		
		when(mockDaoPerson.getByUsername(username)).thenReturn(dummyPerson);
		
		Person loggedIn = ps.login(username, password);
		Assert.assertNull(loggedIn);
	}
	
	//testing update
	
	@Test
	public void shouldBeAbleToEditPerson()
	{
		dummyAddress = new Address("404 Example Rd", "New York", "NY", "10017");
		dummyPerson = new Person(0, firstName, lastName, userName,"pwd".toCharArray(), email, phoneNumber, dummyAddress, UserRole.customer);
		
		when(mockDaoPerson.update(dummyPerson)).thenReturn(true);
		
		boolean result = ps.update(dummyPerson);

		Assert.assertTrue(result);
	}
	
	// testing delete
	
	@Test
	public void shouldBeAbleToDeletePerson()
	{
		dummyAddress = new Address("404 Example Rd", "New York", "NY", "10017");
		dummyPerson = new Person(1, firstName, lastName, userName,"pwd".toCharArray(), email, phoneNumber, dummyAddress, UserRole.customer);
		
		when(mockDaoPerson.delete(dummyPerson.getId())).thenReturn(true);
		
		boolean result = ps.delete(dummyPerson);

		Assert.assertTrue(result);
	}
	
	@Test
	public void shouldNotDeletePersonWithIdZero()
	{
		dummyAddress = new Address("404 Example Rd", "New York", "NY", "10017");
		dummyPerson = new Person(0, firstName, lastName, userName,"pwd".toCharArray(), email, phoneNumber, dummyAddress, UserRole.customer);
		
		when(mockDaoPerson.delete(dummyPerson.getId())).thenReturn(true);
		
		boolean result = ps.delete(dummyPerson);

		Assert.assertFalse(result);
	}
	
	// testing getAll
	@Test
	public void shouldBeAbleToGetAllUsers()
	{
		List<Person> userList = new ArrayList<>();
		dummyAddress = new Address("404 Example Rd", "New York", "NY", "10017");
		dummyPerson = new Person(0, firstName, lastName, userName,"pwd".toCharArray(), email, phoneNumber, dummyAddress, UserRole.customer);
		
	    
		for (int i = 0; i < 5; i++) 
		{
			userList.add(new Person(i+1, firstName + i, lastName, userName,"pwd".toCharArray(), email, phoneNumber, dummyAddress, UserRole.customer));		
		}
		
		when(mockDaoPerson.getAll()).thenReturn(userList);
		
		List<Person> resultList = ps.getAll();
		
		Assert.assertNotNull(resultList);
		Assert.assertArrayEquals(userList.toArray(), resultList.toArray());
	}
}
