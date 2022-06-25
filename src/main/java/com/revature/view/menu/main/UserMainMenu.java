package com.revature.view.menu.main;

import com.revature.Bank;
import com.revature.enums.UserRole;
import com.revature.view.Console;
import com.revature.view.menu.Menu;

public class UserMainMenu extends Menu
{
	private Bank bank;
	private UserRole role;
	
	public UserMainMenu(Bank bank)
	{
		super("Welcome back, " + bank.getCurrentUser().getFirstName() + " " + bank.getCurrentUser().getLastName());
		role = bank.getCurrentUser().getUserRole();
		this.bank = bank;
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
	
	private void generateMainMenu() 
	{
		if (role == UserRole.customer) {
			this.addOption(1, "Deposit");
			this.addOption(2, "Withdraw");
			this.addOption(3, "Transfer");
			this.addOption(4, "Open new account.");
			this.addQuitOption();			
		} else if (role == UserRole.employee) {
			this.addOption(1, "Get customer information.");
			this.addOption(2, "Get customer account(s).");
			this.addOption(3, "Approve/deny accounts.");
			this.addQuitOption();			
		} else if (role == UserRole.admin) {
			this.addOption(1, "Deposit");
			this.addOption(2, "Withdraw");
			this.addOption(3, "Transfer");			
			this.addOption(4, "Get customer information.");
			this.addOption(5, "Get customer account(s).");
			this.addOption(6, "Cancel accounts.");
			this.addQuitOption();
		}
	}
}
