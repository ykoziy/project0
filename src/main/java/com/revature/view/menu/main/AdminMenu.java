package com.revature.view.menu.main;

import com.revature.Bank;

public class AdminMenu extends MainMenu
{

	public AdminMenu(Bank bank)
	{
		super(bank);
	}

	@Override
	public void generateMainMenu()
	{
        this.addOption(1, "Deposit");
        this.addOption(2, "Withdraw");
        this.addOption(3, "Transfer");          
        this.addOption(4, "Get customer information.");
        this.addOption(5, "Get customer account(s).");
        this.addOption(6, "Cancel accounts.");
        this.addQuitOption();
	}

	@Override
	public String handleInput()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
