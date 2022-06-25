package com.revature.view.menu.main;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import com.revature.Bank;
import com.revature.models.Person;
import com.revature.view.Console;
import com.revature.models.Account;

public class CustomerMenu extends MainMenu
{
	public CustomerMenu(Bank bank)
	{
		super(bank);
	}
	
	@Override
	public void generateMainMenu()
	{
		this.addOption(1, "View my accounts.");
        this.addOption(2, "Deposit");
        this.addOption(3, "Withdraw");
        this.addOption(4, "Transfer");
        this.addOption(5, "Open new account.");
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
					viewAccounts();
					repeat();
					input = scan.next().charAt(0);
					break;
				case '2':
					deposit();
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
	
	private void deposit() 
	{
		Console.printLine("\nSelect account and input amount for deposit.");
		Scanner scan = new Scanner(System.in);
		
		boolean isValid = false;
		
		long userId = 0;
		double amount = 0;
		
		while (!isValid)
		{
			Console.printLine("Please enter a valid account ID (id > 0)");
			Console.print("\nAccount ID: ");
			if (scan.hasNextLong())
			{
				userId = scan.nextLong();
				isValid = true;
			} else {
				Console.printLine("\nYou did not enter a number for the account ID, try again.");
				scan.nextLine();
			}
		}

		isValid = false;

		while (!isValid)
		{
			Console.printLine("Please enter a valid amount (amount > 0)");
			Console.print("\nAmount: ");
			if (scan.hasNextDouble())
			{
				amount = scan.nextLong();
				isValid = true;
			} else {
				Console.printLine("\nYou did not enter a number for the account ID, try again.");
				scan.nextLine();
			}
		}
		
		///do the deposit right here....	
	}
	
	private void viewAccounts()
	{
		this.getBank().setUserAccounts();
		List<Account> aList = this.getBank().getCurrentUser().getAccounts();
		Console.printLine("");
		Console.printLine("========== Accounts ==========");
		for (int i = 0; i < aList.size(); i++)
		{
			Account a = aList.get(i);
			String accountNumber = a.getAccountNumber();
			String balanceStr = Console.getMoney(a.getBalance());
			String out = String.format("%d) %s Balance: %s Status: %s", i+1, accountNumber, balanceStr, a.getStatus());
			Console.printLine(out);
		}
		Console.printLine("================================");
		Console.printLine("");
	}

}
