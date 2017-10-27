package com.cn.zbx.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 时间转换工具类
 * @author zyw
 *
 */
public class DateUtils {

	/**
	 * 将Date类型转换为字符串(年月日)	格式:yyyy-MM-dd
	 * @param date	日期类型
	 * @return 		日期字符串
	 */
	public static String formatDate(Date date) {
		return format(date, "yyyy-MM-dd");
	}
	/**
	 * 将Date类型转换时间戳(long)	格式:1465883268
	 * @param date	日期类型
	 * @return 		时间戳
	 */
	public static Long formatTimestamp(Date date) {
		return date.getTime();
	}
	/**
	 * 将Date类型转换为字符串(年月日时分秒)	格式:yyyy-MM-dd HH:mm:ss
	 * @param date	日期类型
	 * @return 		日期字符串
	 */
	public static String formatDateTime(Date date) {
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 将Date类型转换为字符串(年月日时分秒纳秒)	格式:yyyy-MM-dd HH:mm:ss SSS
	 * @param date	日期类型
	 * @return 		日期字符串
	 */
	public static String formatDateTimeNanosecond(Date date) {
		return format(date, "yyyy-MM-dd HH:mm:ss SSS");
	}
	
	/**
	 * 将Date类型转换为字符串
	 * 
	 * @param date  日期类型
	 * @param pattern  字符串格式
	 * @return  日期字符串
	 */
	public static String format(Date date, String pattern) {
		if (date == null) {
			return null;
		}
		if (pattern == null || pattern.equals("") || pattern.equals("null")) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		return new java.text.SimpleDateFormat(pattern).format(date);
	}

	/**
	 * 根据格式获取当前日期
	 * 
	 * @param formatType
	 * @return
	 */
	public static String getDateByFormat(String formatType) {
		SimpleDateFormat format = new SimpleDateFormat(formatType);
		String time = format.format(new Date());
		return time;
	}

	/**
	 * 获取当前时间戳
	 * 
	 * @return
	 */
	public static long getTimeStamp() {
		return System.currentTimeMillis();
	}

	public static String getTimeStampToDate(long timeStamp, String _format) {
		Date date = new Timestamp(timeStamp);
		SimpleDateFormat format = new SimpleDateFormat(_format);
		String now = format.format(date);
		return now;
	}

	/**
	 * 时间戳转换成日期
	 * 
	 * @param timeStamp
	 * @return
	 */
	public static String getTimeStampToDate(String timeStamp) {
		if (!"".equals(timeStamp) && timeStamp != null
				&& !"0".equals(timeStamp)) {
			String date = new Timestamp(Long.parseLong(timeStamp)).toString();
			date = date.split("\\x2E")[0];
			return date;
		} else {
			return "";
		}
	}

	/**
	 * 时间戳转换成日期
	 * 
	 * @param timeStamp
	 * @return
	 */
	public static String getTimeStampToDate(long timeStamp) {
		String date = new Timestamp(timeStamp).toString();
		date = date.split("\\x2E")[0];
		return date;
	}

	/**
	 * 时间戳转换成日期
	 * 
	 * @param timeStamp
	 * @return
	 */
	public static String getTimeStampToDate(Object ts) {
		if (ts == null){
			return null;
		}
		return getTimeStampToDate(((Timestamp) ts).getTime());
	}

	/**
	 * 生成所有月份（从开始月到结束月）
	 * 
	 * @param startMonth
	 * @param endMonth
	 * @return
	 */
	public static String[] monthToMonth(String startMonth, String endMonth) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
		Date date1 = null; // 开始日期
		Date date2 = null; // 结束日期
		try {
			date1 = df.parse(startMonth);
			date2 = df.parse(endMonth);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		// 定义集合存放月份
		List<String> list = new ArrayList<String>();
		list.add(startMonth);
		c1.setTime(date1);
		c2.setTime(date2);
		while (c1.compareTo(c2) < 0) {
			c1.add(Calendar.MONTH, 1);// 开始日期加一个月直到等于结束日期为止
			Date ss = c1.getTime();
			String str = df.format(ss);
			list.add(str);
		}
		// 存放入数组
		String[] str = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			str[i] = (String) list.get(i);
		}
		System.out.println(Arrays.toString(str));
		return str;

	}

	/**
	 * 将一种格式时间转换成另一种时间格式的字符串
	 * 
	 * @param dateValue
	 * @param beforeFormat
	 * @param afterFormat
	 * @return
	 */
	public static String formatDate(String dateValue, String beforeFormat,
			String afterFormat) {
		Date date = null;
		SimpleDateFormat beforeFormatDate = new SimpleDateFormat(beforeFormat);
		SimpleDateFormat afterFormatDate = new SimpleDateFormat(afterFormat);
		try {
			date = beforeFormatDate.parse(dateValue);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return afterFormatDate.format(date);
	}

	/**
	 * 取得系统时间与参数相差的之前的时间
	 * 
	 * @param date
	 * @param beforeTime
	 * @param i
	 * @return
	 */

	public static String getBeforeDateByYMdHms(Date date, String dateFormat,
			String beforeTime, int i) {
		Calendar cal = null;
		SimpleDateFormat format = null;
		if (dateFormat == null || dateFormat.equals("")) {
			format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		} else {
			format = new SimpleDateFormat(dateFormat);
		}

		if (date != null) {
			format.format(date);
			cal = format.getCalendar();
		} else {
			cal = Calendar.getInstance();
		}

		cal = format.getCalendar();

		if ("YEAR".equals(beforeTime)) {
			cal.add(Calendar.YEAR, i);
		}
		if ("MONTH".equals(beforeTime)) {
			cal.add(Calendar.MONTH, i);
		}
		if ("DATE".equals(beforeTime)) {
			cal.add(Calendar.DATE, i);
		}
		if ("HOUR".equals(beforeTime)) {
			cal.add(Calendar.HOUR, i);
		}
		if ("MINUTE".equals(beforeTime)) {
			cal.add(Calendar.MINUTE, i);
		}

		return format.format(cal.getTime());
	}

	/**
	 * 将字符串转成日期类型
	 * 
	 * @param dateString
	 * @param strFormat
	 * @return
	 */
	public static Date getDateByString(String dateString, String strFormat) {
		Date date = null;
		SimpleDateFormat formatDate = new SimpleDateFormat(strFormat);
		try {
			date = formatDate.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 根据时间格式取得当前时间的字符串
	 * 
	 * @param strFormat
	 * @return
	 */
	public static String getTodayByFormat(Date date, String strFormat) {
		if (strFormat == null || strFormat.equals("")) {
			strFormat = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat format = new SimpleDateFormat(strFormat);
		String strDate = format.format(date);

		return strDate;

	}

	/**
	 * 将日期格式化成date类型
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date stringToDate(String strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * 将日期格式化成datetime类型
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date stringToDateTime(String strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 根据时间戳获取当前格式的日期
	 * 
	 * @return
	 */
	public static Date getDateByTimeStamp() {
		return DateUtils.getDateByString(
				DateUtils.getTimeStampToDate(DateUtils.getTimeStamp()),
				"yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	  * 获取系统前后时间
	  * @param c
	  * @param day
	  * @return
	  */
	    public static Calendar getBeforeOrAferTime(Calendar c, int day) {
	        c.add(Calendar.DATE, day);
	        return c;
	    }
	    
	/**
     * 获取某月的最后一天
     * @Title:getLastDayOfMonth
     * @Description:
     * @param:@param year
     * @param:@param month
     * @param:@return
     * @return:String
     * @throws
     */
    public static String getLastDayOfMonth(int year,int month){
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime());
         
        return lastDayOfMonth;
    }
    
	 
 
	public static final Date parseDate(String strDate, String format) {
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(format);
		try {
			date = df.parse(strDate);
		} catch (ParseException e) {
		}
		return date;
	}

	public static Date formatDate(Date date, String format) {
		SimpleDateFormat inDf = new SimpleDateFormat(format);
		SimpleDateFormat outDf = new SimpleDateFormat(format);
		String reDate = "";
		try {
			reDate = inDf.format(date);
			return outDf.parse(reDate);
		} catch (Exception e) {
		}
		return date;
	}

	/**
	 * 转换时间的显示格式
	 *
	 * @param date
	 * @return
	 */
	public Integer dateFormatDiff(Date date) {
		Calendar calendar = Calendar.getInstance();
		Long nd = (long) (1000 * 24 * 60 * 60);// 一天的毫秒数
		Long diff = calendar.getTime().getTime() - date.getTime();
		Long day = diff / nd;// 计算差多少天

		return day.intValue();
	}
 
	public static void main(String[] args) {
		 String lastDay = getLastDayOfMonth(2014,02);
		 System.out.println("获取当前月的最后一天：" + lastDay);
		 
		 System.out.println(formatDateTime(new Date()));
    }
}
