package com.revature.view.menu;

import java.util.Scanner;

import com.revature.Bank;
import com.revature.enums.UserRole;
import com.revature.models.Address;
import com.revature.models.Person;
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
		
		while (!isValid) {
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
					break;
			}
		}
		if (isValid) {
			result = "next";
		}
		return result;
	}
	
	private boolean register()
	{

		
		getPersonalInfo();
		
		getAddressInfo();
		
		boolean isValid = getAccountInfo();

		if (isValid) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean getAddressInfo()
	{
		Scanner scan = new Scanner(System.in);
		String street = "";
		String city = "";
		String state = "";
		String zip = "";
		
		Console.printLine("");
		Console.printLine("\nPlease enter your home address information: ");
		Console.printLine("Street address: ");
		street = scan.nextLine();
		
		Console.printLine("City: ");
		city = scan.nextLine();
		
		Console.printLine("State: ");
		state = scan.nextLine();
		
		Console.printLine("Phone number (5 digit only): ");
		zip = scan.nextLine();
		
		
		Console.printLine("");
		
		address = new Address(street, city, state, zip);
		
		return true;
	}
	
	private boolean getPersonalInfo()
	{
		Scanner scan = new Scanner(System.in);
		String firstName = "";
		String lastName = "";
		String email = "";
		String phoneNumber = "";
		
		Console.printLine("\nLet's gather your personal infromation: ");
		Console.printLine("First name: ");
		firstName = scan.nextLine();
		
		Console.printLine("Last name: ");
		firstName = scan.nextLine();
		
		Console.printLine("Email: ");
		email = scan.nextLine();
		
		Console.printLine("Phone number (10 digit only): ");
		phoneNumber = scan.nextLine();


		Console.printLine("");
		
		
		person = new Person(firstName, lastName, email, phoneNumber);

		return true;
	}
	
	private boolean  getAccountInfo() 
	{
		Scanner scan = new Scanner(System.in);
		String username = "";
		String password = "";

		
		Console.printLine("\nLet's create an account: ");
		Console.printLine("Username: ");
		username = scan.nextLine();
		
		Console.printLine("Password: ");
		password = scan.nextLine();
			
		person.setId(0);
		person.setUserRole(UserRole.customer);
		person.setUserName(username);
		person.setPassword(password.toCharArray());
		
		if (bank.signUp(person, address)) {
			return true;
		} else {
			return false;
		}
	}
}
