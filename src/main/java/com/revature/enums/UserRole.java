package com.revature.enums;

public enum UserRole
{
	CUSTOMER("customer"),
	ADMIN("admin"),
	EMPLOYEE("employee");
	
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
