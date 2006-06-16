/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.helper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Static help methods on dates.
 * 
 * @author David Attias
 */
public abstract class DateHelper
{
	/**
	 * @deprecated never really used.
	 */
	public static final Date INVALID_DATE = new Date(Long.MAX_VALUE);

	/**
	 * @deprecated there is no need to use this method, if you were using it,
	 * call 911.
	 */
	public static void initDateDuJour(Date dateDuJour)
	{
	}

	/**
	 * @deprecated use compareYear
	 */
	public static int compareAnnee(Date date1, Date date2)
	{
		return compareYear(date1, date2);
	}

	/**
	 * Returns a negative, zero or positive integer if date1 is older, equals or
	 * younger than date2. Case of nullity: zero, a negative integer, or a
	 * positive integer if date1 and date2 is null, date1 is null but not date2,
	 * date2 is null but not date1.
	 */
	public static int compareYear(Date date1, Date date2)
	{
		int res = compareNullDate(date1, date2);

		if (res == Integer.MAX_VALUE)
		{
			res = compareNotNullDate(date1, date2, Calendar.YEAR);
		}

		return res;
	}

	/**
	 * @deprecated use compareMonth
	 */
	public static int compareMois(Date date1, Date date2)
	{
		return compareMonth(date1, date2);
	}

	/**
	 * Returns a negative, zero or positive integer if date1 is older equals or
	 * younger than date2. case of nullity: zero, a negative integer, or a
	 * positive integer if date1 and date2 is null, date1 is null but not date2,
	 * date2 is null but not date1.
	 */
	public static int compareMonth(Date date1, Date date2)
	{
		int res = compareNullDate(date1, date2);

		if (res == Integer.MAX_VALUE)
		{
			res = compareNotNullDate(date1, date2, Calendar.YEAR);

			if (res == 0)
			{
				res = compareNotNullDate(date1, date2, Calendar.MONTH);
			}
		}

		return res;
	}

	/**
	 * @deprecated use compareDay
	 */
	public static int compareJour(Date date1, Date date2)
	{
		return compareDay(date1, date2);
	}

	/**
	 * Returns a negative, zero or positive integer if date1 is older equals or
	 * younger than date2. case of nullity: zero, a negative integer, or a
	 * positive integer if date1 and date2 is null, date1 is null but not date2,
	 * date2 is null but not date1.
	 */
	public static int compareDay(Date date1, Date date2)
	{
		int res = compareNullDate(date1, date2);

		if (res == Integer.MAX_VALUE)
		{
			res = compareNotNullDate(date1, date2, Calendar.YEAR);

			if (res == 0)
			{
				res = compareNotNullDate(date1, date2, Calendar.MONTH);

				if (res == 0)
				{
					res = compareNotNullDate(date1, date2, Calendar.DAY_OF_YEAR);
				}
			}
		}

		return res;
	}

	/**
	 * Test all the value not null and return Integer.MAX_VALUE if the test must
	 * be refine.
	 * 
	 * @param date1 a date not nul
	 * @param date2 a date not nul
	 * @param typeComparaison des constantes définies dans Calendar
	 * @return a negative integer, zero, or a positive integer as date1 is less
	 * than, equal to, or greater than date2.
	 */
	private static int compareNotNullDate(Date date1, Date date2,
			int typeComparaison)
	{
		GregorianCalendar cal1 = new GregorianCalendar();
		cal1.setTime(date1);

		int value1 = cal1.get(typeComparaison);

		GregorianCalendar cal2 = new GregorianCalendar();
		cal2.setTime(date2);

		int value2 = cal2.get(typeComparaison);

		if (value1 == value2)
		{
			return 0;
		}
		else if (value1 > value2)
		{
			return 1;
		}
		else
		{
			return -1;
		}
	}

	/**
	 * Test all the value null et return Integer.MAX_VALUE if the test must be
	 * refine.
	 * 
	 * @param date1 Date
	 * @param date2 Date
	 * @return zero, a negative integer, or a positive integer as date1 and
	 * date2 are null, date1 is null but date2 isn't, date2 is null but date1
	 * isn't
	 */
	private static int compareNullDate(Date date1, Date date2)
	{
		if (ValueHelper.isNull(date1))
		{
			if (ValueHelper.isNull(date2))
			{
				return 0;
			}
			else
			{
				return -1;
			}
		}
		else if (ValueHelper.isNull(date2))
		{
			return 1;
		}
		else
		{
			return Integer.MAX_VALUE;
		}
	}

	/**
	 * @deprecated use compareWithCurrentDate
	 */
	public static int compareAvecDateCourante(Date date)
	{
		return compareWithCurrentDate(date);
	}

	/**
	 * Returns a negative, zero or positive integer if the specified date is
	 * older equals or younger than date2. case of nullity: -1 if date is null.
	 */
	public static int compareWithCurrentDate(Date date)
	{
		if (date == null) return -1;

		long base = getTodayDateNoTime().getTime();
		long value = trimTime(date).getTime();

		if (base == value) return 0;

		if (base < value) return 1;
		else return -1;
	}

	/**
	 * Sets the time of the specified calandar object to 00h 00m 00s and returns
	 * it.
	 */
	public static GregorianCalendar trimTime(GregorianCalendar calendar)
	{
		calendar.set(GregorianCalendar.HOUR_OF_DAY, 0);
		calendar.set(GregorianCalendar.MINUTE, 0);
		calendar.set(GregorianCalendar.SECOND, 0);
		calendar.set(GregorianCalendar.MILLISECOND, 0);
		return calendar;
	}

	/**
	 * Sets the time of the specified date object to 00h 00m 00s and returns it.
	 */
	public static Date trimTime(Date date)
	{
		if (date == null) return null;

		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);

		return trimTime(calendar).getTime();
	}

	/**
	 * Returns the date of today with a time set to 00h 00m 00s.
	 */
	public static Date getTodayDateNoTime()
	{
		return trimTime(new GregorianCalendar()).getTime();
	}

	/**
	 * Returns the date of tomorrow with a time set to 00h 00m 00s.
	 */
	public static Date getTomorrowDateNoTime()
	{
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(getTodayDateNoTime());
		calendar.add(GregorianCalendar.DAY_OF_YEAR, 1);
		return trimTime(calendar).getTime();
	}

	/**
	 * Returns the specified date plus one day.
	 */
	public static Date getDatePlusOneDay(Date date)
	{
		return getDatePlusDays(date, 1);
	}

	/**
	 * Returns the specified date plus the specified number of days.
	 */
	public static Date getDatePlusDays(Date date, int days)
	{
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(GregorianCalendar.DAY_OF_YEAR, days);
		return calendar.getTime();
	}

	/**
	 * @deprecated use getDate
	 */
	public static Date getSpecificDate(int year, int month, int day)
	{
		return getDate(year, month, day);
	}

	/**
	 * Returns the specific date with a time set to 00h 00m 00s.
	 */
	public static Date getDate(int year, int month, int day)
	{
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(GregorianCalendar.DAY_OF_MONTH, day);
		calendar.set(GregorianCalendar.MONTH, month);
		calendar.set(GregorianCalendar.YEAR, year);
		return trimTime(calendar).getTime();
	}

	/**
	 * @deprecated use format2LetterDate
	 */
	public static String getFormatedCourrierDate(Date date)
	{
		return format2LetterDate(date);
	}

	/**
	 * Formats the specified date to "d MMMM yyyy" (ie. 27 juin 1973).
	 */
	public static String format2LetterDate(Date date)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("d MMMM yyyy");
		return formatter.format(date);
	}

	/**
	 * @deprecated use format2ShortDate
	 */
	public static String getFormatedDate(Date date)
	{
		return format2ShortDate(date);
	}
	
	/**
	 * Formats the specified date to "dd/MM/yyyy" (ie. 27/06/1973).
	 */
	public static String format2ShortDate(Date date)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.format(date);
	}	
}
