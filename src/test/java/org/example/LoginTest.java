package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    private Login login = new Login();

    @Test
    public void testCheckUserName_Valid() {
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void testCheckUserName_Invalid() {
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }

    @Test
    public void testCheckPasswordComplexity_Valid() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testCheckPasswordComplexity_Invalid() {
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    public void testCheckCellPhoneNumber_Valid() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCheckCellPhoneNumber_Invalid() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }

    @Test
    public void testUsernameCorrectlyFormatted_LoginWelcome() {
        login.registerUser("John", "Doe", "kyl_1", "Ch&&sec@ke99!");
        login.registerPhone("+27838968976");
        boolean loginResult = login.loginUser("kyl_1", "Ch&&sec@ke99!");
        String actualMessage = login.returnLoginStatus(loginResult, "John", "Doe");
        assertEquals("Welcome John, Doe it is great to see you again.", actualMessage);
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        String message = login.getUsernameValidationMessage("kyle!!!!!!!");
        assertEquals("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.", message);
    }

    @Test
    public void testPasswordMeetsComplexity() {
        String message = login.getPasswordValidationMessage("Ch&&sec@ke99!");
        assertEquals("Password successfully captured.", message);
    }

    @Test
    public void testPasswordDoesNotMeetComplexity() {
        String message = login.getPasswordValidationMessage("password");
        assertEquals("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.", message);
    }

    @Test
    public void testCellPhoneCorrectlyFormatted() {
        String message = login.getPhoneValidationMessage("+27838968976");
        assertEquals("Cell phone number successfully added.", message);
    }

    @Test
    public void testCellPhoneIncorrectlyFormatted() {
        String message = login.getPhoneValidationMessage("08966553");
        assertEquals("Cell phone number incorrectly formatted or does not contain international code.", message);
    }
}