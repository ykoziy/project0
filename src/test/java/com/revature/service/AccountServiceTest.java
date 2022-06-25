package com.revature.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Random;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.revature.dao.PsqlAccountDao;
import com.revature.enums.Status;
import com.revature.enums.UserRole;
import com.revature.exceptions.CreateAccountFailedException;
import com.revature.models.Account;
import com.revature.models.Address;
import com.revature.models.Person;

public class AccountServiceTest
{
	private AccountService as;
	private PsqlAccountDao mockDaoAccount;
	
	private Account dummyAccount;
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
		as = new AccountService();
		mockDaoAccount = mock(PsqlAccountDao.class);
		as.adao = mockDaoAccount;
		
		dummyAccount = new Account();
		dummyAccount.setId(0);
		
		dummyPerson = new Person();
		dummyPerson.setAccounts(new HashMap<String, Account>());
		dummyPerson.setId(0);
	}
	
	@After
	public void teardown()
	{
		as = null;
		dummyAccount = null;
		dummyPerson = null;
		dummyAddress = null;
		mockDaoAccount = null;
	}
	
	// testing deposit
	@Test
	public void shouldBeAbleToDepositWithValidAmountAndValidId()
	{
		dummyAccount = new Account(1, 1, 10000, Status.active);
		
		when(mockDaoAccount.get(1)).thenReturn(dummyAccount);
		when(mockDaoAccount.update(dummyAccount)).thenReturn(true);

		boolean result = as.deposit(1, 12.45);
		
		Assert.assertTrue(result);
		Assert.assertEquals(11245, dummyAccount.getBalance());
	}
	
	@Test
	public void shouldNotBeAbleToDepositWithInvalidAmountButValidId()
	{
		dummyAccount = new Account(1, 1, 10000, Status.active);
		
		when(mockDaoAccount.get(1)).thenReturn(dummyAccount);
		when(mockDaoAccount.update(dummyAccount)).thenReturn(true);

		boolean result = as.deposit(1, -12.45);
		
		Assert.assertFalse(result);
		Assert.assertEquals(10000, dummyAccount.getBalance());		
	}
	
	@Test
	public void shouldNotBeAbleToDepositWithValidAmountButInvalidId()
	{
		dummyAccount = new Account(1, 1, 10000, Status.active);
		
		when(mockDaoAccount.get(1)).thenReturn(dummyAccount);
		when(mockDaoAccount.update(dummyAccount)).thenReturn(true);

		boolean result = as.deposit(-1, 12.45);
		
		Assert.assertFalse(result);
		Assert.assertEquals(10000, dummyAccount.getBalance());		
	}
	
	@Test
	public void shouldNotBeAbleToDepositWhenAccountNotFound()
	{
		dummyAccount = new Account(123, 1, 10000, Status.active);
		
		when(mockDaoAccount.get(123)).thenReturn(null);
		when(mockDaoAccount.update(dummyAccount)).thenReturn(true);

		boolean result = as.deposit(1, 12.45);
		
		Assert.assertFalse(result);
		Assert.assertEquals(10000, dummyAccount.getBalance());		
	}

	
	//testing withdraw
	@Test
	public void shouldBeAbleToWithdrawWithValidAmountAndValidId()
	{
		dummyAccount = new Account(1, 1, 10000, Status.active);
		
		when(mockDaoAccount.get(1)).thenReturn(dummyAccount);
		when(mockDaoAccount.update(dummyAccount)).thenReturn(true);

		boolean result = as.withdraw(1, 45.99);
		
		Assert.assertTrue(result);
		Assert.assertEquals(5401, dummyAccount.getBalance());
	}
	
	@Test
	public void shouldNotBeAbleToOverdraft()
	{
		dummyAccount = new Account(1, 1, 10000, Status.active);
		
		when(mockDaoAccount.get(1)).thenReturn(dummyAccount);
		when(mockDaoAccount.update(dummyAccount)).thenReturn(true);

		boolean result = as.withdraw(1, 120);
		
		Assert.assertFalse(result);
		Assert.assertEquals(10000, dummyAccount.getBalance());		
	}
	
	@Test
	public void shouldNotBeAbleToWithdrawWithValidAmountAndInvalidId()
	{
		dummyAccount = new Account(1, 1, 10000, Status.active);
		
		when(mockDaoAccount.get(1)).thenReturn(dummyAccount);
		when(mockDaoAccount.update(dummyAccount)).thenReturn(true);

		boolean result = as.withdraw(0, 45.99);
		
		Assert.assertFalse(result);
		Assert.assertEquals(10000, dummyAccount.getBalance());
	}
	
	@Test
	public void shouldNotBeAbleToWithdrawWhenAccountNotFound()
	{
		dummyAccount = new Account(-1, 1, 10000, Status.active);
		
		when(mockDaoAccount.get(-1)).thenReturn(null);
		when(mockDaoAccount.update(dummyAccount)).thenReturn(true);

		boolean result = as.withdraw(1, 45.99);
		
		Assert.assertFalse(result);
		Assert.assertEquals(10000, dummyAccount.getBalance());
	}
	
	
	// testing create account
	@Test
	public void shouldBeAbleToCreateAnAccountForValidUser()
	{
		dummyAddress = new Address("404 Example Rd", "New York", "NY", "10017");
		dummyPerson = new Person(1, firstName, lastName, userName,"pwd".toCharArray(), email, phoneNumber, dummyAddress, UserRole.customer);
		dummyPerson.setAccounts(new HashMap<String, Account>());
		
		dummyAccount = new Account(0, 1, 10000, Status.pending);
		
		Random r = new Random();
		long fakePK = r.nextLong();
		
		when(mockDaoAccount.add(1, dummyAccount)).thenReturn(fakePK);
		
		Account newAccount = as.createAccountForPerson(dummyPerson, dummyAccount);
		
		Assert.assertEquals(fakePK, newAccount.getId());
		Assert.assertEquals(10000, newAccount.getBalance());
	}
	
	@Test(expected = CreateAccountFailedException.class)
	public void shouldNotBeAbleToCreateAnAccountInvalidUser()
	{
		dummyPerson.setId(0);
		dummyAccount = new Account(0, 1, 10000, Status.pending);
		as.createAccountForPerson(dummyPerson, dummyAccount);
	}
	
	@Test(expected = CreateAccountFailedException.class)
	public void shouldNotBeAbleToCreateAnAccountInvalidAccount()
	{
		dummyPerson.setId(1);
		dummyAccount = new Account(1, 1, 10000, Status.pending);
		as.createAccountForPerson(dummyPerson, dummyAccount);
	}
	
	@Test(expected = CreateAccountFailedException.class)
	public void shouldNotBeAbleToCreateAnAccountWhenOwnerIdNotEqualsToUserId()
	{
		dummyPerson.setId(1);
		dummyAccount = new Account(0, 2, 10000, Status.pending);
		as.createAccountForPerson(dummyPerson, dummyAccount);
	}
	
	@Test(expected = CreateAccountFailedException.class)
	public void shouldNotBeAbleToCreateAnAccountWhenAccountCreationFailed()
	{
		dummyPerson.setId(1);
		dummyAccount = new Account(0, 1, 10000, Status.pending);
		when(mockDaoAccount.add(1, dummyAccount)).thenReturn(-1L);
		as.createAccountForPerson(dummyPerson, dummyAccount);
	}
	
	@Test(expected = CreateAccountFailedException.class)
	public void shouldNotBeAbleToCreateAnAccountWhenAccountCreatedWithSameId()
	{
		dummyPerson.setId(1);
		dummyAccount = new Account(0, 1, 10000, Status.pending);
		when(mockDaoAccount.add(1, dummyAccount)).thenReturn(0L);
		
		as.createAccountForPerson(dummyPerson, dummyAccount);
	}
	
	// testing update
	@Test
	public void shouldBeAbleToUpdateAnAccount()
	{
		dummyAccount = new Account(1, 1, 10000, Status.pending);
		when(mockDaoAccount.update(dummyAccount)).thenReturn(true);
		boolean result = as.update(dummyAccount);
		Assert.assertTrue(result);
	}
	
	@Test
	public void shouldNotBeAbleToUpdateNullAccount()
	{
		dummyAccount = null;
		when(mockDaoAccount.update(dummyAccount)).thenReturn(true);
		boolean result = as.update(dummyAccount);
		Assert.assertFalse(result);
	}
	
	@Test
	public void shouldNotBeAbleToUpdateInvalidAccountId()
	{
		dummyAccount = new Account(0, 1, 10000, Status.pending);
		when(mockDaoAccount.update(dummyAccount)).thenReturn(true);
		boolean result = as.update(dummyAccount);
		Assert.assertFalse(result);
	}
	
	@Test
	public void shouldNotBeAbleToUpdateInvalidOwnerId()
	{
		dummyAccount = new Account(1, 0, 10000, Status.pending);
		when(mockDaoAccount.update(dummyAccount)).thenReturn(true);
		boolean result = as.update(dummyAccount);
		Assert.assertFalse(result);
	}	
}
