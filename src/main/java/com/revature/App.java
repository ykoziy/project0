package com.revature;

import com.revature.view.menu.MenuNavigation;

public class App
{
	public static void main(String... args)
	{
		Bank bank = new Bank();
		MenuNavigation nav = new MenuNavigation(bank);
		nav.start();
	}
}
