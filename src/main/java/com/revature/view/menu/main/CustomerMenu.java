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
	
	private void viewAccounts()
	{
		this.getBank().setUserAccounts();
		List<Account> aList = this.getBank().getCurrentUser().getAccounts();
		Locale usa = new Locale("en", "US");
		NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(usa);
		
		
		Console.printLine("");
		Console.printLine("========== Accounts ==========");
		for (int i = 0; i < aList.size(); i++)
		{
			Account a = aList.get(i);
			String accountNumber = a.getAccountNumber();
			double balance = a.getBalance() / 100.0;
			String balanceStr = dollarFormat.format(balance);
			
			String out = String.format("%d) %s Balance: %s Status: %s", i+1, accountNumber, balanceStr, a.getStatus());
			Console.printLine(out);
		}
		Console.printLine("================================");
		Console.printLine("");
	}

}
