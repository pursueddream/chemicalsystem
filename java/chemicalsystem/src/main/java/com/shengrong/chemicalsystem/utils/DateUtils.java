package com.shengrong.chemicalsystem.utils;

import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DateUtils {

    private static final String DEF_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static final String DEF_DAY_FORMAT = "yyyy-MM-dd";

    public static Timestamp getCurrentTime(){
        return new Timestamp(System.currentTimeMillis());
    }

    public static String format2time(Date date){
        return format(date, DEF_TIME_FORMAT);
    }

    public static String format2day(Date date){
        return format(date, DEF_DAY_FORMAT);
    }

    public static String format(Date date, String format){
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

}
