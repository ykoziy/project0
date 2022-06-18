package com.revature.util;

public final class SequenceGenerator
{
	private static long counter = 0;

	public static long getCounter()
	{
		return counter;
	}

	public static void setCounter(long count)
	{
		counter = count;
	}
	
	public static long getNext() 
	{
		counter++;
		return counter;
	}
}
