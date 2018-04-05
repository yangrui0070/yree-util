package com.sunlands.clearing.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by IntelliJ IDEA.
 * User: zxf
 * Date: 2017/8/14
 * Time: 下午3:58
 * To change this template use File | Settings | File Templates.
 */
public class DateUtils {

    private final static Logger logger = LoggerFactory.getLogger(DateUtils.class);
    /* 只有日月格式 */
    public static final String FORMAT_YYYY_MM = "yyyy-MM";
    /* 最为完整的日期格式 */
    public static final String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss";


    /**
     * 默认日期格式
     */
    public static final String DEF_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 默认date格式
     */
    public static final String DEF_DATE_PATTERN = "yyyy-MM-dd";

    /**
     * 无横杠时间模式
     */
    public static final String NOBAR_DATE_PATTERN = "yyyyMMd";
    //一天的秒时间
    public static final Integer ONE_DAYS_SECONDS = 24 * 60 * 60;


    /**
     * 格式化日期
     *
     * @param format
     * @param date
     * @return
     */
    public static String dateToString(String format, Date date) {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * Date转换成日期字符串，使用默认格式 "yyyy-MM-dd HH:mm:ss"
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        return dateToString(DEF_TIME_PATTERN, date);
    }

    /**
     * 取得当前时间的日期字符串，使用默认格式 "yyyy-MM-dd HH:mm:ss"
     *
     * @return
     */
    public static String dateToString() {
        return dateToString(new Date());
    }

    /**
     * 取得当前时间的日期字符串，使用指定格式
     *
     * @param format
     * @return
     */
    public static String dateToString(String format) {
        return dateToString(format, new Date());
    }

    /**
     * 格式化日期
     *
     * @param format
     * @param date
     * @return
     */
    public static Date stringToDate(String format, String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(date);
        } catch (Exception e) {
            String msg = String.format("格式化%s失败！", date);
            throw new RuntimeException(msg);
        }
    }

    /**
     * 日期字符串转换成Date，使用默认格式 "yyyy-MM-dd HH:mm:ss"
     *
     * @param dateStr
     * @return
     */
    public static Date stringToDate(String dateStr) {
        return stringToDate(DEF_TIME_PATTERN, dateStr);
    }

    /**
     * 在某一个时间的基础上，增加/减少一定的时间，计算出新时间
     *
     * @param baseDate     基础时间
     * @param timeInSecond 时间，正数代表增加，负数代表减少
     * @return
     */
    public static Date addDate(Date baseDate, int timeInSecond) {
        if (baseDate == null) {
            throw new NullPointerException("baseDate is null");
        }
        long newTime = baseDate.getTime() + timeInSecond * 1000L;
        return new Date(newTime);
    }


    /**
     * 目标日期是否在源日期所在自然周内
     *
     * @param sourceDate
     * @param targetDate
     * @return
     */
    public static boolean inNaturalWeek(Date sourceDate, String targetDate) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEF_DATE_PATTERN); //设置时间格式
        Calendar cal = Calendar.getInstance();
        cal.setTime(sourceDate);

        //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }

        cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天

        int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值

        // 源日期所在自然周星期一的00:00:00
        String mondayStr = sdf.format(cal.getTime()) + " 00:00:00";
        Date monday = stringToDate(mondayStr);

        cal.add(Calendar.DATE, 6);
        // 源日期所在自然周星期日的23:59:59
        String sundayStr = sdf.format(cal.getTime()) + " 23:59:59";
        Date sunday = stringToDate(sundayStr);

        long target = stringToDate(DEF_DATE_PATTERN, targetDate).getTime();

        // 判断目标日期是否在源日期所在的自然周内
        if (target >= monday.getTime() && target <= sunday.getTime()) {
            {
                return true;
            }
        }
        return false;
    }

    public static final Date UNREACHABLE_DATE = localDateTime2Date(LocalDateTime.of
            (3000, 1, 1, 12, 0, 0));


    public static Date fetchStartOfDefineDate(Date date, Integer offset) {

        return localDateTime2Date(date2LocalDate(date).plusDays(offset).atStartOfDay());
    }

    public static Date fetchStartOfCurrentDay(Integer offset) {
        LocalDate localDate = LocalDate.now().plusDays(offset);
        return localDateTime2Date(localDate.atStartOfDay());
    }

    public static LocalDate date2LocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static Date localDate2Date(LocalDate localDate) {
        Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static LocalDateTime date2LocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static List<LocalDate> collectLocalDates(LocalDate start, LocalDate end) {
        // 用起始时间作为流的源头，按照每次加一天的方式创建一个无限流
        return Stream.iterate(start, localDate -> localDate.plusDays(1))
                // 截断无限流，长度为起始时间和结束时间的差+1个
                .limit(ChronoUnit.DAYS.between(start, end) + 1)
                // 由于最后要的是字符串，所以map转换一下
                //.map(LocalDate::toString)
                // 把流收集为List
                .collect(Collectors.toList());
    }

    /**
     * 将字符串转换成指定格式的时间
     *
     * @param format
     * @param data
     * @return
     * @throws ParseException
     */
    public static Date parseStringToDate(String format, String data)
            throws ParseException {
        if (data == null) {
            throw new RuntimeException("转换时间格式时间参数为空");
        }
        if (StringUtils.isEmpty(format)) {
            throw new RuntimeException("转换时间格式格式参数为空");
        }
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.parse(data);
    }

    /**
     * 获取输入时间一个月的时间
     *
     * @param date
     * @return
     */
    public static Date getNextMonth(Date date) {
        if (date == null) {
            throw new RuntimeException("获取输入时间下一个月时间出错");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, +1);
        return calendar.getTime();
    }

    /**
     * 根据输入天数和时间差，获得具体时间
     *
     * @param date
     * @param days
     * @return
     */
    public static Date getNextDate(Date date, Integer days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, + days);
        return calendar.getTime();
    }

    /**
     * 比较2个时间是否月份相等,相等返回0，大于返回1，小于返回-1
     *
     * @param firstDate
     * @param secondDate
     * @param format
     * @return
     * @throws ParseException
     */
    public static int compare(Date firstDate, Date secondDate, String format) {
        if (firstDate == null) {
            throw new RuntimeException("判断时间月份大小过程:传入第一个时间参数为空");
        }
        if (secondDate == null) {
            throw new RuntimeException("判断时间月份大小过程:传入第二个时间参数为空");
        }
        if (StringUtils.isEmpty(format)) {
            throw new RuntimeException("判断时间月份大小过程:传入格式为空");
        }
        SimpleDateFormat df = new SimpleDateFormat(format);
        try {
            Date first = df.parse(df.format(firstDate));
            Date second = df.parse(df.format(secondDate));
            if (first.before(second)) {
                return -1;
            }
            if (second.before(first)) {
                return 1;
            }
        } catch (ParseException e) {
            throw new RuntimeException("日期格式转换异常");
        }
        return 0;
    }

    /**
     * 比较2个年月的先后顺序
     *
     * @param firstDate
     * @param secondDate
     * @param format
     * @return
     * @throws ParseException
     */
    public static int compare(Date firstDate, String secondDate, String format)
            throws ParseException {
        if (StringUtils.isEmpty(secondDate)) {
            throw new RuntimeException("判断时间月份大小过程:传入第二个时间参数为空");
        }
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_YYYY_MM);
        Date sd = df.parse(secondDate);
        return compare(firstDate, sd, FORMAT_YYYY_MM);
    }

    /**
     * 比较2个年月的先后顺序
     *
     * @param firstDate
     * @param secondDate
     * @param format
     * @return
     * @throws ParseException
     */
    public static int compare(String firstDate, String secondDate, String format) {
        if (StringUtils.isEmpty(firstDate)) {
            throw new RuntimeException("判断月份大小过程：传入第一个时间参数为空");
        }
        if (StringUtils.isEmpty(secondDate)) {
            throw new RuntimeException("判断时间月份大小过程:传入第二个时间参数为空");
        }
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_YYYY_MM);
        Date sd = null;
        Date fd = null;
        try {
            sd = df.parse(secondDate);
            fd = df.parse(firstDate);
        } catch (ParseException e) {
            throw new RuntimeException("日期格式转换异常");
        }
        return compare(fd, sd, FORMAT_YYYY_MM);
    }

    /**
     * 根据年月获得天数
     *
     * @param yearAndMonth
     * @return
     */
    public static Integer getDaysOfYearAndMonth(Date yearAndMonth) {
        if (yearAndMonth == null) {
            throw new IllegalArgumentException("yearAndMonth不能为空");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(yearAndMonth);
        return c.getActualMaximum(Calendar.DATE);
    }

    /**
     * 根据年月获得天数
     *
     * @param dateStr
     * @return
     */

    /*
    public static Integer getDaysOfYearAndMonth(String dateStr) {
        if (StringUtils.isBlank(dateStr)) {
            throw new RuntimeException("dateStr不能为空:" + dateStr);
        }
        try {
            Date date = new SimpleDateFormat(FORMAT_YYYY_MM).parse(dateStr);
            return getDaysOfYearAndMonth(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }*/

    /**
     * 将date转换为String类型输出
     *
     * @param date
     * @return
     */
    public static String parseDateToString(Date date, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        String result = df.format(date);
        return result;
    }


    /**
     * 将String类型的日期转换为
     * @param date
     * @param format
     * @return
     */

    /*
    public static String parseStringTOString(String date,String format){
        String result = "";
        try {
            Date d = DateFormatUtil.parseStringToDate(format,date);
            result = DateFormatUtil.parseDateToString(d,format);
        } catch (ParseException e) {
            throw new RuntimeException("格式转换错误");
        }
        return result;
    }*/


    /**
     * 获取两个时间段之间的月份
     *
     * @param minDate
     * @param maxDate
     * @return
     * @throws ParseException
     */
    public static List<String> getMonthBetween(Date minDate, Date maxDate) {


        List<String> result = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYY_MM);//格式化为年月

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.setTime(minDate);
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

        max.setTime(maxDate);
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

        Calendar curr = min;
        while (curr.before(max)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }
        return result;
    }

    /**
     * 返回时间的目前的天
     *
     * @param date
     * @return
     */
    public static int getDayForDate(Date date) {
        if (date == null) {
            throw new RuntimeException("传来的时间为空");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 返回和格式是否匹配
     *
     * @param date
     * @param format
     * @return
     */
    public static boolean isFormat(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            Date d = sdf.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * 获得上一个月
     *
     * @param month
     * @return
     * @throws ParseException
     */
    public static String getPreviousMonth(String month) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYY_MM);
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(month));
        } catch (ParseException e) {
            throw new RuntimeException("退款失败:日期转换失败", e);
        }
        cal.add(Calendar.MONTH, -1);

        return sdf.format(cal.getTime());
    }

    /**
     * 返回两个日期间的差值
     *
     * @param startDate
     * @param endTime
     * @return
     */
    public static Integer getDaysBetweenTwoDay(Date startDate, Date endTime) {

        if (startDate == null || endTime == null) {
            logger.error("时间不能为空");
            throw new RuntimeException("时间不能为空");
        }

        long startDateTime = startDate.getTime();
        long endDateTime = endTime.getTime();
        if (startDateTime > endDateTime) {
            throw new RuntimeException("开始时间比结束时间晚");
        }

        int days = (int) ((endDateTime - startDateTime) / (1000 * 60 * 60 * 24));

        return days;
    }


}
