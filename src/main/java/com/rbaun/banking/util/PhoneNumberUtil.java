package com.rbaun.banking.util;

public class PhoneNumberUtil {

    private static final String phoneNumberRegex = "^[\\d+()-]*$";

    /**
     * Check if a phone number is valid
     * @param phoneNumber the phone number to check
     * @return true if the phone number is valid, false otherwise
     */
    public static boolean isValid(String phoneNumber) {
        return phoneNumber.matches(phoneNumberRegex);
    }

}
