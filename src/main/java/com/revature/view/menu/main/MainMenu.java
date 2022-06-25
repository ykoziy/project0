package com.revature.view.menu.main;

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
}
