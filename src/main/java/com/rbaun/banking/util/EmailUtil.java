package com.rbaun.banking.util;

public class EmailUtil {

    // Validate email address
    public static boolean isValidEmailAddress(String email) {
        // Regex from https://howtodoinjava.com/java/regex/java-regex-validate-email-address/
        String regex = "^(.+)@(.+)$";
        return email.matches(regex);
    }
}
