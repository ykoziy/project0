package com.revature.view.menu;

public class MenuFactory
{
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
			return new SignIn("Sign in.");
		}
		return null;
	}
}
