package com.revature.view.menu;

import java.util.Scanner;

import com.revature.Bank;
import com.revature.service.PersonService;
import com.revature.view.Console;

public class SignIn extends Menu
{
	private Bank bank;
	
	public SignIn(String menuTitle, Bank bank)
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
					isValid = getUserInfo();
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
	
	private boolean getUserInfo()
	{
		Scanner scan = new Scanner(System.in);
		Console.printLine("\nUsername: ");
		String username = scan.nextLine();
		
		Console.printLine("Password: ");
		String password = scan.nextLine();
		
		Console.printLine("");

		return bank.signIn(username, password);
	}
}
