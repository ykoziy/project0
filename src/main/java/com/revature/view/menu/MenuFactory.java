package com.revature.view.menu;

import com.revature.Bank;

public class MenuFactory
{
	private Bank bank;
	
	public MenuFactory(Bank bank)
	{
		this.bank = bank;
	}
	
	public Menu getMenu(String menuType)
	{
		if (menuType == null)
		{
			return null;
		}
		
		if (menuType.equalsIgnoreCase("welcome")) 
		{
			return new Welcome();
		} else if (menuType.equalsIgnoreCase("initial")) {
			return new Initial("Welcome to YKZ BANK!");
		} else if (menuType.equalsIgnoreCase("signin")) {
			return new SignIn("Sign in.", bank);
		} else if (menuType.equalsIgnoreCase("signup")) {
			return new SignUp("Sign up.", bank);
		}
		return null;
	}
}
