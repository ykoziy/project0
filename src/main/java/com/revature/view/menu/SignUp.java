package com.revature.view.menu;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import org.apache.commons.validator.routines.EmailValidator;

import com.revature.Bank;
import com.revature.enums.UserRole;
import com.revature.models.Address;
import com.revature.models.Person;
import com.revature.util.PasswordSecurity;
import com.revature.view.Console;

public class SignUp extends Menu
{
	private Bank bank;
	private Address address;
	private Person person;

	public SignUp(String menuTitle, Bank bank)
	{
		super(menuTitle);
		this.bank = bank;
		this.addOption(1, "Continue");
		this.addQuitOption();
		this.addGoBackOption();
	}

	@Override
	public void show()
	{
		Console.generateMenu(menuTitle, options);
	}

	@Override
	public String handleInput()
	{
		Scanner scan = new Scanner(System.in);
		char input = scan.next().charAt(0);
		boolean isValid = false;
		String result = "invalid";
		while (!isValid)
		{
			switch (input)
			{
			case '1':
				isValid = register();
				break;
			case 'b':
				return "back";
			case 'q':
				this.exitApp();
				break;
			default:
				input = scan.next().charAt(0);
				break;
			}
		}
		if (isValid)
		{
			result = "back";
		}
		return result;
	}

	private boolean register()
	{
		getPersonalInfo();
		getAddressInfo();
		boolean isValid = createUserAccount();
		if (isValid)
		{
			return true;
		} else
		{
			return false;
		}
	}

	private boolean getPersonalInfo()
	{
		String firstName = "";
		String lastName = "";
		String email = "";
		String phoneNumber = "";
		Console.printLine("\nLet's gather your personal infromation: ");
		firstName = readStringOfLength("First name", 45);
		lastName = readStringOfLength("Last name", 45);
		email = readStringOfLength("Email", 320);
		while (!EmailValidator.getInstance().isValid(email))
		{
			Console.printLine("Invalid email, try again");
			email = readStringOfLength("Email", 320);
		}
		phoneNumber = readString("Phone number (10 digit only)");
		while (!phoneNumber.matches("^\\d{10}\\b.*"))
		{
			Console.printLine("Phone number must be 10 digits only, try again.");
			phoneNumber = readString("Phone number (10 digit only)");
		}
		Console.printLine("");
		person = new Person(firstName, lastName, email, phoneNumber);
		return true;
	}

	private boolean getAddressInfo()
	{
		String street = "";
		String city = "";
		String state = "";
		String zip = "";
		Console.printLine("");
		Console.printLine("\nPlease enter your home address information: ");
		street = readStringOfLength("Street address", 100);
		city = readStringOfLength("City", 40);
		state = readStringOfLength("State (2 letters)", 2);
		zip = readString("Zip (5 digits only)");
		while (!zip.matches("^\\d{5}\\b.*"))
		{
			Console.printLine("Zip code must be 5 digits only, try again.");
			zip = readString("Zip (5 digits only)");
		}
		Console.printLine("");
		address = new Address(street, city, state.toUpperCase(), zip);
		return true;
	}

	private boolean createUserAccount()
	{
		String username = "";
		String password = "";
		Console.printLine("\nLet's create an account: ");
		username = readStringOfLength("Username", 20);
		password = readStringOfLength("Password", 35);
		String dbHash = PasswordSecurity.getHashedPassword(password);
		if (dbHash.equals(""))
		{
			return false;
		}
		person.setId(0);
		person.setUserRole(UserRole.customer);
		person.setUserName(username);
		person.setPassword(dbHash.toCharArray());
		if (bank.signUp(person, address))
		{
			Console.printLine(String.format("Welcome to YKZ BANK %s %s, you can now sign in using username: %s ", person.getFirstName(), person.getLastName(), person.getUserName()));
			return true;
		} else
		{
			return false;
		}
	}
}
