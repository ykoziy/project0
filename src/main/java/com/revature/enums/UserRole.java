package com.revature.enums;

public enum UserRole
{
	customer("customer"),
	admin("admin"),
	employee("employee");
	
	private final String text;

	private UserRole(String text)
	{
		this.text = text;
	}
	
	@Override
	public String toString()
	{
		return text;
	}
}
