package com.fjrcloud.lin.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lin on 2016/8/19.
 */
public class DateUtil {

    public static SimpleDateFormat mSimpleDateFormat = null;

    public static String getCurrentDate() {
        Date d = new Date();
        mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return mSimpleDateFormat.format(d);
    }
        public static long getCurrentDate2() {
            String date = getCurrentDate();
            long time = getStringtoDate(date);
            return time;
        }

    public static String getDateToString(long time) {
        Date d = new Date(time);
        mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return  mSimpleDateFormat.format(d);
    }

    public static String getDateToString2(long time) {
        Date d = new Date(time);
        mSimpleDateFormat = new SimpleDateFormat("MM-dd  HH:mm");
        return  mSimpleDateFormat.format(d);
    }

    public static String getDateToString3(long time) {
        Date d = new Date(time);
        mSimpleDateFormat = new SimpleDateFormat("MM-dd");
        return  mSimpleDateFormat.format(d);
    }

    public static String getDateToString4(long time) {
        Date d = new Date(time);
        mSimpleDateFormat = new SimpleDateFormat("HH:mm");
        return  mSimpleDateFormat.format(d);
    }

    public static long getStringtoDate(String time) {
        mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = mSimpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static long getStringtoDate2(String time) {
        mSimpleDateFormat = new SimpleDateFormat("MM-dd");
        Date date = new Date();
        try {
            date = mSimpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }
}
