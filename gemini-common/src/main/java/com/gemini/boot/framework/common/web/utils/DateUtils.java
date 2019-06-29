package com.gemini.boot.framework.common.web.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author 小明不读书
 * @date 2018-01-18
 */
public class DateUtils {

    public static String getDateTime(Date date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    /**
     * 获取前几天的日期（yyyy-MM-dd）
     *
     * @param past 前几天
     * @return
     */
    public static String getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        return result;
    }


    /**
     * 获取指定日期（yyyy-MM-dd）过去第几天的日期（yyyy-MM-dd）
     *
     * @param specifiedDate 指定日期（yyyy-MM-dd）
     * @param past          过去第几天
     * @return 日期（yyyy-MM-dd）
     */
    public static String getBeforeDateForPast(String specifiedDate, int past) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdf.parse(specifiedDate));
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date date = calendar.getTime();

        String result = sdf.format(date);
        return result;
    }

    /**
     * 得到当前时间的前N小时
     *  
     *
     * @param past
     * @return
     */
    public static String getBeforeHourForPast(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - past);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result = sdf.format(calendar.getTime());
        return result;
    }

    public static String getBeforeMinuteForPast(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - past);
        Date beforeD = calendar.getTime();
        String result = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(beforeD);
        return result;
    }

    public static void main(String[] strings) throws ParseException {
        String date = "2018-01-06";
        String result = getBeforeDateForPast(date, 7);
        System.out.println(result);

        String beforeHourForPast = getBeforeHourForPast(1);
        System.out.println(beforeHourForPast);

    }

}
