package com.akzholus.easydiet.common;

//import org.joda.time.DateTime;

import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Calendar;
import java.text.DateFormat;

public class Formatters {
//    public static Locale DEFAULT_LOCALE = Locale.US;
//    public static final SimpleDateFormat MONTH_DAY_US_FORMATTER = new SimpleDateFormat(
//            "MMMM d YYYY", DEFAULT_LOCALE);
//
//    public static final String SHORT_MONTH_DAY_PATTERN = "MMM d YYYY";
//
//
//    public static String formatFloat(float f) {
//        if (f == (int) f) {
//            return String.format(DEFAULT_LOCALE, "%d", (int) f);
//        }
//        return String.format(DEFAULT_LOCALE, "%.1f", f);
//    }

    public static String formatGoalDateTime(Date goalDate) {
        DateFormat formatter = DateFormat.getDateInstance(); // Date and time
        String dateStr = formatter.format(goalDate);
        return dateStr.toString();
    }

}