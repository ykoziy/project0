package com.revature.view.menu.main;

import java.util.Scanner;

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
        this.addOption(3, "Approve/deny account.");
        this.addOption(4, "Add/remove account users.");
        this.addQuitOption();
	}

	@Override
	public String handleInput()
	{
		Scanner scan = new Scanner(System.in);
		char input = scan.next().charAt(0);
		
		boolean isValid = false;
		
		while (!isValid) {
			switch (input)
			{
				case '1':
					getCustomerInfo();
					repeat();
					input = scan.next().charAt(0);
					break;
				case '2':
					viewCustomerAccounts();
					repeat();
					input = scan.next().charAt(0);
					break;
				case '3':
					super.approveDenyAccounts();
					repeat();
					input = scan.next().charAt(0);
					break;	
				case '4':
					super.editAccountUsers();
					repeat();
					input = scan.next().charAt(0);
					break;					
				case 'q':
					this.exitApp();
					break;				
				default:
					input = scan.next().charAt(0);
					break;
			}
		}

		return "invalid";
	}
}
