package com.revature.view.menu.main;

import com.revature.Bank;

public class CustomerMenu extends MainMenu
{

	public CustomerMenu(Bank bank)
	{
		super(bank);
	}
	
	@Override
	public void generateMainMenu()
	{
        this.addOption(1, "Deposit");
        this.addOption(2, "Withdraw");
        this.addOption(3, "Transfer");
        this.addOption(4, "Open new account.");
        this.addQuitOption();   
	}

	@Override
	public String handleInput()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
