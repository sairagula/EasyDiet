package com.akzholus.easydiet.time;

//import org.joda.time.DateTime;
//
//public class TimeSourceImpl implements TimeSource {
//    @Override
//    public DateTime getCurrentTime() {
//        return DateTime.now();
//    }
//}

import android.util.Log;

import java.util.Calendar;
import  java.util.Date;

public class  TimeSourceImpl implements TimeSource {

    @Override
    public Date getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        Log.d(TAG, " TIME: " + cal );

        return cal.getTime();
    }

}

