package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login loginSystem = new Login();

        // ===== PART 1: REGISTRATION ===== //
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

        // ===== PART 1: LOGIN ===== //
        System.out.println("=== Login ===");
        System.out.print("Enter username: ");
        String loginUsername = scanner.nextLine();
        System.out.print("Enter password: ");
        String loginPassword = scanner.nextLine();

        boolean loginSuccess = loginSystem.loginUser(loginUsername, loginPassword);
        String loginMessage = loginSystem.returnLoginStatus(loginSuccess, firstName, lastName);
        System.out.println(loginMessage);

        if (!loginSuccess) {
            scanner.close();
            return;  // exit if login fails
        }

        // ===== PART 2: MESSAGING ===== //
        System.out.println("\nWelcome to QuickChat.");

        boolean quit = false;
        while (!quit) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1. Send Messages");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Quit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("How many messages do you wish to enter? ");
                    int totalMessagesToEnter = scanner.nextInt();
                    scanner.nextLine();
                    for (int i = 1; i <= totalMessagesToEnter; i++) {
                        System.out.println("\n--- Message " + i + " ---");
                        String recipient;
                        while (true) {
                            System.out.print("Enter recipient cell number (+27...): ");
                            recipient = scanner.nextLine();
                            String msg = Message.checkRecipientCell(recipient);
                            System.out.println(msg);
                            if (msg.equals("Cell phone number successfully captured.")) break;
                        }
                        String messageText;
                        while (true) {
                            System.out.print("Enter your message (max 250 chars): ");
                            messageText = scanner.nextLine();
                            if (messageText.length() <= 250) {
                                System.out.println("Message ready to send.");
                                break;
                            } else {
                                int excess = messageText.length() - 250;
                                System.out.println("Message exceeds 250 characters by " + excess + "; please reduce the size.");
                            }
                        }
                        Message msgObj = new Message(i, recipient, messageText);
                        System.out.println("Generated Message Hash: " + msgObj.createMessageHash());
                        String result = msgObj.sentMessage(scanner);
                        System.out.println(result);
                        System.out.println(msgObj);
                    }
                    // Display total sent messages using a temporary instance
                    Message tempMsg = new Message(1, "+27711111111", "dummy");
                    System.out.println("\nTotal messages sent: " + tempMsg.returnTotalMessages());
                    break;
                case 2:
                    System.out.println("Coming Soon.");
                    break;
                case 3:
                    quit = true;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
        scanner.close();
    }
}