package com.revature.models;

import java.math.BigDecimal;

import com.revature.enums.Status;

public class Account
{
	private long id;
	private long ownerId;
	private BigDecimal balance;
	private Status status;
	
	private static final int NUMBER_LENGTH = 17;
	
	public Account() 
	{
		super();
	}

	public Account(long id, long ownerId, BigDecimal balance, Status status)
	{
		super();
		this.id = id;
		this.ownerId = ownerId;
		this.balance = balance;
		this.status = status;
	}
	
	public void setBalance(BigDecimal balance)
	{
		this.balance = balance;
	}
	
	public double getBalance()
	{
		return balance.doubleValue();
	}
	
	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public long getOwnerId()
	{
		return ownerId;
	}

	public void setOwnerId(long ownerId)
	{
		this.ownerId = ownerId;
	}

	public Status getStatus()
	{
		return status;
	}

	public void setStatus(Status status)
	{
		this.status = status;
	}

	public String getAccountNumber() {
		StringBuffer sb = new StringBuffer();
		String id = Long.toString(this.id);
		int padZeroCount = NUMBER_LENGTH - id.length();
		
		for (int i = 0; i < padZeroCount; i++) {
			sb.append("0");
		}
		
		sb.append(id);
		return sb.toString();
	}
}
