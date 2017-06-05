package com.wbsf.core.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.junit.Test;

/**
 * 测方法并非为实际意义的测试，主要是为了体哦概念股JotaTime Api的操作范例
 * @author hubery
 *
 */
public class JodaTimeTest {
	
	@Test
	public void testJodaTime(){
		
		//方法一：取系统点间  
		DateTime dt1 = new DateTime();  
		  
		//方法二：通过java.util.Date对象生成  
		DateTime dt2 = new DateTime(new Date());  
		  
		//方法三：指定年月日点分秒生成(参数依次是：年,月,日,时,分,秒,毫秒)  
		DateTime dt3 = new DateTime(2012, 5, 20, 13, 14, 0, 0);  
		  
		//方法四：ISO8601形式生成  
		DateTime dt4 = new DateTime("2012-05-20");  
		DateTime dt5 = new DateTime("2012-05-20T13:14:00");  
		  
		//只需要年月日的时候  
		LocalDate localDate = new LocalDate(2009, 9, 6);// September 6, 2009  
		  
		//只需要时分秒毫秒的时候  
		LocalTime localTime = new LocalTime(13, 30, 26, 0);// 1:30:26PM  
		DateTime dt = new DateTime();
		//年
		int year = dt.getYear();
		//月
		int month = dt.getMonthOfYear();
		//日
		int day = dt.getDayOfMonth();
		//星期
		int week = dt.getDayOfWeek();
		//点
		int hour = dt.getHourOfDay();
		//分
		int min = dt.getMinuteOfHour();
		//秒
		int sec = dt.getSecondOfMinute();
		//毫秒
		int msec = dt.getMillisOfSecond();
		DateTime datiemTimet01 = new DateTime();

		//星期
		switch(datiemTimet01.getDayOfWeek()) {
		case DateTimeConstants.SUNDAY:
			System.out.println("星期日");
			break;
		case DateTimeConstants.MONDAY:
			System.out.println("星期一");
			break;
		case DateTimeConstants.TUESDAY:
			System.out.println("星期二");
			break;
		case DateTimeConstants.WEDNESDAY:
			System.out.println("星期三");
			break;
		case DateTimeConstants.THURSDAY:
			System.out.println("星期四");
			break;
		case DateTimeConstants.FRIDAY:
			System.out.println("星期五");
			break;
		case DateTimeConstants.SATURDAY:
			System.out.println("星期六");
			break;
		}
		datiemTimet01 = new DateTime();

		//转换成java.util.Date对象
		Date d1 = new Date(datiemTimet01.getMillis());
		Date d2 = datiemTimet01.toDate();

		//转换成java.util.Calendar对象
		Calendar c1 = Calendar.getInstance();
		c1.setTimeInMillis(datiemTimet01.getMillis());
		Calendar c2 = datiemTimet01.toCalendar(Locale.getDefault());
		datiemTimet01 = new DateTime();

		//昨天
		DateTime yesterday = datiemTimet01.minusDays(1);		
		//明天
		DateTime tomorrow = datiemTimet01.plusDays(1);		
		//1个月前
		DateTime before1month = datiemTimet01.minusMonths(1);		
		//3个月后
		DateTime after3month = datiemTimet01.plusMonths(3);		
		//2年前
		DateTime before2year = datiemTimet01.minusYears(2);		
		//5年后
		DateTime after5year = datiemTimet01.plusYears(5);
		datiemTimet01 = new DateTime();	

		//月末日期	
		DateTime lastday = datiemTimet01.dayOfMonth().withMaximumValue();

		//90天后那周的周一
		DateTime firstday = datiemTimet01.plusDays(90).dayOfWeek().withMinimumValue();
		//默认设置为日本时间
		DateTimeZone.setDefault(DateTimeZone.forID("Asia/Tokyo"));
		dt1 = new DateTime();

		//伦敦时间
		dt2 = new DateTime(DateTimeZone.forID("Europe/London"));
		DateTime begin = new DateTime("2012-02-01");
		DateTime end = new DateTime("2012-05-01");

		//计算区间毫秒数
		Duration d = new Duration(begin, end);
		long time = d.getMillis();

		//计算区间天数
		Period p = new Period(begin, end, PeriodType.days());
		int days = p.getDays();

		//计算特定日期是否在该区间内
		Interval i = new Interval(begin, end);
		boolean contained = i.contains(new DateTime("2012-03-01"));
		DateTime dateTime1 = new DateTime("2012-02-01");
		DateTime dateTime2 = new DateTime("2012-05-01");

		//和系统时间比
		boolean b1 = dateTime1.isAfterNow();
		boolean b2 = dateTime1.isBeforeNow();
		boolean b3 = dateTime1.isEqualNow();

		//和其他日期比
		boolean f1 = dateTime1.isAfter(dateTime2);
		boolean f2 = dateTime1.isBefore(dateTime2);
		boolean f3 = dateTime1.isEqual(dateTime2);
		DateTime dateTime = new DateTime();

		String s1 = dateTime.toString("yyyy/MM/dd hh:mm:ss.SSSa");
		String s2 = dateTime.toString("yyyy-MM-dd HH:mm:ss");
		String s3 = dateTime.toString("EEEE dd MMMM, yyyy HH:mm:ssa");
		String s4 = dateTime.toString("yyyy/MM/dd HH:mm ZZZZ");
		String s5 = dateTime.toString("yyyy/MM/dd HH:mm Z");


		
	}
}
