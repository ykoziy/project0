package com.revature.view.menu.main;

import java.util.List;
import java.util.Scanner;

import com.revature.Bank;
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
				case '3':
					withdraw();
					repeat();
					input = scan.next().charAt(0);
					break;
				case '4':
					Console.printLine("not implemented");
					repeat();
					input = scan.next().charAt(0);
					break;
				case '5':
					createAccount();
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
	
	private boolean deposit() 
	{
		Console.printLine("\nSelect account and input amount for deposit.");
		Scanner scan = new Scanner(System.in);
		
		boolean isValid = false;
		
		long accountId = 0;
		double amount = 0;
		
		while (!isValid)
		{
			Console.printLine("Please enter a valid account ID (id > 0)");
			Console.print("\nAccount ID: ");
			if (scan.hasNextLong())
			{
				accountId = scan.nextLong();
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
				amount = scan.nextDouble();
				isValid = true;
			} else {
				Console.printLine("\nYou did not enter a number for the account ID, try again.");
				scan.nextLine();
			}
		}
		
		long userId = this.getBank().getCurrentUser().getId();
		
		if (this.getBank().checkUserAccess(userId, accountId))
		{
			boolean result = this.getBank().deposit(accountId, amount);
			if (result)
			{
				Console.printLine("\nYou have succesfully deposited: $" + amount + " into account with ID: " + accountId);
				return true;
			} else {
				Console.printLine("\nDeposit failed.");
				return false;
			}
		}
		Console.printLine("\nDeposit failed, account access failure.");
		return false;
	}
	
	private void withdraw() 
	{
		Console.printLine("\nSelect account and input amount for withdraw.");
		Scanner scan = new Scanner(System.in);
		
		boolean isValid = false;
		
		long accountId = 0;
		double amount = 0;
		
		while (!isValid)
		{
			Console.printLine("Please enter a valid account ID (id > 0)");
			Console.print("\nAccount ID: ");
			if (scan.hasNextLong())
			{
				accountId = scan.nextLong();
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
				amount = scan.nextDouble();
				isValid = true;
			} else {
				Console.printLine("\nYou did not enter a number for the amount, try again.");
				scan.nextLine();
			}
		}
		
		long userId = this.getBank().getCurrentUser().getId();
		
		if (this.getBank().checkUserAccess(userId, accountId))
		{
			boolean result = this.getBank().withdraw(accountId, amount);
			if (result)
			{
				Console.printLine("\nYou have succesfully withdrew: $" + amount + " from account with ID: " + accountId);
			} else {
				Console.printLine("\nWithdraw failed. ");
			}
		} else {
			Console.printLine("\nWithdraw failed, account access failure.");			
		}
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
	
	private void createAccount()
	{
		Console.printLine("\nThank you for choosing YKZ BANK, are you ready to creat an account?");
		Scanner scan = new Scanner(System.in);
		
		boolean isValid = false;
		String choice = "";
		double amount = 0;
		String username = "";
		
		while (!isValid)
		{
			Console.print("\nDo you wish to continue (yes or no)? ");
			choice = scan.nextLine();
			if (choice.equalsIgnoreCase("yes"))
			{
				isValid = true;
			} else if (choice.equalsIgnoreCase("no")) {
				return;
			} else {
				Console.printLine("Invalid choice, type in yes or no.");
			}
		}
		
		isValid = false;
		
		while (!isValid)
		{
			Console.printLine("Please enter a valid initial deposit amount (amount > 0)");
			Console.print("\nInitial deposit: ");
			if (scan.hasNextDouble())
			{
				amount = scan.nextDouble();
				if (amount > 0) {
					isValid = true;
				} else {
					Console.printLine("Deposit amount must be > 0, try again.");
					scan.nextLine();
				}
			} else {
				Console.printLine("\nYou did not enter a number for the amount, try again.");
				scan.nextLine();
			}
		}
		
		isValid = false;
		scan.nextLine();
		
		while (!isValid)
		{
			Console.printLine("Confirm your username: ");
			username = scan.nextLine();
			if (!username.equals(""))
			{
				if (username.equals(this.getBank().getCurrentUser().getUserName()))
				{
					isValid = true;
				} else {
					Console.printLine("Confirm username, type in your username.");
				}				
			} else {
				Console.printLine("\nPlease enter your username.");
				scan.nextLine();
			}

		}
		
		Console.printLine("Creating account with initial deposit of $" + amount);
		
		if (this.getBank().createAccount(amount)) 
		{
			Console.printLine("Created account with balance of $" + amount);
			Console.printLine("It will take 2-5 business days to approve your account, thank you for choosing YKZ BANK.");
		} else {
			Console.printLine("Account creation failed.");
		}
	}

}
