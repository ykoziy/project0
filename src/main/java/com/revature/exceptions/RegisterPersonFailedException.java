package com.revature.exceptions;

public class RegisterPersonFailedException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public RegisterPersonFailedException(String message)
	{
		super(message);
	}
}
