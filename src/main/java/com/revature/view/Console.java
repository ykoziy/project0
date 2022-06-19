package com.revature.view;

import java.util.List;

public class Console
{
	public static void print(Object obj)
    {
        System.out.print(obj.toString());
    }
	
    public static void printLine(Object obj)
    {
        System.out.println(obj.toString());
    }
    
    public static void showWelcomeScreen()
    {
        print("*");
        print(repeatStringNTimes("-", 70));
        print("*\n");
        String line = "|" + repeatStringNTimes(" ", 70) + "|";
        for (int i = 1; i <= 2; i++) {
            printLine(line);
        }
        print(welcomeLogo());
        for (int i = 1; i <= 6; i++) {
            printLine(line);
        }

        String lineWelcome = "|" + repeatStringNTimes(" ", 23) + "Welcome to YKZ Bank!" + repeatStringNTimes(" ", 27) + "|";
        String lineContinue = "|" + repeatStringNTimes(" ", 21) + "Press Enter to continue." + repeatStringNTimes(" ", 25) + "|";
        printLine(lineWelcome);
        printLine(lineContinue);
        for (int i = 1; i <= 1; i++) {
            printLine(line);
        }

        print("*");
        print(repeatStringNTimes("-", 70));
        print("*\n");
    }
    
    public static void generateMenu(String menuTitle, List<String> options)
    {
        printLine(menuTitle);
        for (int i = 0; i < options.size(); i++) {
            printLine(options.get(i));
        }
        printLine("\nSelect an option " + "(" + 1 + " - " + options.size() + ")" + " to continue:");
    }
    
    private static String repeatStringNTimes(String str, int n)
    {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            builder.append(str);
        }
        return builder.toString();
    }
    
    private static String welcomeLogo()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("|  ###  ### ### ###  #######       ######     ###    ##   ##  ### ###  |\n");
        builder.append("|   ##  ##   ## ##   ##  ##         ##  ##   ## ##   ###  ##   ## ##   |\n");
        builder.append("|    ####    ####       ##          ##  ##  ##   ##  #### ##   ####    |\n");
        builder.append("|     ##     ###       ##           #####   ##   ##  #######   ###     |\n");
        builder.append("|     ##     ####     ##            ##  ##  #######  ## ####   ####    |\n");
        builder.append("|     ##     ## ##   ##   ##        ##  ##  ##   ##  ##  ###   ## ##   |\n");
        builder.append("|    ####   ### ###  #######       ######   ##   ##  ##   ##  ### ###  |\n");
        return builder.toString();
    }
}
