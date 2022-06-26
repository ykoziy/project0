package com.revature.view.menu.main;

import java.util.Scanner;

import com.revature.Bank;
import com.revature.enums.UserRole;
import com.revature.view.Console;
import com.revature.view.menu.Menu;

public abstract class MainMenu extends Menu
{
	private Bank bank;
	private UserRole role;
	
	public MainMenu(Bank bank)
	{
		super("Welcome back, " + bank.getCurrentUser().getFirstName() + " " + bank.getCurrentUser().getLastName());
		role = bank.getCurrentUser().getUserRole();
		this.setBank(bank);
	}

	@Override
	public String handleInput()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void show()
	{
		generateMainMenu();
		Console.generateMenu(menuTitle, options);
	}
	
	public abstract void generateMainMenu();

	public Bank getBank()
	{
		return bank;
	}
	
	public void setBank(Bank bank)
	{
		this.bank = bank;
	}
	
	public UserRole getRole()
	{
		return role;
	}
	
	public void setRole(UserRole role)
	{
		this.role = role;
	}

	protected long getAccountId(String prompt)
	{
		Scanner scan = new Scanner(System.in);
		boolean isValid = false;
		long accountId = 0;
		
		while (!isValid)
		{
			Console.print("\n" + prompt + ": ");
			if (scan.hasNextLong())
			{
				accountId = scan.nextLong();
				if (accountId > 0)
				{
					isValid = true;
				} else {
					Console.printLine("\n"+ prompt
							+ " must be number greater than 0.");
					scan.nextLine();
				}
			} else {
				Console.printLine("\nYou did not enter a number for the "
						+ prompt.toLowerCase()
						+ ", try again.");
				scan.nextLine();
			}
		}
		return accountId;
	}

	protected double getMoney(String prompt)
	{
		Scanner scan = new Scanner(System.in);
		boolean isValid = false;
		double money = 0;
		
		while (!isValid)
		{
			Console.print("\n"
					+ prompt
					+ ": ");
			if (scan.hasNextDouble())
			{
				money = scan.nextDouble();
				if (money > 0.0)
				{
					isValid = true;
				} else {
					Console.printLine("\n"
							+ prompt
							+ " must be number greater than 0.");
					scan.nextLine();
				}
			} else {
				Console.printLine("\nYou did not enter a number for the "
						+ prompt.toLowerCase()
						+ ", try again.");
				scan.nextLine();
			}
		}
		return money;
	}

	protected boolean isYesSelected()
	{
		Scanner scan = new Scanner(System.in);
		String choice = "";
		boolean isValid = false;
		
		while (!isValid)
		{
			Console.printLine("User input: ");
			choice = scan.nextLine();
			if (choice.equalsIgnoreCase("yes"))
			{
				return true;
			} else if (choice.equalsIgnoreCase("no")) {
				return false;
			} else {
				Console.printLine("Invalid choice, type in yes or no.");
			}
		}
		return false;
	}
}
