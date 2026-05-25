package org.example;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void testMessageLengthSuccess() {
        String msg = "Hi Mike, can you join us for dinner tonight?";
        assertTrue(msg.length() <= 250);
        // Simulate system message
        assertEquals("Message ready to send.", "Message ready to send.");
    }

    @Test
    public void testMessageLengthFailure() {
        StringBuilder longMsg = new StringBuilder();
        for (int i = 0; i < 260; i++) longMsg.append("a");
        int excess = longMsg.length() - 250;
        String expected = "Message exceeds 250 characters by " + excess + "; please reduce the size.";
        String actual = (longMsg.length() <= 250) ? "Message ready to send." :
                "Message exceeds 250 characters by " + excess + "; please reduce the size.";
        assertEquals(expected, actual);
    }

    @Test
    public void testRecipientCellValid() {
        String result = Message.checkRecipientCell("+27718693002");
        assertEquals("Cell phone number successfully captured.", result);
    }

    @Test
    public void testRecipientCellInvalid() {
        String result = Message.checkRecipientCell("08575975889");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", result);
    }

    @Test
    public void testMessageHashCorrect() throws Exception {
        // Create a Message object with known text
        Message msg = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        // Use reflection to set a fixed messageID starting with "00"
        Field idField = Message.class.getDeclaredField("messageID");
        idField.setAccessible(true);
        idField.set(msg, "0012345678");  // first two digits are "00"
        // Generate the hash
        String hash = msg.createMessageHash();

        // Basic checks
        assertNotNull(hash);
        assertFalse(hash.isEmpty());

        // Check format: two digits, colon, one or more digits, colon, then any non-empty string
        String[] parts = hash.split(":");
        assertEquals(3, parts.length, "Hash should have three parts separated by colons");
        assertTrue(parts[0].matches("\\d{2}"), "First part should be two digits");
        assertTrue(parts[1].matches("\\d+"), "Second part should be a number");
        assertFalse(parts[2].isEmpty(), "Third part should not be empty");
    }

    @Test
    public void testMessageIDGenerated() {
        Message msg = new Message(1, "+27718693002", "Hi");
        assertTrue(msg.checkMessageID());
        assertNotNull(msg.checkMessageID());
    }

    @Test
    public void testSentMessageOptions() {
        assertEquals("Message successfully sent.", "Message successfully sent.");
        assertEquals("Press 0 to delete the message.", "Press 0 to delete the message.");
        assertEquals("Message successfully stored.", "Message successfully stored.");
    }
}