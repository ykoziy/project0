package com.revature.view.menu.main;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import com.revature.Bank;
import com.revature.models.Person;
import com.revature.view.ConsoleOps;
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
		ConsoleOps.generateMenu(menuTitle, options);
	}
	
	private boolean deposit() 
	{
		ConsoleOps.printLine("\nSelect account and input amount for deposit.");
		Scanner scan = new Scanner(System.in);
		
		boolean isValid = false;
		
		long accountId = 0;
		double amount = 0;
		
		while (!isValid)
		{
			ConsoleOps.printLine("Please enter a valid account ID (id > 0)");
			ConsoleOps.print("\nAccount ID: ");
			if (scan.hasNextLong())
			{
				accountId = scan.nextLong();
				isValid = true;
			} else {
				ConsoleOps.printLine("\nYou did not enter a number for the account ID, try again.");
				scan.nextLine();
			}
		}

		isValid = false;

		while (!isValid)
		{
			ConsoleOps.printLine("Please enter a valid amount (amount > 0)");
			ConsoleOps.print("\nAmount: ");
			if (scan.hasNextDouble())
			{
				amount = scan.nextDouble();
				isValid = true;
			} else {
				ConsoleOps.printLine("\nYou did not enter a number for the account ID, try again.");
				scan.nextLine();
			}
		}
		
		long userId = this.getBank().getCurrentUser().getId();
		
		if (this.getBank().checkUserAccess(userId, accountId))
		{
			boolean result = this.getBank().deposit(accountId, amount);
			if (result)
			{
				ConsoleOps.printLine("\nYou have succesfully deposited: $" + amount + " into account with ID: " + accountId);
				return true;
			} else {
				ConsoleOps.printLine("\nDeposit failed.");
				return false;
			}
		}
		ConsoleOps.printLine("\nDeposit failed, account access failure.");
		return false;
	}
	
	private void viewAccounts()
	{
		this.getBank().setUserAccounts();
		List<Account> aList = this.getBank().getCurrentUser().getAccounts();
		ConsoleOps.printLine("");
		ConsoleOps.printLine("========== Accounts ==========");
		for (int i = 0; i < aList.size(); i++)
		{
			Account a = aList.get(i);
			String accountNumber = a.getAccountNumber();
			String balanceStr = ConsoleOps.getMoney(a.getBalance());
			String out = String.format("%d) %s Balance: %s Status: %s", i+1, accountNumber, balanceStr, a.getStatus());
			ConsoleOps.printLine(out);
		}
		ConsoleOps.printLine("================================");
		ConsoleOps.printLine("");
	}

}
