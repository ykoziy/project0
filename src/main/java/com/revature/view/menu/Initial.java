package com.revature.view.menu;

import java.util.Scanner;

import com.revature.view.ConsoleOps;

public class Initial extends Menu implements Interactable
{
	public Initial(String menuTitle)
	{
		super(menuTitle);
		this.addOption(1, "Sign In");
		this.addOption(2, "Sign Up");
		this.addQuitOption();
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
		
		while (!isValid) {
			switch (input)
			{
				case '1':
					isValid = true;
					return "signin";
				case '2':
					isValid = true;
					return "signup";
				case 'q':
					this.exitApp();
					break;				
				default:
					input = scan.next().charAt(0);
					break;
			}
		}

		return "invalid";
	}
}
