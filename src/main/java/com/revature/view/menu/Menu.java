package com.revature.view.menu;

import java.util.ArrayList;
import java.util.List;

public abstract class Menu implements Interactable
{
	// subclasses must implement Interactable interface
	
	protected String menuTitle;
	protected List<String> options  = new ArrayList<>();
	
	public Menu() {
		
	}
	
	public Menu(String menuTitle)
	{
		this.menuTitle = menuTitle;
	}
	
	public abstract void show();
		
	protected void addQuitOption()
	{
		options.add("q) Exit the application.");
	}
	
	protected void addGoBackOption()
	{
		options.add("b) Bo back.");
	}
	
	protected void addOption(int number, String text)
	{
		options.add(number + ") " + text);
	}
	
	protected void exitApp() {
		System.out.println("Thank you for using YKZ BANK, exiting........");
		System.exit(0);
	}
}
