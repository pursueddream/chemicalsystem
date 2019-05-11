package com.shengrong.chemicalsystem.utils;

import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DateUtils {

    private static final String DEF_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static Timestamp getCurrentTime(){
        return new Timestamp(System.currentTimeMillis());
    }

    public static String format(Date date){
        return format(date, DEF_DATE_FORMAT);
    }

    public static String format(Date date, String format){
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

}
