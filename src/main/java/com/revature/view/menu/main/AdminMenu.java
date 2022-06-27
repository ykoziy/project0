package com.revature.view.menu.main;

import java.util.List;
import java.util.Scanner;

import com.revature.Bank;
import com.revature.models.Account;
import com.revature.models.Person;
import com.revature.view.Console;

public class AdminMenu extends MainMenu
{

	public AdminMenu(Bank bank)
	{
		super(bank);
	}

	@Override
	public void generateMainMenu()
	{
		this.addOption(1, "Get all usernames");
        this.addOption(2, "Deposit");
        this.addOption(3, "Withdraw");
        this.addOption(4, "Transfer");
        this.addOption(5, "Get customer information.");
        this.addOption(6, "Get customer account(s).");
        this.addOption(7, "Cancel accounts.");
        this.addOption(8, "Approve/deny account.");
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
					getAllUsernames();
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
					getCustomerInfo();
					repeat();
					input = scan.next().charAt(0);
					break;
				case '6':
					viewCustomerAccounts();
					repeat();
					input = scan.next().charAt(0);
					break;
				case '7':
					deleteAccount();
					repeat();
					input = scan.next().charAt(0);
					break;
				case '8':
					approveDenyAccounts();
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

	private boolean deposit()
	{
		Console.printLine("\nSelect account and input amount for deposit.");
		long accountId = 0;
		double amount = 0;

		Console.printLine("Please enter a valid account ID (id > 0)");
		accountId = getAccountId("Account ID");

		Console.printLine("Please enter a valid amount (amount > 0)");
		amount = getMoney("Amount");

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

	private void withdraw()
	{
		Console.printLine("\nSelect account and input amount for withdraw.");
		long accountId = 0;
		double amount = 0;

		Console.printLine("Please enter a valid account ID (id > 0)");
		accountId = getAccountId("Account ID");

		Console.printLine("Please enter a valid amount (amount > 0)");
		amount = getMoney("Amount");

		boolean result = this.getBank().withdraw(accountId, amount);
		if (result)
		{
			Console.printLine("\nYou have succesfully withdrew: $" + amount + " from account with ID: " + accountId);
		} else {
			Console.printLine("\nWithdraw failed. ");
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

		boolean result = this.getBank().transfer(srcId, destId, amount);
		if (result)
		{
			Console.printLine("\nYou have succesfully transfered: $" + amount + " ");
			Console.printLine("From account ID: " + srcId + " to the account ID: " + destId);
		} else {
			Console.printLine("\nTransfer failed. ");
		}
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

	private void viewCustomerAccounts()
	{
		Console.printLine("\nSelect customer to view their accounts.");
		Scanner scan = new Scanner(System.in);

		List<Account> aList = null;
		boolean isValid = false;

		String username = "";

		while (!isValid)
		{
			Console.print("\nPlease enter customers username: ");
			username = scan.nextLine();
			if (!username.equals(""))
			{
				aList = this.getBank().getAccountsForUser(username);
				if (aList.size() > 0) {
					printAccounts(aList, username);
				} else {
					Console.printLine("\nUser with username: " + username + " does not exist. Or ha no accounts attached.");
				}
				isValid = true;
			} else {
				Console.printLine("\nPlease enter a username.");
				scan.nextLine();
			}
		}
	}
	
	private void getAllUsernames()
	{
		Console.printLine("This might take a while....");
		List<Person> aList = null;		
		aList = this.getBank().getAllUsernames();
		if (aList.size() > 0) {
			printUsernames(aList);
		} else {
			Console.printLine("\nSomething wrong, cant get all users.");
		}
	}
	
	private void printUsernames(List<Person> users)
	{
		Console.printLine("");
		Console.printLine("========== Bank Users ==========");
		for (int i = 0; i < users.size(); i++)
		{
			Person p = users.get(i);
			String username = p.getUserName();
			String email = p.getEmail();
			long id = p.getId();
			String out = String.format("id: %d username: %s email: %s", id, username, email);
			Console.printLine(out);
		}
		Console.printLine("================================");
		Console.printLine("");		
	}
	
	private void deleteAccount()
	{
		long accountId = 0;
		
		Console.printLine("\nSelect account by ID to delete it.");
		Console.printLine("Please enter a valid account ID (id > 0)");
		accountId = getAccountId("Account ID");
		
		Console.printLine("\nWarning! Are you sure want to delete an account with ID of " + accountId);
		Console.printLine("\nUser account might still be in use.");
		Console.print("Type in yes or no: ");
		if(!isYesSelected())
		{
			return;
		}
		
		Console.printLine("\nAttempting to delete account with ID of " + accountId);
		
		if (this.getBank().deleteAccount(accountId))
		{
			Console.printLine("Deleted account with ID of " + accountId);
		} else {
			Console.printLine("Account deletion failed.");
		}
	}
	
	private void approveDenyAccounts()
	{
		Console.printLine("\nApprove/deny accounts menu.");
		Scanner scan = new Scanner(System.in);
		List<Account> aList = null;
		
		Console.printLine("\nDo you know account ID of a pending account? ");
		Console.print("Type in yes or no: ");
		if(isYesSelected())
		{
			Console.printLine("\nPrinting pending accounts.");
			aList = this.getBank().getAllAccounts(null);
			if (aList.size() > 0) {
				this.printAccounts(aList);
			} else {
				Console.printLine("\nSomething wrong, cant get all pending accounts.");
				return;
			}
		}
		
		// get account ID and get account
		// ask if approving or denying
		// update the status
	}
}
