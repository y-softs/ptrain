package com.nomen.ntrain.ptrain.util;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
/**
 * 	理论学习_每日三问
 * 	日历构造，日期查询
 * @author 邱鑫
 * @date:2013-12-4
 */
public class DateTimeUtil {
	private DateTimeUtil() {

    }

    /**
     * 以格式format返回表示日期时间的字符串
     * 
     * @param format
     * @return
     */
    public static String getDateTimeStr(String format) {
        Date date = new Date();
        Format formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    /**
     * 取得当前是几号
     * 
     * @return
     */
    public static String getDateTimeNum() {
        return getDateTimeStr("dd");
    }
    /**
     * 取得当前日期时间
     * 
     * @return
     */
    public static String getCurrDateTime() {
        return getDateTimeStr("yyyy.MM.dd HH:mm:ss");
    }

    /**
     * 取得当前日期,不足两位前补零
     * 
     * @return
     */
    public static String getCurrDate() {
        return getDateTimeStr("yyyy-MM-dd");
    }

    /**
     * 取得当前日期
     * 
     * @return
     */
    public static String getSimpleCurrDate() {
        return getDateTimeStr("yyyy.M.d");
    }

    /**
     * 取得当前时间
     * 
     * @return
     */
    public static String getCurrTime() {
        return getDateTimeStr("HH:mm:ss");
    }

    /**
     * 取得当前年月 yyyy.MM
     * 
     * @return
     */
    public static String getCurrYearMonth() {
        return getDateTimeStr("yyyy.MM");
    }
    /**
     * 取得当前年月  yyyy-MM
     * 
     * @return
     */
    public static String getCurrYearOrMonth() {
        return getDateTimeStr("yyyy-MM");
    }

    /**
     * 从文本形式日期取得Date日期时间
     * 
     * @param strMonth
     * @return
     */
    private static Date getDate(String strMonth) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy.MM.dd");

        try {
            java.util.Date date = myFormatter.parse(strMonth);
            return date;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 得到两个文本日期之间的天数
     * 
     * @param startDate
     * @param endDate
     * @return
     */
    public static long getDaysBetween(String startDate, String endDate) {
        Date dStart = getDate(startDate);
        Date dEnd = getDate(endDate);

        return (dEnd.getTime() - dStart.getTime()) / (24 * 60 * 60 * 1000);
    }

    /**
     * 取某月的天数,strMonth的格式是"yyyy.MM"
     * @param strMonth
     * @return
     */
    public static int getDaysInAMonth(String strMonth) {
        String[] arr = strMonth.split("[.]");

        // Create a calendar object of the desired month
        Calendar cal = new GregorianCalendar(Integer.parseInt(arr[0]), Integer
                .parseInt(arr[1]) - 1, 1);

        // Get the number of days in that month
        int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        return days;
    }

    /**
     * 取某月第一天是周几,strMonth的格式是"yyyy.MM"
     * @param strMonth
     * @return
     */
    public static int getWeekOfFirstDay(String strMonth) {
        String[] arr = strMonth.split("[.]");

        Calendar xmas = new GregorianCalendar(Integer.parseInt(arr[0]), Integer
                .parseInt(arr[1]) - 1, 1);
        int dayOfWeek = xmas.get(Calendar.DAY_OF_WEEK) - 1;
        return dayOfWeek;
    }

	/**
	 * 获取月份最后一天
	 * @param sDate 格式：9999-01或9999-1
	 */
	public static int getLastDay(String sDate) throws ParseException{
	 	Date date = new java.text.SimpleDateFormat("yyyy-MM").parse(sDate);
        Calendar cDay = Calendar.getInstance();   
        cDay.setTime(date);   
        final int lastDayTemp = cDay.getActualMaximum(Calendar.DAY_OF_MONTH);   
        Date lastDate = cDay.getTime();
        lastDate.setDate(lastDayTemp);
		SimpleDateFormat format=new SimpleDateFormat("dd");
		return Integer.parseInt(format.format(lastDate));
	}

    public static void main(String[] args) throws ParseException {
        System.out.println("当前日期时间为:" + getCurrDateTime());
        System.out.println("当前日期为:" + getCurrDate());
        System.out.println("当前日期为:" + getSimpleCurrDate());
        System.out.println("当前时间为:" + getCurrTime());
        System.out.println("2008.07.05与2008.07.18之间相隔:"
                + getDaysBetween("2008.07.05", "2008.07.18") + "天");
        System.out.println("当前年月为:" + getCurrYearMonth());
        System.out.println("本月第一天为周" + getWeekOfFirstDay(getCurrYearMonth()));
        System.out.println("本月有" + getDaysInAMonth(getCurrYearMonth()) + "天");
        System.out.println("月份最后一天" + getLastDay("2012-2"));
    }
}
