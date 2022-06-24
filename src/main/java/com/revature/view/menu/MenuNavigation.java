package com.revature.view.menu;

import com.revature.Bank;
import com.revature.view.Console;

public class MenuNavigation
{

	private Menu menu;
	private MenuFactory fact;
	private Bank bank;
	
	public MenuNavigation(Bank bank)
	{
		this.bank = bank;
		fact = new MenuFactory(this.bank);
	}
	
	public void start() 
	{
		menu = fact.getMenu("welcome");
		menu.show();
		String choice = menu.handleInput();
		if (choice.equals("next")) {
			initial();
		}
	}
	
	private void initial()
	{
		menu = fact.getMenu("initial");
		menu.show();
		String choice = menu.handleInput();
		if (choice.equals("signin")) {
			int result = signIn();
			if (result == 0) {
				initial();
			}
		} else if(choice.equals("signup")) {
			System.out.println("Sign up");
		}
	}
	
	private int signIn()
	{
		menu = fact.getMenu("signin");
		menu.show();
		String choice = menu.handleInput();
		if (choice.equals("back")) {
			return 0;
		} else if (choice.equals("next")) {
			String name = bank.getCurrentUser().getFirstName();
			Console.printLine(name + " welcome back, thank you for being YKZ BANK customer");
		}
		return 1;
	}
}
