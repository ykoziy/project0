package com.revature.view.menu.main;

import java.util.List;
import java.util.Scanner;

import com.revature.Bank;
import com.revature.enums.Status;
import com.revature.models.Account;
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
				case '2':
					viewCustomerAccounts();
					repeat();
					input = scan.next().charAt(0);
					break;
				case '3':
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
	
	private void approveDenyAccounts()
	{
		Console.printLine("\nApprove/deny accounts menu.");
		List<Account> aList = null;
		Scanner scan = new Scanner(System.in);
		
		boolean isValid = false;
		String username = "";
		
		Console.printLine("\nPlease enter customers username.");
		username = readString("Username");
		
		if (!username.equals(""))
		{
			aList = this.getBank().getAccountsForUser(username);
			if (aList.size() > 0) {
				printAccounts(aList, username);
			} else {
				Console.printLine("\nUser with username: " + username + " does not exist. Or has no accounts attached.");
				return;
			}
		}
		
		long accountId = 0;
		Console.printLine("Please enter a valid account ID (id > 0)");
		accountId = getAccountId("Account ID");
		Account acc = this.getBank().getAccountById(accountId);
		if (acc != null)
		{
			Console.printLine("Selected account.");
			printAccount(acc);
			if (acc.getStatus() == Status.active) {
				Console.printLine("Can't deny/approve an account that is active.");
				return;
			}
			Console.printLine("\nDo you approve this account? ");
			Console.print("Type in yes to approve or no to deny: ");
			if(isYesSelected())
			{
				acc.setStatus(Status.active);
			} else {
				acc.setStatus(Status.closed);
			}
			
			if (!this.getBank().updateAccount(acc)) 
			{
				return;
			} 
			Console.printLine("Account status was changed.");
		} else {
			Console.printLine("\nUnable to get account.");
			return;
		}
	}
}
