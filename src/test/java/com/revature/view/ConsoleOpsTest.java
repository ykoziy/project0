package com.revature.view;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class ConsoleOpsTest
{
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
	
	@Before
	public void setUpStreams() {
		// reassign "standard" output stream
	    System.setOut(new PrintStream(outContent));
	    // reassign "standard" error stream
	    System.setErr(new PrintStream(errContent));
	}
	
	@After
	public void restoreStreams() {
	    System.setOut(originalOut);
	    System.setErr(originalErr);
	}
	
	@Test
	public void ptintShouldPrintALine()
	{
		ConsoleOps.print("test print");
		Assert.assertEquals("test print", outContent.toString());
	}
	
	@Test
	public void printLineShouldPrintANewLine()
	{
		ConsoleOps.printLine("test print");
		Assert.assertEquals("test print\r\n", outContent.toString());
	}
	
	@Test
	public void showWelcomeScreenShouldPrintWelcomeScreen()
	{
		ConsoleOps.showWelcomeScreen();
		Assert.assertNotEquals("", outContent.toString());
	}
	
	@Test
	public void generateMenuShouldShowValidMenu()
	{
        List<String> options = new ArrayList<>();
        options.add("1) Account Information");
        options.add("2) Deposit");
        options.add("3) Withdraw");
        options.add("4) Transfer");
        ConsoleOps.generateMenu("Customer Menu", options);
        Assert.assertTrue(outContent.toString().contains("Customer Menu"));
        Assert.assertTrue(outContent.toString().contains("1) Account Information"));
        Assert.assertTrue(outContent.toString().contains("2) Deposit"));
        Assert.assertTrue(outContent.toString().contains("3) Withdraw"));
        Assert.assertTrue(outContent.toString().contains("4) Transfer"));
	}
}
