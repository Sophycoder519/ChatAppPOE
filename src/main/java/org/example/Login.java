
package org.example;

import java.util.regex.Pattern;

public class Login {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String phoneNumber;

    // ======================= Boolean Validation methods ===================//
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        if (password.length() < 8) return false;
        boolean hasUpper = false, hasDigit = false, hasSpecial = false;
        String specialChars = "!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?";
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else if (specialChars.indexOf(c) >= 0) hasSpecial = true;
        }
        return hasUpper && hasDigit && hasSpecial;
    }

    public boolean checkCellPhoneNumber(String phone) {
        String regex = "^\\+27\\d{9}$";
        return Pattern.matches(regex, phone);
    }

    // Message methods
    public String getUsernameValidationMessage(String username) {
        if (checkUserName(username)) return "Username successfully captured.";
        else return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
    }

    public String getPasswordValidationMessage(String password) {
        if (checkPasswordComplexity(password)) return "Password successfully captured.";
        else return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
    }

    public String getPhoneValidationMessage(String phone) {
        if (checkCellPhoneNumber(phone)) return "Cell phone number successfully added.";
        else return "Cell phone number incorrectly formatted or does not contain international code.";
    }

    // Registration methods
    public String registerUser(String firstName, String lastName, String username, String password) {
        String usernameMsg = getUsernameValidationMessage(username);
        String passwordMsg = getPasswordValidationMessage(password);
        if (!usernameMsg.equals("Username successfully captured.")) return usernameMsg;
        if (!passwordMsg.equals("Password successfully captured.")) return passwordMsg;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        return "Username and password successfully captured.";
    }

    public String registerPhone(String phone) {
        String phoneMsg = getPhoneValidationMessage(phone);
        if (phoneMsg.equals("Cell phone number successfully added.")) this.phoneNumber = phone;
        return phoneMsg;
    }

    // Login methods
    public boolean loginUser(String username, String password) {
        return this.username != null && this.username.equals(username) && this.password != null && this.password.equals(password);
    }

    public String returnLoginStatus(boolean loginSuccess, String firstName, String lastName) {
        if (loginSuccess) return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        else return "Username or password incorrect, please try again.";
    }
}