package org.example;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// JSON storage uses org.json library: https://github.com/stleary/JSON-java

public class Message {
    // Static accumulators (shared across all message instances)
    private static int totalMessagesSent = 0;
    private static List<String> allMessagesDetails = new ArrayList<>();

    // Instance fields
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    private boolean isSent;

    private Random random = new Random();

    // Constructor – called for each message
    public Message(int messageNumber, String recipient, String messageText) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
        this.isSent = false;
    }

    // Generate a random 10-digit number as string
    private String generateMessageID() {
        long id = 1_000_000_000L + (long)(random.nextDouble() * 9_000_000_000L);
        return String.valueOf(id);
    }

    // Check if message ID is not more than 10 characters (always true here)
    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    // Validate recipient cell number (reuse logic from Part 1)
    public static String checkRecipientCell(String phone) {
        if (phone == null) return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        if (phone.matches("^\\+27\\d{9}$"))
            return "Cell phone number successfully captured.";
        else
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }
    public String createMessageHash() {
        // Get first two digits of message ID
        String idPart = messageID.substring(0, 2);

        // Split message text into words (by spaces)
        String[] words = messageText.trim().split("\\s+");

        // First word
        String firstWord = words[0];

        // Last word (if only one word, use it as both)
        String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;

        // Combine and convert to uppercase
        String combined = firstWord + lastWord;
        String hash = idPart + ":" + messageNumber + ":" + combined;

        return hash.toUpperCase();
    }


    // User chooses: send, disregard, or store
    public String sentMessage(Scanner scanner) {
        System.out.println("\nChoose an option:");
        System.out.println("1. Send Message");
        System.out.println("2. Disregard Message");
        System.out.println("3. Store Message to send later");
        System.out.print("Enter choice (1-3): ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                isSent = true;
                totalMessagesSent++;
                storeMessageInJson("sent");
                allMessagesDetails.add(this.toString());
                return "Message successfully sent.";
            case 2:
                isSent = false;
                return "Press 0 to delete the message.";
            case 3:
                isSent = false;
                storeMessageInJson("stored");
                allMessagesDetails.add(this.toString());
                return "Message successfully stored.";
            default:
                return "Invalid choice. Message disregarded.";
        }
    }

    // Store message in JSON file (append to messages.json)
    private void storeMessageInJson(String status) {
        JSONObject messageJson = new JSONObject();
        messageJson.put("messageID", messageID);
        messageJson.put("messageNumber", messageNumber);
        messageJson.put("recipient", recipient);
        messageJson.put("messageText", messageText);
        messageJson.put("messageHash", messageHash);
        messageJson.put("status", status);
        messageJson.put("timestamp", System.currentTimeMillis());

        try {
            JSONArray array;
            if (Files.exists(Paths.get("messages.json"))) {
                String content = new String(Files.readAllBytes(Paths.get("messages.json")));
                array = new JSONArray(content);
            } else {
                array = new JSONArray();
            }
            array.put(messageJson);
            try (FileWriter file = new FileWriter("messages.json")) {
                file.write(array.toString(4));
            }
        } catch (IOException e) {
            System.err.println("Error storing message: " + e.getMessage());
        }
    }

    // Return all messages sent/stored during this session
    public String printMessages() {
        if (allMessagesDetails.isEmpty()) return "No messages have been sent or stored.";
        StringBuilder sb = new StringBuilder("\n--- All Messages ---\n");
        for (String msg : allMessagesDetails) {
            sb.append(msg).append("\n");
        }
        return sb.toString();
    }

    // Return total number of successfully sent messages
    public int returnTotalMessages() {
        return totalMessagesSent;
    }

    // Display full details of this message (after sending/storing)
    @Override
    public String toString() {
        return "Message ID: " + messageID +
                "\nMessage Hash: " + messageHash +
                "\nRecipient: " + recipient +
                "\nMessage: " + messageText +
                "\n------------------------";
    }
}
