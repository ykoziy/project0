package com.revature.view.menu.main;

import java.util.List;
import java.util.Scanner;

import com.revature.Bank;
import com.revature.enums.Status;
import com.revature.enums.UserRole;
import com.revature.models.Account;
import com.revature.models.Person;
import com.revature.view.Console;
import com.revature.view.menu.Menu;

public abstract class MainMenu extends Menu
{
	private Bank bank;
	private UserRole role;

	public MainMenu(Bank bank)
	{
		super("Welcome back, " + bank.getCurrentUser().getFirstName() + " " + bank.getCurrentUser().getLastName());
		role = bank.getCurrentUser().getUserRole();
		this.setBank(bank);
	}
	
	@Override
	public void show()
	{
		generateMainMenu();
		Console.generateMenu(menuTitle, options);
	}

	public abstract void generateMainMenu();

	public Bank getBank()
	{
		return bank;
	}

	public void setBank(Bank bank)
	{
		this.bank = bank;
	}

	public UserRole getRole()
	{
		return role;
	}

	public void setRole(UserRole role)
	{
		this.role = role;
	}

	protected long getAccountId(String prompt)
	{
		Scanner scan = new Scanner(System.in);
		boolean isValid = false;
		long accountId = 0;
		while (!isValid)
		{
			Console.print("\n" + prompt + ": ");
			if (scan.hasNextLong())
			{
				accountId = scan.nextLong();
				if (accountId > 0)
				{
					isValid = true;
				} else
				{
					Console.printLine("\n" + prompt + " must be number greater than 0.");
					scan.nextLine();
				}
			} else
			{
				Console.printLine("\nYou did not enter a number for the " + prompt.toLowerCase() + ", try again.");
				scan.nextLine();
			}
		}
		return accountId;
	}

	protected double getMoney(String prompt)
	{
		Scanner scan = new Scanner(System.in);
		boolean isValid = false;
		double money = 0;
		while (!isValid)
		{
			Console.print("\n" + prompt + ": ");
			if (scan.hasNextDouble())
			{
				money = scan.nextDouble();
				if (money > 0.0)
				{
					isValid = true;
				} else
				{
					Console.printLine("\n" + prompt + " must be number greater than 0.");
					scan.nextLine();
				}
			} else
			{
				Console.printLine("\nYou did not enter a number for the " + prompt.toLowerCase() + ", try again.");
				scan.nextLine();
			}
		}
		return money;
	}

	protected boolean isYesSelected()
	{
		Scanner scan = new Scanner(System.in);
		String choice = "";
		boolean isValid = false;
		while (!isValid)
		{
			choice = scan.nextLine();
			if (choice.equalsIgnoreCase("yes"))
			{
				return true;
			} else if (choice.equalsIgnoreCase("no"))
			{
				return false;
			} else
			{
				Console.printLine("Invalid choice, type in yes or no.");
			}
		}
		return false;
	}

	protected void printAccounts(List<Account> accounts, String username)
	{
		Console.printLine("");
		Console.printLine("========== Accounts for " + username + " ==========");
		for (int i = 0; i < accounts.size(); i++)
		{
			Account a = accounts.get(i);
			String accountNumber = a.getAccountNumber();
			String balanceStr = Console.getMoney(a.getBalance());
			String out = String.format("%d) Account number: %s Balance: %s Status: %s", i + 1, accountNumber,
					balanceStr, a.getStatus());
			Console.printLine(out);
		}
		Console.printLine("================================");
		Console.printLine("");
	}

	protected void printAccounts(List<Account> accounts)
	{
		Console.printLine("");
		Console.printLine("========== Accounts ===========");
		for (int i = 0; i < accounts.size(); i++)
		{
			Account a = accounts.get(i);
			String accountNumber = a.getAccountNumber();
			String balanceStr = Console.getMoney(a.getBalance());
			String out = String.format("%d) Account number: %s Balance: %s Status: %s", i + 1, accountNumber,
					balanceStr, a.getStatus());
			Console.printLine(out);
		}
		Console.printLine("================================");
		Console.printLine("");
	}

	protected void printAccount(Account a)
	{
		String accountNumber = a.getAccountNumber();
		String balanceStr = Console.getMoney(a.getBalance());
		String out = String.format("Account number: %s Balance: %s Status: %s", accountNumber, balanceStr,
				a.getStatus());
		Console.printLine(out);
	}

	protected void approveDenyAccounts()
	{
		Console.printLine("\nApprove/deny an account.");
		List<Account> aList = null;
		Scanner scan = new Scanner(System.in);
		boolean isValid = false;
		String username = "";
		Console.printLine("\nPlease enter customers username.");
		username = readString("Username");
		if (!username.equals(""))
		{
			aList = this.getBank().getAccountsForUser(username);
			if (aList.size() > 0)
			{
				printAccounts(aList, username);
			} else
			{
				Console.printLine(
						"\nUser with username: " + username + " does not exist. Or has no accounts attached.");
				return;
			}
		}
		long accountId = 0;
		Console.printLine("Please enter a valid account ID (id > 0)");
		accountId = getAccountId("Account ID");
		Account acc = this.getBank().getAccountById(accountId);
		if (acc != null)
		{
			Console.printLine("Selected account.");
			printAccount(acc);
			if (acc.getStatus() == Status.active)
			{
				Console.printLine("Can't deny/approve an account that is active.");
				return;
			}
			Console.printLine("\nDo you approve this account? ");
			Console.print("Type in yes to approve or no to deny: ");
			if (isYesSelected())
			{
				acc.setStatus(Status.active);
			} else
			{
				acc.setStatus(Status.closed);
			}
			if (!this.getBank().updateAccount(acc))
			{
				Console.printLine("Account status was not changed.");
				return;
			}
			Console.printLine("Account status was changed.");
		} else
		{
			Console.printLine("\nUnable to get account.");
			return;
		}
	}

	protected void editAccountUsers()
	{
		Console.printLine("\nSelect a customer to remove/add to the account.");
		String username = "";
		Person person = null;
		Console.printLine("\nPlease enter customers username.");
		username = readString("Username");
		if (!username.equals(""))
		{
			person = this.getBank().getUser(username);
			if (person == null)
			{
				Console.printLine("\nUser with username: " + username + " does not exist.");
				return;
			}
		}
		long accountId = 0;
		Console.printLine("Please enter a valid account ID (id > 0)");
		accountId = getAccountId("Account ID");
		Account acc = this.getBank().getAccountById(accountId);
		if (acc != null)
		{
			boolean isChanged = false;
			Console.printLine("Selected account.");
			printAccount(acc);
			Console.printLine(
					"\nDo you wan to add/remove " + username + " access to the account with ID: " + accountId);
			Console.print("Type in yes to add or no to remove: ");
			if (isYesSelected())
			{
				isChanged = this.getBank().addAccountUser(person.getId(), accountId);
				if (!isChanged)
				{
					Console.printLine("Account user was not added.");
					return;
				}
			} else
			{
				isChanged = this.getBank().removeAccountUser(person.getId(), accountId);
				if (!isChanged)
				{
					Console.printLine("Account user was not removed.");
					return;
				}
			}
			if (isChanged)
			{
				Console.printLine("Account user access was changed.");
			}
		} else
		{
			Console.printLine("\nUnable to get account.");
			return;
		}
	}

	protected void getCustomerInfo()
	{
		Console.printLine("\nSelect customer to view their information.");
		Scanner scan = new Scanner(System.in);
		boolean isValid = false;
		String username = "";
		while (!isValid)
		{
			Console.print("\nPlease enter customers username: ");
			username = scan.nextLine();
			if (!username.equals(""))
			{
				Person p = this.getBank().getUser(username);
				if (p != null)
				{
					System.out.println(p.getInfo());
				} else
				{
					Console.printLine("\nUser with username: " + username + " does not exist.");
				}
				isValid = true;
			} else
			{
				Console.printLine("\nPlease enter a username.");
				scan.nextLine();
			}
		}
	}

	protected void viewCustomerAccounts()
	{
		Console.printLine("\nSelect a customer to view their accounts.");
		Scanner scan = new Scanner(System.in);
		List<Account> aList = null;
		boolean isValid = false;
		String username = "";
		while (!isValid)
		{
			Console.print("\nPlease enter customers username: ");
			username = scan.nextLine();
			if (!username.equals(""))
			{
				aList = this.getBank().getAccountsForUser(username);
				if (aList.size() > 0)
				{
					printAccounts(aList, username);
				} else
				{
					Console.printLine(
							"\nUser with username: " + username + " does not exist. Or has no accounts attached.");
				}
				isValid = true;
			} else
			{
				Console.printLine("\nPlease enter a username.");
				scan.nextLine();
			}
		}
	}
}
