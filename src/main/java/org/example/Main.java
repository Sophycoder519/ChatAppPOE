package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login loginSystem = new Login();

        System.out.println("=== Registration ===");
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        String username;
        while (true) {
            System.out.print("Enter username (must contain '_' and max 5 chars): ");
            username = scanner.nextLine();
            String msg = loginSystem.getUsernameValidationMessage(username);
            System.out.println(msg);
            if (msg.equals("Username successfully captured.")) break;
        }

        String password;
        while (true) {
            System.out.print("Enter password (min 8 chars, 1 capital, 1 number, 1 special): ");
            password = scanner.nextLine();
            String msg = loginSystem.getPasswordValidationMessage(password);
            System.out.println(msg);
            if (msg.equals("Password successfully captured.")) break;
        }

        String phone;
        while (true) {
            System.out.print("Enter South African cell phone number (e.g., +27831234567): ");
            phone = scanner.nextLine();
            String msg = loginSystem.getPhoneValidationMessage(phone);
            System.out.println(msg);
            if (msg.equals("Cell phone number successfully added.")) break;
        }

        loginSystem.registerUser(firstName, lastName, username, password);
        loginSystem.registerPhone(phone);
        System.out.println("Registration successful!\n");

        System.out.println("=== Login ===");
        System.out.print("Enter username: ");
        String loginUsername = scanner.nextLine();
        System.out.print("Enter password: ");
        String loginPassword = scanner.nextLine();

        boolean loginSuccess = loginSystem.loginUser(loginUsername, loginPassword);
        String loginMessage = loginSystem.returnLoginStatus(loginSuccess, firstName, lastName);
        System.out.println(loginMessage);

        scanner.close();
    }
}