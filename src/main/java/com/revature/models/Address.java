package com.revature.models;

import java.io.Serializable;

public class Address implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private long id;
	
	private long userId;
	
	private String street;
	
	private String city;
	
	private String state;
	
	private String zip;

	public Address()
	{
		super();
	}
	
	public Address(String street, String city, String state, String zip)
	{
		super();
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	public Address(long userId, String street, String city, String state, String zip)
	{
		super();
		this.userId = userId;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	public long getUserId()
	{
		return userId;
	}

	public void setUserId(long userId)
	{
		this.userId = userId;
	}

	public String getStreet()
	{
		return street;
	}

	public void setStreet(String street)
	{
		this.street = street;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public String getZip()
	{
		return zip;
	}

	public void setZip(String zip)
	{
		this.zip = zip;
	}

	@Override
	public String toString()
	{
		return String.format("%s, %s, %s %s", street, city, state, zip);
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}
	
	
}
