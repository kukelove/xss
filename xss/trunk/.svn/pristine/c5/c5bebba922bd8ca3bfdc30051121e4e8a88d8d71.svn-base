package com.xp.brushms.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by hzm on 2015/8/3.
 */
public class DateUtils {

//    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//    private static SimpleDateFormat dfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    private static SimpleDateFormat dfTimeTow = new SimpleDateFormat("yyyyMM-dd HH");
//    private static SimpleDateFormat dfTimeint = new SimpleDateFormat("yyyyMMddHHmmss");
//    private static SimpleDateFormat getDfTimeInThao() = new SimpleDateFormat("yyMMddHHmmssSSS");

    private static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>();

    public static SimpleDateFormat getDateFormat()
    {
        return new SimpleDateFormat("yyyy-MM-dd");
    }

    private static SimpleDateFormat getDfTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    private static SimpleDateFormat getDfTimeInThao() {
        return new SimpleDateFormat("yyMMddHHmmssSSS");
    }
    private static SimpleDateFormat getDf() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }
    private static SimpleDateFormat getDfTimeTow() {
        return new SimpleDateFormat("yyyyMM-dd HH");
    }
    private static SimpleDateFormat getDfTimeInt() {
        return new SimpleDateFormat("yyyyMMddHHmmss");
    }

    public static String getDateTimeStr(Date d) {

        return getDfTime().format(d);

    }

    public static String getNowDateString(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy/M/d");
        Calendar cal = Calendar.getInstance();
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return df.format(calendar.getTime());
    }


    public static String getDateTimehaoStr(Date d) {

        return getDfTimeInThao().format(d);

    }


    public static String getDateStr(Date d) {
        return getDf().format(d);
    }
    public static String getDateInt(Date d) {
        return getDfTimeInt().format(d);
    }
    public static String getDateHour(Date d) {
        return getDfTimeTow().format(d);
    }
    public static Date getToday() {

        Date ret = new Date();
        try {
            ret =  getDateFormat().parse(getDateFormat().format(ret));
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return ret;
    }

    public static Date todayStart() {

        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        Date start = calendar.getTime();
        return start;
    }

    public static Date todayEnd() {
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        Date start = calendar.getTime();
        return start;
    }

    public static Date parseDate(String dateStr) {
        try {
            return getDf().parse(dateStr);
        } catch (Exception exp) {
            exp.printStackTrace();
            return null;
        }
    }

    public static Date parseDateTime(String dateStr) {
        try {
            return getDfTime().parse(dateStr);
        } catch (Exception exp) {
            exp.printStackTrace();
            return null;
        }
    }

    public static Date getDate(Date d) {
        Date ret = null;
        try {
            ret = getDf().parse(getDf().format(d));
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return ret;
    }
    public static Date getHourFormatTime(Date d) {
        Date ret = null;
        try {
            ret = getDfTimeTow().parse(getDfTimeTow().format(d));
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return ret;
    }
    public static Date addDay(Date d, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.DATE, days);
        return c.getTime();
    }

    public static Date addMinute(Date d, int minutes) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.MINUTE, minutes);
        return c.getTime();

    }

    public static Date addMillisecond(Date d, int  millisecond) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.MILLISECOND, millisecond);
        return c.getTime();

    }

}
