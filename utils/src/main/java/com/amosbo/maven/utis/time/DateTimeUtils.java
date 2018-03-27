package com.amosbo.maven.utis.time;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author Amos_Bo
 * @version 1.0
 * @作者 E-mail:284285624@qq.com
 * @date 创建时间：2015年8月19日 下午3:24:12
 * @描述：时间处理类
 */
public class DateTimeUtils {

    /**
     * 获取指定开始结束时间的字符串
     *
     * @param startTime
     * @param endTime
     * @return
     * @description
     * @date 2015年7月27日
     */
    public static String getDate(long startTime, long endTime) {
        StringBuilder builder = new StringBuilder();

        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日", Locale
                .getDefault());
        builder.append(sdf.format(startTime));

        sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        builder.append(" ");
        builder.append(sdf.format(startTime));
        builder.append("-");
        builder.append(sdf.format(endTime));
        return builder.toString().trim();
    }

    /**
     * 获取时间跨度
     *
     * @param stime
     * @param etime
     * @return
     * @description
     * @date 2015年7月28日
     */
    public static String getDate(String stime, String etime) {
        return getDate(stime, etime, "yyyy-MM-dd HH:mm");
    }

    /**
     * 获取日期跨度
     *
     * @param stime
     * @param etime
     * @param format
     * @return
     * @description
     * @date 2015年7月28日
     */
    public static String getDate(String stime, String etime, String format) {
        StringBuilder sBuilder = new StringBuilder();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format,
                Locale.getDefault());
        try {
            Date startTime = simpleDateFormat.parse(stime);
            Date endTime = simpleDateFormat.parse(etime);
            simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm",
                    Locale.getDefault());
            sBuilder.append(simpleDateFormat.format(startTime));
            sBuilder.append(" - ");
            simpleDateFormat = new SimpleDateFormat("HH:mm", Locale
                    .getDefault());
            sBuilder.append(simpleDateFormat.format(endTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sBuilder.toString();
    }

    /**
     * 按照指定格式返回当前时间字符串
     *
     * @param format
     * @return
     * @description
     * @date 2015年7月27日
     */
    public static String getCurrentTime(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault
                ());
        Date date = new Date(System.currentTimeMillis());
        return sdf.format(date);
    }

    /**
     * 返回默认格式的当前时间【2012-12-23 07:45】
     *
     * @return
     * @description
     * @date 2015年7月27日
     */
    public static String getCurrentTime() {
        return getCurrentTime("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * @param smdate
     * @param edate
     * @return 获取两个时间差天数
     * @throws ParseException
     */
    public static int getDaysBetween(String smdate, String edate) throws
            ParseException {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long day = 0;
        Date date = myFormatter.parse(smdate);
        Date mydate = myFormatter.parse(edate);
        day = (mydate.getTime() - date.getTime()) / (24 * 60 * 60 * 1000);
        return (int) day;
    }

    public static String getYearMounthDayChar(String timeStr, String ymd) {
        if (!TextUtils.isEmpty(timeStr)) {
            timeStr = timeStr.replace("/", "-");

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date d2 = null;
            try {
                d2 = formatter.parse(timeStr);
            } catch (Exception e) {
                e.printStackTrace();
            }

            SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy");
            SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
            SimpleDateFormat sdf2 = new SimpleDateFormat("dd");
            SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy年MM月dd日");
            String year = sdf0.format(d2);
            String mounth = sdf1.format(d2);
            String day = sdf2.format(d2);
            String mYearMunthDay = sdf3.format(d2);
            //返回年份
            if (ymd.equals("YEAR")) {
                return year;
            } else if (ymd.equals("MOUNTH")) {
                //返回月份
                return mounth;
            } else if (ymd.equals("DAY")) {
                //返回天
                return day;
            } else {
                //返回年月日格式：2016年7月6日
                return mYearMunthDay;
            }
        } else {
            return " 年 月 日";
        }
    }

    /**
     * 将日期变成常见中文格式
     * 获取距目前时间间隔
     * @param date 日期
     * @return 日期字符串
     */
    public String getRencentTime(long date) {
        try {
            Date time = new Date(date);
            String ftime = "";
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat date_Formater_2 = new SimpleDateFormat("yyyy.MM.dd");
            if (cal.getTimeInMillis() - date < 1000) {
                return "刚刚";
            }
            String curDate = date_Formater_2.format(cal.getTime());
            String paramDate = date_Formater_2.format(time);
            if (curDate.equals(paramDate)) {
                int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
                if (hour == 0) {
                    ftime = Math.max(
                            (cal.getTimeInMillis() - time.getTime()) / 60000, 1)
                            + "分钟前";
                } else {
                    ftime = hour + "小时前";
                }
                return ftime;
            }

            long lt = time.getTime() / 86400000;
            long ct = cal.getTimeInMillis() / 86400000;
            int days = (int) (ct - lt);
            if (days == 0) {
                int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
                if (hour == 0) {
                    ftime = Math.max(
                            (cal.getTimeInMillis() - time.getTime()) / 60000, 1)
                            + "分钟前";
                } else {
                    ftime = hour + "小时前";
                }

            } else if (days == 1) {
                ftime = "昨天";
            } else if (days == 2) {
                ftime = "前天";
            } else if (days > 2 && days < 10) {
                ftime = days + "天前";
            } else {
                ftime = date_Formater_2.format(time);
            }
            return ftime;

        } catch (Throwable e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 日期时间格式转换
     * @param typeFrom 原格式
     * @param typeTo   转为格式
     * @param value    传入的要转换的参数
     * @return String
     */
    public String stringDateToStringData(String typeFrom, String typeTo,
                                         String value) {
        String re = value;
        SimpleDateFormat sdfFrom = new SimpleDateFormat(typeFrom);
        SimpleDateFormat sdfTo = new SimpleDateFormat(typeTo);

        try {
            re = sdfTo.format(sdfFrom.parse(re));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return re;
    }

    /**
     * 毫秒转化时分秒毫秒
     * @param ms
     * @return
     */
    public String formatTime(Long ms) {
        Integer ss = 1;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        StringBuffer sb = new StringBuffer();

        if (day < 0) {
            day = 0L;
        }
        hour = day * 24 + hour;

        String hourStr = hour + ":";
        if (hour <= 0) {
            hourStr = "";
        }

        if (hourStr.length() == 2) {
            hourStr = "0" + hourStr;
        }
        sb.append(hourStr);
        String minuteStr = minute + ":";
        if (minute <= 0) {
            minuteStr = "00:";
        }
        if (minuteStr.length() == 2) {
            minuteStr = "0" + minuteStr;
        }
        sb.append(minuteStr);
        String secondStr = second + "";
        if (second <= 0) {
            secondStr = "00";
        }

        if (secondStr.length() == 1) {
            secondStr = "0" + secondStr;
        }
        sb.append(secondStr);
        return sb.toString();
    }
}
