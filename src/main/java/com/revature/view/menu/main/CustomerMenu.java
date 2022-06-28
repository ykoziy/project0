package com.revature.view.menu.main;

import java.util.List;
import java.util.Scanner;

import com.revature.Bank;
import com.revature.models.Account;
import com.revature.models.Person;
import com.revature.view.Console;

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
        this.addOption(6, "Open new joint account.");
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
					transfer();
					repeat();
					input = scan.next().charAt(0);
					break;
				case '5':
					createAccount();
					repeat();
					input = scan.next().charAt(0);
					break;
				case '6':
					createJointAccount();
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
	
	private boolean deposit() 
	{
		Console.printLine("\nSelect account and input amount for deposit.");
		long accountId = 0;
		double amount = 0;
		
		Console.printLine("Please enter a valid account ID (id > 0)");
		accountId = getAccountId("Account ID");

		Console.printLine("Please enter a valid amount (amount > 0)");
		amount = getMoney("Amount");
		
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
		long accountId = 0;
		double amount = 0;
		
		Console.printLine("Please enter a valid account ID (id > 0)");
		accountId = getAccountId("Account ID");

		Console.printLine("Please enter a valid amount (amount > 0)");
		amount = getMoney("Amount");
		
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
	
	private void transfer()
	{
		Console.printLine("\nSelect source and destination accounts,");
		Console.printLine("and enter transfer amount.");

		long srcId = 0;
		long destId = 0;
		double amount = 0;
		
		Console.printLine("Please enter a valid source account ID (id > 0)");
		srcId = getAccountId("Source Account ID");

		Console.printLine("Please enter a valid destination account ID (id > 0)");
		destId = getAccountId("Destination Account ID");
		
		Console.printLine("Please enter a valid amount (amount > 0)");
		amount = getMoney("Amount");

		long userId = this.getBank().getCurrentUser().getId();
		
		if (this.getBank().checkUserAccess(userId, srcId))
		{
			boolean result = this.getBank().transfer(srcId, destId, amount);
			if (result)
			{
				Console.printLine("\nYou have succesfully transfered: $" + amount + " ");
				Console.printLine("From account ID: " + srcId + " to the account ID: " + destId);
			} else {
				Console.printLine("\nTransfer failed. ");
			}
		} else {
			Console.printLine("\nTransfer failed, you dont have access to the source account.");			
		}
	}
		
	private void createAccount()
	{
		Console.printLine("\nThank you for choosing YKZ BANK, are you ready to creat an account?");
		Scanner scan = new Scanner(System.in);
		
		boolean isValid = false;
		double amount = 0;
		String username = "";
		
		Console.print("\nDo you wish to continue (yes or no)? ");
		if(!isYesSelected())
		{
			return;
		}
		
		Console.printLine("Please enter a valid initial deposit amount (amount > 0)");
		amount = getMoney("Initial deposit");
		
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
		
		if (this.getBank().createAccount(amount) != null) 
		{
			Console.printLine("Created account with balance of $" + amount);
			Console.printLine("It will take 2-5 business days to approve your account, thank you for choosing YKZ BANK.");
		} else {
			Console.printLine("Account creation failed.");
		}
	}
	
	private void createJointAccount()
	{
		Console.printLine("\nThank you for choosing YKZ BANK, are you ready to create a joint account?");
		Scanner scan = new Scanner(System.in);
		
		boolean isValid = false;
		double amount = 0;
		String username = "";
		Person person = null;
		
		Console.print("\nDo you wish to continue (yes or no)? ");
		if(!isYesSelected())
		{
			return;
		}
		
		while (!isValid)
		{
			Console.print("\nPlease enter another username: ");
			username = scan.nextLine();
			if (!username.equals(""))
			{
				person = this.getBank().getUser(username);
				if (person != null) {
					System.out.println(person.getInfo());
				} else {
					Console.printLine("\nUser with username: " + username + " does not exist.");
				}
				isValid = true;
			} else {
				Console.printLine("\nPlease enter a username.");
				scan.nextLine();
			}
		}
		
		Console.printLine("Please enter a valid initial deposit amount (amount > 0)");
		amount = getMoney("Initial deposit");
		
		isValid = false;
		
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
		
		Console.printLine("Creating joint account with initial deposit of $" + amount);
		Account newAccount = this.getBank().createAccount(amount);
		
		if (newAccount != null) 
		{
			if (this.getBank().addAccountUser(person.getId(), newAccount.getId()))
			{
				Console.printLine("Created joint account with balance of $" + amount);
				Console.printLine("It will take 2-5 business days to approve your account, thank you for choosing YKZ BANK.");
			}
		} else {
			Console.printLine("Account creation failed.");
		}
	}

}
