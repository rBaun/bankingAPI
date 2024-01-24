package com.rbaun.banking.util;

public class EmailUtil {

    /**
     * Check if an email address is valid
     * @param email the email address to check
     * @return true if the email address is valid, false otherwise
     */
    public static boolean isValidEmailAddress(String email) {
        // Regex from https://howtodoinjava.com/java/regex/java-regex-validate-email-address/
        String regex = "^(.+)@(.+)$";
        return email.matches(regex);
    }
}
