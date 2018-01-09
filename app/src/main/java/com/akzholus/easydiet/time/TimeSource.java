package com.akzholus.easydiet.time;

/**
 * Created by sairagul on 1/9/18.
 */

import org.joda.time.DateTime;

public interface TimeSource {
    DateTime getCurrentTime();
}