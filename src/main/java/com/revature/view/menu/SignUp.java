package com.revature.view.menu;

import java.util.Scanner;

import com.revature.Bank;
import com.revature.enums.UserRole;
import com.revature.models.Address;
import com.revature.models.Person;
import com.revature.view.ConsoleOps;

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
		ConsoleOps.generateMenu(menuTitle, options);
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
		
		ConsoleOps.printLine("");
		ConsoleOps.printLine("\nPlease enter your home address information: ");
		ConsoleOps.printLine("Street address: ");
		street = scan.nextLine();
		
		ConsoleOps.printLine("City: ");
		city = scan.nextLine();
		
		ConsoleOps.printLine("State: ");
		state = scan.nextLine();
		
		ConsoleOps.printLine("Phone number (5 digit only): ");
		zip = scan.nextLine();
		
		
		ConsoleOps.printLine("");
		
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
		
		ConsoleOps.printLine("\nLet's gather your personal infromation: ");
		ConsoleOps.printLine("First name: ");
		firstName = scan.nextLine();
		
		ConsoleOps.printLine("Last name: ");
		firstName = scan.nextLine();
		
		ConsoleOps.printLine("Email: ");
		email = scan.nextLine();
		
		ConsoleOps.printLine("Phone number (10 digit only): ");
		phoneNumber = scan.nextLine();


		ConsoleOps.printLine("");
		
		
		person = new Person(firstName, lastName, email, phoneNumber);

		return true;
	}
	
	private boolean  getAccountInfo() 
	{
		Scanner scan = new Scanner(System.in);
		String username = "";
		String password = "";

		
		ConsoleOps.printLine("\nLet's create an account: ");
		ConsoleOps.printLine("Username: ");
		username = scan.nextLine();
		
		ConsoleOps.printLine("Password: ");
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
