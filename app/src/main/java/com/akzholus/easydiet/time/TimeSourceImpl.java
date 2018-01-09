package com.akzholus.easydiet.time;

/**
 * Created by sairagul on 1/9/18.
 */
import org.joda.time.DateTime;

public class TimeSourceImpl implements TimeSource {
    @Override
    public DateTime getCurrentTime() {
        return DateTime.now();
    }
}
