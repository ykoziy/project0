package com.revature.view.menu;

import com.revature.Bank;
import com.revature.enums.UserRole;
import com.revature.view.menu.main.AdminMenu;
import com.revature.view.menu.main.CustomerMenu;
import com.revature.view.menu.main.EmployeeMenu;

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
		} else if (menuType.equalsIgnoreCase("main_menu")) {
			return getMainMenu(bank.getCurrentUser().getUserRole());
		}
		return null;
	}
	
	
	private Menu getMainMenu(UserRole role)
	{
		if (role == UserRole.customer) {
			return new CustomerMenu(bank);
		} else if (role == UserRole.employee) {
			return new EmployeeMenu(bank);			
		} else if (role == UserRole.admin) {
			return new AdminMenu(bank);				
		}
		return null;
	}
}
