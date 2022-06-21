package com.revature.view.menu;

import java.util.ArrayList;
import java.util.Scanner;

import com.revature.view.Console;

public class Initial extends Menu
{
	public Initial(String menuTitle)
	{
		super(menuTitle);
		this.options = new ArrayList<>();
		this.addOption(1, "Sign In");
		this.addOption(2, "Sign Up");
		this.addQuitOption();
	}

	@Override
	public void show()
	{
		Console.generateMenu(menuTitle, options);
	}
	
	@Override
	public boolean handleInput()
	{
			Scanner scan = new Scanner(System.in);
			char input = scan.next().charAt(0);
			
			boolean isValid = false;
			
			while (!isValid) {
				switch (input)
				{
					case '1':
						System.out.println("Sign In...........");
						isValid = true;
						break;
					case '2':
						System.out.println("Sign Up...........");
						isValid = true;
						break;
					case 'q':
						this.exitApp();
						break;				
					default:
						input = scan.next().charAt(0);
						break;
				}
			}

			return false;
	}

}
