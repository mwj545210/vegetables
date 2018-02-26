package com.mwj.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarUtils {

    public static final long MILLISECS_PER_SECOND = 1000;
    public static final long MILLISECS_PER_MINUTE = 60 * MILLISECS_PER_SECOND;
    public static final long MILLISECS_PER_HOUR = 60 * MILLISECS_PER_MINUTE;
    public static final long MILLISECS_PER_DAY = 24 * MILLISECS_PER_HOUR;

    /**
     * 日期格式化
     */
    public static final String FORMAT_DATE = "yyyy-MM-dd";

    /**
     * 年月格式化
     */
    public static final String FORMAT_YEAR_MONTH = "yyyy-MM";

    /**
     * 月日格式化
     */
    public static final String FORMAT_MONTH_DAY = "MM-dd";

    public static final String FORMAT_AAA_TIME_NOT_SECOND = "aaa HH:mm";

    /**
     * 时间格式化(不含秒)
     */
    public static final String FORMAT_TIME_NOT_SECOND = "HH:mm";

    /**
     * 时间格式化
     */
    public static final String FORMAT_TIME = "HH:mm:ss";

    /**
     * 日期时间格式化(不含秒)
     */
    public static final String FORMAT_DATE_TIME_NOT_SECOND = "yyyy-MM-dd HH:mm";

    /**
     * 日期格式化字符串
     */
    public static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    public static SimpleDateFormat getDateFormat(String format) {
        return new SimpleDateFormat(format);
    }

    /**
     * 将时间格式化日期格式(yyyy-MM-dd)
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        return formatDateByFormat(date, FORMAT_DATE);
    }

    /**
     * 将时间格式化日期格式(yyyy-MM)
     *
     * @param date
     * @return
     */
    public static String formatYearMonth(Date date) {
        return formatDateByFormat(date, FORMAT_YEAR_MONTH);
    }

    public static String formatMonthDay(Date date) {
        return formatDateByFormat(date, FORMAT_MONTH_DAY);
    }

    /**
     * 将时间格式为时间形式(HH:mm:ss)
     *
     * @param date
     * @return
     */
    public static String formatTime(Date date) {
        return formatDateByFormat(date, FORMAT_TIME);
    }

    /**
     * 将时间格式化为不带秒的时间形式(HH:mm)
     *
     * @param date
     * @return
     */
    public static String formatTimeNoSecond(Date date) {
        return formatDateByFormat(date, FORMAT_TIME_NOT_SECOND);
    }

    public static String formatAaaTimeNoSecond(Date date) {
        return formatDateByFormat(date, FORMAT_AAA_TIME_NOT_SECOND);
    }

    /**
     * 将时间格式化为日期时间格式(yyyy-MM-dd HH:mm:ss)
     *
     * @param date
     * @return
     */
    public static String formatDateTime(Date date) {
        return formatDateByFormat(date, FORMAT_DATE_TIME);
    }

    /**
     * 将时间格式化为日期时间格式(MM-dd)
     *
     * @param date
     * @return
     */
    public static String formatMonthAndDay(Date date) {
        return formatDateByFormat(date, FORMAT_MONTH_DAY);
    }

    /**
     * 将时间格式化为日期时间格式不带秒(yyyy-MM-dd HH:mm)
     *
     * @param date
     * @return
     */
    public static String formatDateTimeNotSecond(Date date) {
        return formatDateByFormat(date, FORMAT_DATE_TIME_NOT_SECOND);
    }

    /**
     * 将时间格式化为指定格式
     *
     * @param date
     * @param format
     * @return
     */
    public static String formatDateByFormat(Date date, String format) {
        String result = "";
        if (date != null) {
            result = getDateFormat(format).format(date);
        }
        return result;
    }

    /**
     * 将java.util.Date转化为java.sql.Date
     *
     * @param date
     * @return
     */
    public static java.sql.Date parseSqlDate(Date date) {
        if (date == null) {
            throw new NullPointerException();
        }
        return new java.sql.Date(date.getTime());
    }

    /**
     * 获取年份
     *
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    /**
     * 获取月份
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取日期(一个月中的哪一天)
     *
     * @param date
     * @return
     */
    public static int getDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取小时(24小时制)
     *
     * @param date
     * @return
     */
    public static int getHour(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取分钟
     *
     * @param date
     * @return
     */
    public static int getMinute(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MINUTE);
    }

    /**
     * 获取秒
     *
     * @param date
     * @return
     */
    public static int getSecond(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.SECOND);
    }

    /**
     * 获取毫秒
     *
     * @param date
     * @return
     */
    public static long getMillis(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getTimeInMillis();
    }

    /**
     * 获取指定时间是星期几
     *
     * @param date
     * @return
     */
    public static int getWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        dayOfWeek = dayOfWeek - 1;
        if (dayOfWeek == 0) {
            dayOfWeek = 7;
        }
        return dayOfWeek;
    }

    /**
     * 日期相加
     *
     * @param date Date
     * @param day  int
     * @return Date
     */
    public static Date addDate(Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);
        return c.getTime();
    }

    /**
     * 日期相减
     *
     * @param date  Date
     * @param date1 Date
     * @return int
     */
    public static int diffDate(Date date, Date date1) {
        return (int) ((getMillis(date) - getMillis(date1)) / (24 * 3600 * 1000));
    }

    /**
     * 将字符串转换为日期格式的Date类型(yyyy-MM-dd)
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String date) throws ParseException {
        return getDateFormat(FORMAT_DATE).parse(date);
    }

    /**
     * 将字符串转换为日期格式的Date类型(yyyy-MM-dd)
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date parseYearMonth(String date) throws ParseException {
        return getDateFormat(FORMAT_YEAR_MONTH).parse(date);
    }

    /**
     * 将字符串转换为日期时间格式的Date类型(yyyy-MM-dd HH:mm:ss)
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date parseDateTime(String date) throws ParseException {
        return getDateFormat(FORMAT_DATE_TIME).parse(date);
    }

    /**
     * 将字符串转换为日期时间格式的Date类型(yyyy-MM-dd HH:mm)
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date parseDateTimeNoSecond(String date) throws ParseException {
        return getDateFormat(FORMAT_DATE_TIME_NOT_SECOND).parse(date);
    }

    public static boolean compareDateAndWeekDay(Date date, int weekDay) {
        return weekDay == getWeek(date);
    }

    /**
     * 将字符串转换为日期时间格式的Date类型( MM-dd )
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date parseMonthDay(String date) throws ParseException {
        return getDateFormat(FORMAT_MONTH_DAY).parse(date);
    }

    /**
     * 将字符串转为时间格式的Date类型(HH:mm)
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date parseTimeNoSecond(String date) throws ParseException {
        return getDateFormat(FORMAT_TIME_NOT_SECOND).parse(date);
    }

    /**
     * 将字符串转为时间格式的Date类型(HH:mm:ss)
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date parseTimeNoDate(String date) throws ParseException {
        return getDateFormat(FORMAT_TIME).parse(date);
    }

    /**
     * 获得当前时间
     *
     * @return
     */
    public static String getCurrentDateTime() {
        Date date = new Date();
        String result = formatDateTime(date);
        return result;
    }

    /**
     * 时间比较yyyy-MM-dd
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean compareDate(Date date1, Date date2) {
        String str1 = formatDate(date1);
        String str2 = formatDate(date2);
        if (str1.equals(str2)) {
            return true;
        }
        return false;
    }

    /**
     * 时间比较HH:mm
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean compareDateTimeHHmm(Date date1, Date date2) {
        String str1 = formatTimeNoSecond(date1);
        String str2 = formatTimeNoSecond(date2);
        return str1.equals(str2);
    }

    public static SimpleDateFormat getSimpleDateFormat(String pattern) {
        return new SimpleDateFormat(pattern);
    }

    public static Date getFormatDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    public static boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        int year1 = cal1.get(Calendar.YEAR);
        int month1 = cal1.get(Calendar.MONTH);
        int day1 = cal1.get(Calendar.DAY_OF_MONTH);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int year2 = cal2.get(Calendar.YEAR);
        int month2 = cal2.get(Calendar.MONTH);
        int day2 = cal2.get(Calendar.DAY_OF_MONTH);
        return year1 == year2 && month1 == month2 && day1 == day2;
    }

    public static int daysBetween(Date date1, Date date2) {
        long qua = date1.getTime() - date2.getTime();
        return (int) (qua / (60 * 60 * 1000 * 24));
    }

    public static Date attemptParseDate(String dateStr) throws ParseException {
        Date date;
        try {
            date = getDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(dateStr);
        } catch (ParseException e) {
            try {
                date = parseDateTime(dateStr);
            } catch (ParseException e1) {
                try {
                    date = parseTimeNoDate(dateStr);
                } catch (ParseException e2) {
                    try {
                        date = parseDate(dateStr);
                    } catch (ParseException e3) {
                        date = getDateFormat("HH:mm").parse(dateStr);
                    }
                }
            }
        }
        return date;
    }


    /**
     * 清除日期对象中的时、分、秒字段
     *
     * @param date
     * @return
     */
    public static Date clearTime(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        clearTime(calendar);
        return calendar.getTime();
    }

    /**
     * 消除日历中的时、分、秒字段
     *
     * @param calendar
     */
    public static void clearTime(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    /**
     * 返回指定日期所天的结束时间，即23点59分59秒，主要用于一些日期范围的判断
     *
     * @param date
     * @return
     */
    public static Date lastOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * 获取指定日期所在季度的第1天
     *
     * @param date
     * @return
     */
    public static Date firstDateOfQuarter(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int m = (calendar.get(Calendar.MONTH) / 3) * 3;
        calendar.set(Calendar.MONTH, m);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        clearTime(calendar);
        return calendar.getTime();
    }

    /**
     * 获取指定年度、季度的第一天
     *
     * @param year
     * @param quarter 季度1-4
     * @return
     */
    public static Date firstDateOfQuarter(int year, int quarter) {
        int m = (quarter - 1) * 3;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, m);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        clearTime(calendar);
        return calendar.getTime();
    }

    /**
     * 获取指定日期所在季度的最后一天
     *
     * @param date
     * @return
     */
    public static Date lastDateOfQuarter(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int m = (calendar.get(Calendar.MONTH) / 3) * 3 + 2;
        calendar.set(Calendar.MONTH, m);
        calendar.set(Calendar.DAY_OF_MONTH, lastDayOfMonth(calendar.get(Calendar.YEAR), m + 1));
        lastTime(calendar);
        return calendar.getTime();
    }

    /**
     * 获取指定年度、季度的最后一天
     *
     * @param year
     * @return
     */
    public static Date lastDateOfQuarter(int year, int quarter) {
        int m = quarter * 3;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, m - 1);
        calendar.set(Calendar.DAY_OF_MONTH, lastDayOfMonth(year, m));
        lastTime(calendar);
        return calendar.getTime();
    }

    /**
     * 获取指定日期是所在季度的第几天
     *
     * @param date
     * @return
     */
    public static int dayOfQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        clearTime(calendar);
        Date cur = calendar.getTime();

        int q = calendar.get(Calendar.MONTH) / 3 + 1;
        calendar.set(Calendar.MONTH, (q - 1) * 3);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return (int) diffDays(calendar.getTime(), cur);
    }

    /**
     * 获取指定半年的第一天
     *
     * @param year
     * @param semi 半年1,2
     * @return
     */
    public static Date firstDateOfSemi(int year, int semi) {
        int m = semi == 1 ? 0 : 6;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, m);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        clearTime(calendar);
        return calendar.getTime();
    }

    /**
     * 获取指定半年的最后一天
     *
     * @param year
     * @param semi 半年1,2
     * @return
     */
    public static Date lastDateOfSemi(int year, int semi) {
        int m = semi == 1 ? 5 : 11;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, m);
        calendar.set(Calendar.DAY_OF_MONTH, lastDayOfMonth(year, m + 1));
        lastTime(calendar);
        return calendar.getTime();
    }

    /**
     * 获取日期对应的半年（1表示上半年，2表示下半年)
     *
     * @param date
     * @return
     */
    public static int semi(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) <= 5 ? 1 : 2;
    }

    /**
     * 获取指定日期所在半年的第1天
     *
     * @param date
     * @return
     */
    public static Date firstDateOfSemi(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int m = calendar.get(Calendar.MONTH) <= 5 ? 0 : 6;
        calendar.set(Calendar.MONTH, m);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        clearTime(calendar);
        return calendar.getTime();
    }

    /**
     * 获取指定日期所在半年的最后一天
     *
     * @param date
     * @return
     */
    public static Date lastDateOfSemi(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int m = calendar.get(Calendar.MONTH) <= 5 ? 5 : 11;
        calendar.set(Calendar.MONTH, m);
        calendar.set(Calendar.DAY_OF_MONTH, lastDayOfMonth(calendar.get(Calendar.YEAR), m + 1));
        lastTime(calendar);
        return calendar.getTime();
    }

    /**
     * 获取指定日期是所在半年的第几天
     *
     * @param date
     * @return
     */
    public static int dayOfSemi(Date date) {
        Date first = firstDateOfSemi(date);
        return (int) diffDays(first, date);
    }

    /**
     * 获取某个月份的最后一天
     *
     * @param year
     * @param month 1-12
     * @return
     */
    public static int lastDayOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 获取某个日期所在月份的最后一天对应的日
     *
     * @param date
     * @return
     */
    public static int lastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.DATE, 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 获取某个日期所在月份的最后一天对应的日期
     *
     * @param date
     * @return
     */
    public static Date lastDateOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);
        calendar.roll(Calendar.DATE, -1);
        lastTime(calendar);
        return calendar.getTime();
    }

    /**
     * 获取某个月最后一天的日期
     *
     * @param year
     * @param month
     * @return
     */
    public static Date lastDateOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        calendar.roll(Calendar.DATE, -1);
        lastTime(calendar);
        return calendar.getTime();
    }

    /**
     * 获取指定日期所在月份的第1天
     *
     * @param date
     * @return
     */
    public static Date firstDateOfMonth(Date date) {
        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        clearTime(calendar);
        return calendar.getTime();
    }

    /**
     * 获取指定月份的第一天
     *
     * @param year
     * @param month 月份1-12
     * @return
     */
    public static Date firstDateOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        clearTime(calendar);
        return calendar.getTime();
    }

    /**
     * 返回指定日期所天的结束时间，即23点59分59秒，主要用于一些日期范围的判断
     *
     * @param calendar
     * @return
     */
    public static void lastTime(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
    }

    /**
     * 计算两个日期之间相差的天数（按天计算，如果不是同天天时不满一天也当作一天）<br>
     * 如2009-1-1 23:00:00 与 2009-1-2 01:00:00，返回的结果是1天
     *
     * @param baseDate
     * @param checkDate
     * @return
     */
    public static long diffDays(Date baseDate, Date checkDate) {
        Calendar base = Calendar.getInstance();
        base.setTime(baseDate);
        Calendar check =  Calendar.getInstance();
        check.setTime(checkDate);
        clearTime(base);
        clearTime(check);
        return diffMilliseconds(base, check) / MILLISECS_PER_DAY;
    }

    /**
     * 获取两个时间之间相差的毫秒数
     *
     * @param base
     * @param check
     * @return
     */
    public static long diffMilliseconds(Calendar base, Calendar check) {
        long endL = check.getTimeInMillis() + check.getTimeZone().getOffset(check.getTimeInMillis());
        long startL = base.getTimeInMillis() + base.getTimeZone().getOffset(base.getTimeInMillis());
        return endL - startL;
    }

    /**
     * 获取两个时间之间相差的毫秒数
     *
     * @param baseDate
     * @param checkDate
     * @return
     */
    public static long diffMilliseconds(Date baseDate, Date checkDate) {
        Calendar start = Calendar.getInstance();
        start.setTime(baseDate);
        Calendar end =  Calendar.getInstance();
        end.setTime(checkDate);
        return diffMilliseconds(start, end);
    }

    /**
     * 根据年，月，日创建一个Date对象,月份为1-12
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static Date newDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(year, month - 1, day, 0, 0, 0);
        return calendar.getTime();
    }
}