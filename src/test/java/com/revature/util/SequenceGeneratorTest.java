package com.revature.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SequenceGeneratorTest
{
	@Before
	public void resetCounter()
	{
		SequenceGenerator.setCounter(0L);
	}
	
	@Test
	public void getNextShouldReturnNextNumberAndIncrement()
	{
		long oldCount = SequenceGenerator.getCounter();
		
		long next = SequenceGenerator.getNext();
		
		long newCount = SequenceGenerator.getCounter();
		
		Assert.assertEquals(0L, oldCount);
		Assert.assertEquals(1L, next);
		Assert.assertEquals(1L, newCount);
	}
	
	@Test
	public void callingGetNextThreeTimeSetsCounterToThree()
	{
		
		long next = SequenceGenerator.getNext();
		next = SequenceGenerator.getNext();
		next = SequenceGenerator.getNext();
		Assert.assertEquals(3L, next);
	}
}
;