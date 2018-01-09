package com.akzholus.easydiet.common;

/**
 * Created by sairagul on 1/9/18.
 */

import com.akzholus.easydiet.time.TimeSource;
import org.joda.time.Duration;
import org.joda.time.LocalDate;

public class GoalProcessPolicy {
    public static Duration getDurationBetweenCheckins() {
        return Duration.standardDays(7);
    }
}
