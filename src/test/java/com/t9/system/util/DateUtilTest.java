package com.t9.system.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.t9.system.web.T9RuningTimeException;

public class DateUtilTest {

	@Test
	public void testGetTimeInMillis() {
		Calendar calendar = Calendar.getInstance(); 
		Date date=calendar.getTime();
		calendar.add(Calendar.HOUR, 1);
		Date date2=calendar.getTime();
		String str=DateUtil.getTimeInMillis(date, date2);
		assertEquals(str, "3600 second!");
		calendar.add(Calendar.MILLISECOND, 100);
		Date date3=calendar.getTime();
		String str2=DateUtil.getTimeInMillis(date2, date3);
		assertEquals(str2, "100 min second!");
	}

	@Test
	public void testDateToString() {
		String str1=DateUtil.dateToString(null);
		assertEquals(str1, "");
	
		Date aa=new Date(1416815316606l);
		assertEquals(DateUtil.dateToString(aa), "2014-11-24 15:48:36");
		Calendar calendar = Calendar.getInstance(); 
		calendar.set(2013, 1, 12, 11, 13, 24);
		Date date=calendar.getTime();
		String str=DateUtil.dateToString(date);
		assertEquals(str, "2013-02-12 11:13:24");
	}
	
	@Test
	public void testDateToShortString() {
		Calendar calendar = Calendar.getInstance(); 
		calendar.set(2013, 1, 12, 11, 13, 24);
		Date date=calendar.getTime();
		String str=DateUtil.dateToShortString(date);
		assertEquals(str, "2013-02-12");
	}

	@Test(expected=T9RuningTimeException.class)	
	public void testParasDate() {
		Date date=DateUtil.parasDate(null);
		assertNull(date);
		date=DateUtil.parasDate("2013-02-12 11:13:24");
		assertEquals(DateUtil.dateToString(date), "2013-02-12 11:13:24");
		date=DateUtil.parasDate("2013-02-12 11:13");
		
		
	}
	
}
