package com.revature.view.menu;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WelcomeTest
{
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
	private final InputStream originalIn = System.in;
	
	private ByteArrayInputStream simulatedInput;
	
	private Menu menu;
	
	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	    simulatedInput = null;
	}
	
	@Before
	public void setObjects() {
		menu = new Welcome();
	}
	
	@After
	public void restoreStreams() {
	    System.setOut(originalOut);
	    System.setErr(originalErr);
	    System.setIn(originalIn);
	}
	
	@Test
	public void showShouldShowWelcomeScreen()
	{
		menu.show();
		Assert.assertNotEquals("", outContent.toString());
	}
	
	@Test
	public void shouldHandleInputEnterKey()
	{
		simulatedInput = new ByteArrayInputStream("\n".getBytes());
		System.setIn(simulatedInput);
		
		Assert.assertEquals("next",menu.handleInput());
	}
}
