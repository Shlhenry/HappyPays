package com.futeng.happypays.activity.sign;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by lane on 2017/9/14.
 * Email :cocoinut@163.com
 * Instructions :一句话说明当前类
 */

public class TestGetWeek {

    public static void getDate() {
        int currentMaxDays = getCurrentMonthDay();
        int maxDaysByDate = getDaysByYearMonth(2012, 11);
        String week = getDayOfWeekByDate("2012-12-25");
        System.out.println("本月天数：" + currentMaxDays);
        System.out.println("2012年11月天数：" + maxDaysByDate);
        System.out.println("2012-12-25是：" + week);
    }

    /**
     * 获取当月的 天数
     */
    public static int getCurrentMonthDay() {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 根据年 月 获取对应的月份 天数
     */
    public static int getDaysByYearMonth(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 根据日期 找到对应日期的 星期
     */
    public static String getDayOfWeekByDate(String date) {
        String dayOfweek = "-1";
        try {
            SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
            Date myDate = myFormatter.parse(date);
            SimpleDateFormat formatter = new SimpleDateFormat("E");
            String str = formatter.format(myDate);
            dayOfweek = str;
        } catch (Exception e) {
            System.out.println("错误!");
        }
        return dayOfweek;
    }

}
