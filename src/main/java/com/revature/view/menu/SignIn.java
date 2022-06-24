package com.revature.view.menu;

import java.util.Scanner;

import com.revature.view.Console;

public class SignIn extends Menu
{
	public SignIn(String menuTitle)
	{
		super(menuTitle);
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
		return result;
	}
	
	private boolean getUserInfo()
	{
		boolean canProceed = false;
		Scanner scan = new Scanner(System.in);
		Console.printLine("\nUsername: ");
		String username = scan.nextLine();
		
		Console.printLine("Password: ");
		String password = scan.nextLine();
		
		Console.printLine("");
		// placeholder //
		return canProceed;
	}
}
