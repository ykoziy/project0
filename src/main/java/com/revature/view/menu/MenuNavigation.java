package com.revature.view.menu;

public class MenuNavigation
{

	private Menu menu;
	private MenuFactory fact;
	
	public MenuNavigation()
	{
		fact = new MenuFactory();
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
		}
		return 1;
	}
}
