package com.akzholus.easydiet.common;

/**
 * Created by sairagul on 1/9/18.
 */

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Formatters {
    public static Locale DEFAULT_LOCALE = Locale.US;
    public static final SimpleDateFormat MONTH_DAY_US_FORMATTER = new SimpleDateFormat(
            "MMMM d", DEFAULT_LOCALE);

    public static final String SHORT_MONTH_DAY_PATTERN = "MMM d";

    public static String formatGoalDateTime(DateTime goalDate) {
        return goalDate.toString(SHORT_MONTH_DAY_PATTERN);
    }

    public static String formatFloat(float f) {
        if (f == (int) f) {
            return String.format(DEFAULT_LOCALE, "%d", (int) f);
        }
        return String.format(DEFAULT_LOCALE, "%.1f", f);
    }
}