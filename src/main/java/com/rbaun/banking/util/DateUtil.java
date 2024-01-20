package com.rbaun.banking.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static String format(LocalDate date) {
        return format(date, DEFAULT_FORMATTER);
    }

    public static String format(LocalDate date, DateTimeFormatter formatter) {
        return date.format(formatter);
    }

}
