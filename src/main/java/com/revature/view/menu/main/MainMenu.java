package com.revature.view.menu.main;

import java.util.List;
import java.util.Scanner;

import com.revature.Bank;
import com.revature.enums.UserRole;
import com.revature.models.Account;
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

	protected void printAccounts(List<Account> accounts, String username)
	{
		Console.printLine("");
		Console.printLine("========== Accounts for " + username +" ==========");
		for (int i = 0; i < accounts.size(); i++)
		{
			Account a = accounts.get(i);
			String accountNumber = a.getAccountNumber();
			String balanceStr = Console.getMoney(a.getBalance());
			String out = String.format("%d) Account number: %s Balance: %s Status: %s", i+1, accountNumber, balanceStr, a.getStatus());
			Console.printLine(out);
		}
		Console.printLine("================================");
		Console.printLine("");		
	}
	
	protected void printAccounts(List<Account> accounts)
	{
		Console.printLine("");
		Console.printLine("========== Accounts ===========");
		for (int i = 0; i < accounts.size(); i++)
		{
			Account a = accounts.get(i);
			String accountNumber = a.getAccountNumber();
			String balanceStr = Console.getMoney(a.getBalance());
			String out = String.format("%d) Account number: %s Balance: %s Status: %s", i+1, accountNumber, balanceStr, a.getStatus());
			Console.printLine(out);
		}
		Console.printLine("================================");
		Console.printLine("");		
	}
	
	protected void printAccount(Account a)
	{
		String accountNumber = a.getAccountNumber();
		String balanceStr = Console.getMoney(a.getBalance());
		String out = String.format("Account number: %s Balance: %s Status: %s", accountNumber, balanceStr, a.getStatus());
		Console.printLine(out);		
	}
}
