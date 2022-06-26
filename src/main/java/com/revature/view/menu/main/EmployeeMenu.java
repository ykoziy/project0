package com.revature.view.menu.main;

import java.util.Scanner;

import com.revature.Bank;
import com.revature.models.Person;
import com.revature.view.Console;

public class EmployeeMenu extends MainMenu
{
	public EmployeeMenu(Bank bank)
	{
		super(bank);
	}
	
	@Override
	public void generateMainMenu()
	{
        this.addOption(1, "Get customer information.");
        this.addOption(2, "Get customer account(s).");
        this.addOption(3, "Approve/deny account.");
        this.addQuitOption();
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
					getCustomerInfo();
					repeat();
					input = scan.next().charAt(0);
					break;				
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
	
	private void repeat()
	{
		Console.generateMenu(menuTitle, options);
	}
	
	private void getCustomerInfo() 
	{
		Console.printLine("\nSelect customer to view their information.");
		Scanner scan = new Scanner(System.in);
		
		boolean isValid = false;
		
		String username = "";
		
		while (!isValid)
		{
			Console.print("\nPlease enter customers username: ");
			username = scan.nextLine();
			if (!username.equals(""))
			{
				Person p = this.getBank().getUser(username);
				if (p != null) {
					System.out.println(p.getInfo());
				} else {
					Console.printLine("\nUser with username: " + username + " does not exist.");
				}
				isValid = true;
			} else {
				Console.printLine("\nPlease enter a username.");
				scan.nextLine();
			}
		}
	}
}
