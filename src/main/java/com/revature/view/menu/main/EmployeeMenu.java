package com.revature.view.menu.main;

import com.revature.Bank;

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
        this.addOption(3, "Approve/deny accounts.");
        this.addQuitOption();
	}

	@Override
	public String handleInput()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
