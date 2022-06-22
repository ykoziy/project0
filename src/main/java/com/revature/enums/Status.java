package com.revature.enums;

public enum Status
{
	ACTIVE("active"),
	PENDING("pending"),
	CLOSED("closed");
	
	private final String text;

	private Status(String text)
	{
		this.text = text;
	}
	
	@Override
	public String toString()
	{
		return text;
	}
}
