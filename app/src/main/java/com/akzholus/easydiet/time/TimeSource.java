package com.akzholus.easydiet.time;

//import org.joda.time.DateTime;

import android.util.Log;

import java.util.Date;
import java.util.Calendar;


public interface TimeSource {
    public static final String TAG = "ViewDatabase";
        Date getCurrentTime();
}