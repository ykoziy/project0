package com.revature.exceptions;

public class CreateAccountFailedException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public CreateAccountFailedException(String message)
	{
		super(message);
	}
}
