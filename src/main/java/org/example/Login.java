package org.example;

import java.util.regex.Pattern;

public class Login {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String phoneNumber;

    // ==================== BOOLEAN VALIDATION METHODS ==================== //

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
}