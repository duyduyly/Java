package io_nio2.console;


import java.io.Console;
import java.util.Arrays;

public class MyConsoleExample {

    //Example, can't run directly in this project
    //create a file and cd into folder and run
    public static void main(String[] args) {
        Console console = System.console();

        if (console == null) {
            System.out.println("No console available");
        } else {
            char[] password = console.readPassword("Enter your password: ");
            char[] repeatPassword = "Alan123".toCharArray();

            if (Arrays.equals(password, repeatPassword)) {
                console.writer().println("-".repeat(30));
                console.format("Hello %s!%n", "Alan");
            } else {
                console.writer().println("-".repeat(30));
                console.writer().println("Wrong Password!");
                console.writer().flush();
            }
        }
    }
}
