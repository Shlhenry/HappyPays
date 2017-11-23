package com.futeng.happypays.activity.kit;


import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * author: MaCa
 * time  : 2016/11/15 10:59
 * note  : TODO
 */
public class DateUtils {
    /**
     * 获取当前年份
     *
     * @return
     */
    public static int getYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * 获取当前月份
     *
     * @return
     */
    public static int getMonth() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前日期是该月的第几天
     *
     * @return
     */
    public static int getCurrentDayOfMonth() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当前年月日分
     */
    public static String getThisYMD() {
        Calendar calendar = Calendar.getInstance();
        String thisymd = calendar.get(Calendar.YEAR) + "_" +
                (calendar.get(Calendar.MONTH) + 1) + "_" +
                calendar.get(Calendar.DAY_OF_MONTH) + "_" + getHour() + ":" + getMinute();
        return thisymd;//二十四小时制
    }

    /**
     * 获取当前日期String
     */
    public static String getThisDate() {
        Calendar calendar = Calendar.getInstance();
        MacaDateBean date = new MacaDateBean();
        date.setYear(calendar.get(Calendar.YEAR));
        date.setMonth(calendar.get(Calendar.MONTH) + 1);
        date.setDay(calendar.get(Calendar.DAY_OF_MONTH));
        date.setHour(calendar.get(Calendar.HOUR_OF_DAY));
        date.setMin(calendar.get(Calendar.MINUTE));
        date.setSs(calendar.get(Calendar.SECOND));
        return date.getYMDStr() + " " + date.getHMSStr();//二十四小时制
    }

    /**
     * 获取当前日期Date类型
     */
    public static MacaDateBean getThisDateOfObject() {
        Calendar calendar = Calendar.getInstance();
        MacaDateBean date = new MacaDateBean();
        date.setYear(calendar.get(Calendar.YEAR));
        date.setMonth(calendar.get(Calendar.MONTH) + 1);
        date.setDay(calendar.get(Calendar.DAY_OF_MONTH));
        date.setHour(calendar.get(Calendar.HOUR_OF_DAY));
        date.setMin(calendar.get(Calendar.MINUTE));
        date.setSs(calendar.get(Calendar.SECOND));
        return date;//二十四小时制
    }

    /**
     * 获取当前日期时间戳
     */
    public static long getThisDateOfUnix(boolean isSecond) {
        if (isSecond) {
            return System.currentTimeMillis() / 1000;//秒级
        }
        return System.currentTimeMillis();//毫秒级
    }


    /**
     * 获取前后多少天的日期
     */
    public static MacaDateBean getLastNextDate(MacaDateBean thisDate, int count) {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(thisDate.getYear(), thisDate.getMonth() - 1, thisDate.getDay() + count);
        MacaDateBean sucDate = new MacaDateBean();
        sucDate.setYear(calendar.get(Calendar.YEAR));
        sucDate.setMonth(calendar.get(Calendar.MONTH) + 1);
        sucDate.setDay(calendar.get(Calendar.DAY_OF_MONTH));
        Log.e("lastnextdate", sucDate.getYMDStr());
        return sucDate;
    }

    /**
     * 获取前后多少月的日期
     */
    public static MacaDateBean getLastNextMonth(MacaDateBean thisDate, int count) {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(thisDate.getYear(), thisDate.getMonth() - 1 + count, thisDate.getDay());
        MacaDateBean sucDate = new MacaDateBean();
        sucDate.setYear(calendar.get(Calendar.YEAR));
        sucDate.setMonth(calendar.get(Calendar.MONTH) + 1);
        sucDate.setDay(calendar.get(Calendar.DAY_OF_MONTH));
        Log.e("lastnextdate", sucDate.getYMDStr());
        return sucDate;
    }

    /**
     * 获取前后多少年的日期
     */
    public static MacaDateBean getLastNextYear(MacaDateBean thisDate, int count) {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(thisDate.getYear() + count, thisDate.getMonth() - 1, thisDate.getDay());
        MacaDateBean sucDate = new MacaDateBean();
        sucDate.setYear(calendar.get(Calendar.YEAR));
        sucDate.setMonth(calendar.get(Calendar.MONTH) + 1);
        sucDate.setDay(calendar.get(Calendar.DAY_OF_MONTH));
        Log.e("lastnextyear", sucDate.getYMDStr());
        return sucDate;
    }

    /**
     * 获取前后多少分钟的时间
     */
    public static MacaDateBean getLastNextTime(MacaDateBean thisDate, int count) {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(thisDate.getYear(), thisDate.getMonth() - 1, thisDate.getDay(), thisDate.getHour(), thisDate.getMin() + count, 0);
        MacaDateBean sucDate = new MacaDateBean();
        sucDate.setHour(calendar.get(Calendar.HOUR_OF_DAY));
        sucDate.setMin(calendar.get(Calendar.MINUTE));
        Log.e("lastnexttime", sucDate.getHMStr());
        return sucDate;
    }

    /**
     * 比较大小
     *
     * @return
     */

    public static boolean aWithB(MacaDateBean a, MacaDateBean b) {
        boolean big = false;
        if (a.getYear() > b.getYear()) {
            big = true;
        } else if (a.getYear() < b.getYear()) {
            big = false;
        } else {
            if (a.getMonth() > b.getMonth()) {
                big = true;
            } else if (a.getMonth() < b.getMonth()) {
                big = false;
            } else {
                if (a.getDay() > b.getDay()) {
                    big = true;
                } else if (a.getDay() < b.getDay()) {
                    big = false;
                }
            }
        }
        return big;
    }

    /**
     * 获取当前日期是该周的第几天
     *
     * @return
     */
    public static int getCurrentDayOfWeek() {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取某个日期是该周的第几天
     *
     * @return
     */
    public static String getSomeCurrentDayOfWeek(MacaDateBean date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(date.getYear(), date.getMonth() - 1, date.getDay());
        String week = "";
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                week = "周日";
                break;
            case 2:
                week = "周一";
                break;
            case 3:
                week = "周二";
                break;
            case 4:
                week = "周三";
                break;
            case 5:
                week = "周四";
                break;
            case 6:
                week = "周五";
                break;
            case 7:
                week = "周六";
                break;

        }
        return week;
    }

    /**
     * 获取某个日期是该周的第几天
     *
     * @return
     */
    public static String getSomeCurrentDayOfWeekEng(MacaDateBean date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(date.getYear(), date.getMonth() - 1, date.getDay());
        String week = "";
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                week = "SUN";
                break;
            case 2:
                week = "MON";
                break;
            case 3:
                week = "TUE";
                break;
            case 4:
                week = "WED";
                break;
            case 5:
                week = "THU";
                break;
            case 6:
                week = "FRI";
                break;
            case 7:
                week = "SAT";
                break;

        }
        return week;
    }

    /**
     * 获取当前是几点
     */
    public static int getHour() {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);//二十四小时制
    }

    /**
     * 获取当前是几分
     *
     * @return
     */
    public static int getMinute() {
        return Calendar.getInstance().get(Calendar.MINUTE);
    }

    /**
     * 获取当前秒
     *
     * @return
     */
    public static int getSecond() {
        return Calendar.getInstance().get(Calendar.SECOND);
    }

    /**
     * 根据传入的年份和月份，获取当前月份的日历分布
     *
     * @param year
     * @param month
     * @return
     */
    public static int[][] getDayOfMonthFormat(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);//设置时间为每月的第一天
        //设置日历格式数组,6行7列
        int days[][] = new int[6][7];
        //设置该月的第一天是周几
        int daysOfFirstWeek = calendar.get(Calendar.DAY_OF_WEEK);
        //设置本月有多少天
        int daysOfMonth = getDaysOfMonth(year, month);
        //设置上个月有多少天
        int daysOfLastMonth = getLastDaysOfMonth(year, month);
        int dayNum = 1;
        int nextDayNum = 1;
        //将日期格式填充数组
        for (int i = 0; i < days.length; i++) {
            for (int j = 0; j < days[i].length; j++) {
                if (i == 0 && j < daysOfFirstWeek - 1) {
                    days[i][j] = daysOfLastMonth - daysOfFirstWeek + 2 + j;
                } else if (dayNum <= daysOfMonth) {
                    days[i][j] = dayNum++;
                } else {
                    days[i][j] = nextDayNum++;
                }
            }
        }
        return days;
    }

    /**
     * 根据传入的年份和月份，判断上一个有多少天
     *
     * @param year
     * @param month
     * @return
     */
    public static int getLastDaysOfMonth(int year, int month) {
        int lastDaysOfMonth = 0;
        if (month == 1) {
            lastDaysOfMonth = getDaysOfMonth(year - 1, 12);
        } else {
            lastDaysOfMonth = getDaysOfMonth(year, month - 1);
        }
        return lastDaysOfMonth;
    }

    /**
     * 根据传入的年份和月份，判断当前月有多少天
     *
     * @param year
     * @param month
     * @return
     */
    public static int getDaysOfMonth(int year, int month) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 2:
                if (isLeap(year)) {
                    return 29;
                } else {
                    return 28;
                }
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
        }
        return -1;
    }

    /**
     * 判断是否为闰年
     *
     * @param year
     * @return
     */
    public static boolean isLeap(int year) {
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            return true;
        }
        return false;
    }


    //秒转时分
    public static String secondToHMSStr(int second) {
        String str = "";
        if (second >= 3600) {
            int hout = second / 3600;
            int min = (second / 60) % 60;
            int sec = second % 60;
            String hourStr = hout + "";
            String minStr = min + "";
            String secStr = sec + "";
            if (hout < 10) {
                hourStr = "0" + hout;
            }
            if (min < 10) {
                minStr = "0" + min;
            }
            if (sec < 10) {
                secStr = "0" + sec;
            }
            str = hourStr + ":" + minStr + ":" + secStr;
        } else {
            int min = second / 60;
            int sec = second % 60;
            String minStr = min + "";
            String secStr = sec + "";
            if (min < 10) {
                minStr = "0" + min;
            }
            if (sec < 10) {
                secStr = "0" + sec;
            }
            str = minStr + ":" + secStr;
        }
        return str;
    }

    //秒转时分秒
    public static String secondToHHMMSS(int second) {
        String str = "";
        if (second >= 3600) {
            int hout = second / 3600;
            int min = (second / 60) % 60;
            int sec = second % 60;
            String hourStr = hout + "";
            String minStr = min + "";
            String secStr = sec + "";
            if (hout < 10) {
                hourStr = "0" + hout;
            }
            if (min < 10) {
                minStr = "0" + min;
            }
            if (sec < 10) {
                secStr = "0" + sec;
            }
            str = hourStr + "小时" + minStr + "分" + secStr + "秒";
        } else {
            int min = second / 60;
            int sec = second % 60;
            String minStr = min + "";
            String secStr = sec + "";
            if (min < 10) {
                minStr = "0" + min;
            }
            if (sec < 10) {
                secStr = "0" + sec;
            }
            str = minStr + "分" + secStr + "秒";
        }
        return str;
    }

    //字符串转时间戳
    public static String getDateToUnixTime(String timeString) {

        timeString = timeString.replace("-", "");
        timeString = timeString.replace(":", "");
        timeString = timeString.replace(" ", "");
        String timeStamp = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINESE);
        Date d;
        try {
            d = sdf.parse(timeString);
            long l = d.getTime() / 1000;
            timeStamp = String.valueOf(l);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeStamp;
    }

    //时间戳转字符串
    public static String getUnixToDateStrYMDHMS(String timeStamp) {
        if (timeStamp.contains("-") || timeStamp.isEmpty()) {
            return timeStamp;
        }
        String timeString = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        long l = Long.valueOf(timeStamp);
        l = l * 1000;
        timeString = sdf.format(new Date(l));//单位秒
        return timeString;
    }

    //时间戳转MacaBean
    public static MacaDateBean getUnixToDateStrDateBean(String timeStamp) {
        if (timeStamp.contains("-") || timeStamp.isEmpty()) {
            return new MacaDateBean();
        }
        String timeString = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        long l = Long.valueOf(timeStamp);
        l = l * 1000;
        timeString = sdf.format(new Date(l));//单位秒
        String[] timearr = timeString.split("-");
        MacaDateBean macaDateBean = new MacaDateBean();
        macaDateBean.setYear(Integer.valueOf(timearr[0]));
        macaDateBean.setMonth(Integer.valueOf(timearr[1]));
        macaDateBean.setDay(Integer.valueOf(timearr[2]));
        macaDateBean.setHour(Integer.valueOf(timearr[3]));
        macaDateBean.setMin(Integer.valueOf(timearr[4]));
        macaDateBean.setSs(Integer.valueOf(timearr[5]));
        return macaDateBean;
    }

    //时间戳转字符串
    public static String getUnixToDateStrYMD(String timeStamp) {
        if (timeStamp.contains("-") || timeStamp.isEmpty()) {
            return timeStamp;
        }
        String timeString = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long l = Long.valueOf(timeStamp);
        l = l * 1000;
        timeString = sdf.format(new Date(l));//单位秒
        return timeString;
    }

    //时间戳转字符串
    public static String getUnixToDateStrYMDHM(String timeStamp) {
        if (timeStamp.contains("-") || timeStamp.isEmpty()) {
            return timeStamp;
        }
        String timeString = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long l = Long.valueOf(timeStamp);
        l = l * 1000;
        timeString = sdf.format(new Date(l));//单位秒
        return timeString;
    }

    //时间戳转字符串
    public static String getUnixToDateStrD(String timeStamp) {
        if (timeStamp.contains("-") || timeStamp.isEmpty()) {
            return timeStamp;
        }
        String timeString = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        long l = Long.valueOf(timeStamp);
        l = l * 1000;
        timeString = sdf.format(new Date(l));//单位秒
        return timeString;
    }

    //时间戳转字符串
    public static String getUnixToDateStrHm(String timeStamp) {
        if (timeStamp.contains("-") || timeStamp.isEmpty()) {
            return timeStamp;
        }
        String timeString = null;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        long l = Long.valueOf(timeStamp);
        l = l * 1000;
        timeString = sdf.format(new Date(l));//单位秒
        return timeString;
    }

    //时间戳转字符串
    public static String getUnixToDateStrMDHMSFile(String timeStamp) {
        if (timeStamp.contains("-") || timeStamp.isEmpty()) {
            return timeStamp;
        }
        String timeString = null;
        SimpleDateFormat sdf = new SimpleDateFormat("MMdd_HHmmss");
        long l = Long.valueOf(timeStamp);
        l = l * 1000;
        timeString = sdf.format(new Date(l));//单位秒
        return timeString;
    }

    //时间戳转字符串
    public static String getUnixToDateStrMDHM(String timeStamp) {
        if (timeStamp.contains("-") || timeStamp.isEmpty()) {
            return timeStamp;
        }
        String timeString = null;
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
        long l = Long.valueOf(timeStamp);
        l = l * 1000;
        timeString = sdf.format(new Date(l));//单位秒
        return timeString;
    }


    public static class MacaDateBean {
        private int year;
        private int month;
        private int day;
        private String yearStr;//1997
        private String monthStr;//07
        private String dayStr;//16
        private String YMDStr;//19970716
        private int hour;
        private int min;
        private int ss;
        private String hourStr;//08
        private String minStr;//06
        private String ssStr;//03
        private String HMStr;//08:03
        private String HMSStr;//08:03:02

        public MacaDateBean() {

        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
            setYearStr("" + year);
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
            if (month < 10) {
                setMonthStr("0" + month);
            } else {
                setMonthStr("" + month);
            }
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
            if (day < 10) {
                setDayStr("0" + day);
            } else {
                setDayStr("" + day);
            }
        }

        public String getYearStr() {
            return yearStr;
        }

        public void setYearStr(String yearStr) {
            this.yearStr = yearStr;
        }

        public String getMonthStr() {
            return monthStr;
        }

        public void setMonthStr(String monthStr) {
            this.monthStr = monthStr;
        }

        public String getDayStr() {
            return dayStr;
        }

        public void setDayStr(String dayStr) {
            this.dayStr = dayStr;
            setYMDStr(yearStr + "-" + monthStr + "-" + dayStr);
        }

        public String getYMDStr() {
            if (YMDStr == null || YMDStr.isEmpty())
                setYMDStr(yearStr + "-" + monthStr + "-" + dayStr);
            return YMDStr;
        }

        public void setYMDStr(String YMDStr) {
            this.YMDStr = YMDStr;
        }

        public int getHour() {
            return hour;
        }

        public void setHour(int hour) {
            this.hour = hour;
            if (hour < 10) {
                setHourStr("0" + hour);
            } else {
                setHourStr("" + hour);
            }
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
            if (min < 10) {
                setMinStr("0" + min);
            } else {
                setMinStr("" + min);
            }
        }

        public int getSs() {
            return ss;
        }

        public void setSs(int ss) {
            this.ss = ss;
            if (ss < 10) {
                setSsStr("0" + ss);
            } else {
                setSsStr("" + ss);
            }
        }

        public String getHourStr() {
            return hourStr;
        }

        public void setHourStr(String hourStr) {
            this.hourStr = hourStr;
        }

        public String getMinStr() {
            return minStr;
        }

        public void setMinStr(String minStr) {
            this.minStr = minStr;
            setHMStr(hourStr + ":" + minStr);
        }

        public String getSsStr() {
            return ssStr;
        }

        public void setSsStr(String ssStr) {
            this.ssStr = ssStr;
            setHMSStr(hourStr + ":" + minStr + ":" + ssStr);
        }

        public String getHMStr() {
            return HMStr;
        }

        public void setHMStr(String HMStr) {
            this.HMStr = HMStr;
        }

        public String getHMSStr() {
            return HMSStr;
        }

        public void setHMSStr(String HMSStr) {
            this.HMSStr = HMSStr;
        }
    }

}

//                       .::::.
//                     .::::::::.
//                    :::::::::::
//                 ..:::::::::::'
//              '::::::::::::'
//                .::::::::::
//           '::::::::::::::..
//                ..::::::::::::.
//              ``::::::::::::::::
//               ::::``:::::::::'        .:::.
//              ::::'   ':::::'       .::::::::.
//            .::::'      ::::     .:::::::'::::.
//           .:::'       :::::  .:::::::::' ':::::.
//          .::'        :::::.:::::::::'      ':::::.
//         .::'         ::::::::::::::'         ``::::.
//     ...:::           ::::::::::::'              ``::.
//    ```` ':.          ':::::::::'                  ::::..
//                       '.:::::'                    ':'````..
//
//
//      ┏┛ ┻━━━━┛ ┻┓
//      ┃　　　　　　      ┃
//      ┃　　　━　　   　　┃
//      ┃　┳┛　  ┗┳　　 ┃
//      ┃　　　　　　 　　  ┃
//      ┃　　　┻　　　　　 ┃
//      ┃　　　　　　　　　 ┃
//      ┗━┓　　　┏━━━┛
//       　 ┃　　　┃   神兽保佑
//      　  ┃　　　┃   代码无BUG！
//      　  ┃　　　┗━━━━━━━━━┓
//      　  ┃　　　　　　　   　　　　 ┣┓
//      　  ┃　　　　        　　　　 ┏┛
//      　  ┗━┓ ┓ ┏━━━┳ ┓ ┏━┛
//      　　    ┃ ┫ ┫   　┃ ┫ ┫
//       　　   ┗━┻━┛   ┗━┻━┛
