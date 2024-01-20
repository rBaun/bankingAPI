package com.rbaun.banking.util;

public class PhoneNumberUtil {

    private static final String phoneNumberRegex = "^[\\d+()-]*$";

    public static boolean isValid(String phoneNumber) {
        return phoneNumber.matches(phoneNumberRegex);
    }

}
