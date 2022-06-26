package com.revature.view.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.view.Console;

public abstract class Menu implements Interactable
{
	protected String menuTitle;
	protected List<String> options;
	
	public Menu() {
		options = new ArrayList<>();
	}
	
	public Menu(String menuTitle)
	{
		this();
		this.menuTitle = menuTitle;
	}
	
	public abstract void show();
			
	protected void addQuitOption()
	{
		options.add("q) Exit the application.");
	}
	
	protected void addGoBackOption()
	{
		options.add("b) Go back.");
	}
	
	protected void addOption(int number, String text)
	{
		options.add(number + ") " + text);
	}
	
	protected void exitApp() {
		System.out.println("Thank you for using YKZ BANK, exiting........");
		System.exit(0);
	}

	protected String readString(String prompt)
	{
		Scanner scan = new Scanner(System.in);
		String input = "";
		boolean isValid = false;
		
		while (!isValid)
		{
			Console.printLine(prompt + ": ");
			input = scan.nextLine();
			if (!input.equals(""))
			{
				return input;
			} else {
				Console.printLine(prompt + " cant be empty.");
			}
		}
		return "";		
	}

	protected void repeat()
	{
		Console.generateMenu(menuTitle, options);
	}
}
