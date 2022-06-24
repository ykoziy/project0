package com.revature.view.menu;

import java.util.Scanner;

import com.revature.view.Console;

public class Welcome extends Menu
{
	@Override
	public void show()
	{
		Console.showWelcomeScreen();
	}

	@Override
	public String handleInput()
	{
		Scanner scan = new Scanner(System.in);
		
		boolean isValid = false;
		
		while (!isValid)
		{
			if (scan.nextLine().equals("")) {
				isValid = true;
				return "next";
			}
		}
		return "invalid";
	}
}
