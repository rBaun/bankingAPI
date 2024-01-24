package com.rbaun.banking.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for date operations
 */
public class DateUtil {

    private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Format a date using the default formatter
     * @param date the date to format
     * @return the formatted date
     */
    public static String format(LocalDate date) {
        return format(date, DEFAULT_FORMATTER);
    }

    /**
     * Format a date using a custom formatter
     * @param date the date to format
     * @param formatter the formatter to use
     * @return the formatted date
     */
    public static String format(LocalDate date, DateTimeFormatter formatter) {
        return date.format(formatter);
    }

}
