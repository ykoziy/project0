package com.revature.view.menu;

import java.util.Scanner;

import com.revature.view.Console;

public class Welcome extends Menu
{
	@Override
	public boolean handleInput()
	{		
		Scanner scan = new Scanner(System.in);
		
		String input = scan.nextLine();
		
		boolean isValid = false;
		
		while (!isValid) {
			if (input.equals("")) {
				isValid = true;
			} else {
				System.out.println("Invalid option, try again.");
			}
		}
		return isValid;	
	}

	@Override
	public void show()
	{
		Console.showWelcomeScreen();
	}

}
