package org.azizkhani.common.utility;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import java.util.logging.Level;



public class DateUtility {
	public static final int PATTERNS1 = 1;  // yyyy/mm/dd
	public static final int PATTERNS2 = 2; // yyyy-mm-dd
	public static final int PATTERNS3 = 3; // yyyy-dd-mm

	private static final String DATE_IDENTIFIER = "#d#";

	// public static int SolarYear = 0, SolarMonth = 0, SolarDay = 0;
	// public static int GregorianYear = 0, GregorianMonth = 0, GregorianDay =
	// 0;
	public static final int dkSolar = 0;
	public static final int dkGregorian = 1;
	public static final int[] LeapMonth = { 12, 2 };
	public static final int[][] DaysOfMonths = {
			{ 31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30, 29 },
			{ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 } };
	// { Far, Ord, Kho, Tir, Mor, Sha, Meh, Aba, Aza, Day, Bah, Esf },
	// { Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec });
	public static final int[][] DaysToMonth = {
			{ 0, 31, 62, 93, 124, 155, 186, 216, 246, 276, 306, 336, 365 },
			{ 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365 } };
	// { Far, Ord, Kho, Tir, Mor, Sha, Meh, Aba, Aza, Day, Bah,^Esf, *** },
	// { Jan,^Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec, *** });

	public static String[] solarMonthName = {
			"\u0641\u0631\u0648\u0631\u062f\u064a\u0646",
			"\u0627\u0631\u062f\u064a\u0628\u0647\u0634\u062a",
			"\u062e\u0631\u062f\u0627\u062f", "\u062a\u064a\u0631",
			"\u0645\u0631\u062f\u0627\u062f",
			"\u0634\u0647\u0631\u064a\u0648\u0631", "\u0645\u0647\u0631",
			"\u0622\u0628\u0627\u0646", "\u0622\u0630\u0631", "\u062f\u064a",
			"\u0628\u0647\u0645\u0646", "\u0627\u0633\u0641\u0646\u062f" };
	public static String[] gregorianMonthName = { "Jan", "Feb", "Mar", "Apr",
			"May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };

	public static boolean IsLeapYear(int DateKind, int Year) {
		if (DateKind == dkSolar)
			return ((((Year + 38) * 31) % 128) <= 30);
		return ((Year % 4) == 0)
				&& (((Year % 100) != 0) || ((Year % 400) == 0));
	}

	public static int DaysOfMonth(int DateKind, int Year, int Month) {
		int Result = 0;
		if ((Year != 0) && ((Month >= 1) && (Month <= 12))) {
			Result = DaysOfMonths[DateKind][Month - 1];
			if ((Month == LeapMonth[DateKind]) && (IsLeapYear(DateKind, Year)))
				Result = Result + 1;
		}
		return Result;
	}

	public static int getGregorianMonthNumber(String monthStr) {
		java.text.DateFormatSymbols dfs = new java.text.DateFormatSymbols();
		String[] months = dfs.getShortMonths();
		for (int i = 0; i <= 11; i++)
			if (monthStr.equalsIgnoreCase(months[i]))
				return ++i;
		return -1;
	}

	public static boolean IsDateValid(int DateKind, int Year, int Month, int Day) {
		return (Year != 0) && (Month >= 1) && (Month <= 12) && (Day >= 1)
				&& (Day <= DaysOfMonth(DateKind, Year, Month));
	}

	public static int DaysToDate(int DateKind, int Year, int Month, int Day) {
		int Result = 0;
		if (IsDateValid(DateKind, Year, Month, Day)) {
			Result = DaysToMonth[DateKind][Month - 1] + Day;
			if ((Month > LeapMonth[DateKind]) && IsLeapYear(DateKind, Year))
				Result = Result + 1;
		}
		return Result;
	}

	public static int[] DateOfDay(int DateKind, int Days, int Year) {
		int[] MonthDay = new int[2];
		int LeapDay = 0;
		int m = 0;
		int Month = 0;
		int Day = 0;
		for (m = 2; m <= 13; m++) {
			if ((m > LeapMonth[DateKind]) && IsLeapYear(DateKind, Year))
				LeapDay = 1;
			if (Days <= (DaysToMonth[DateKind][m - 1] + LeapDay)) {
				Month = m - 1;
				if (Month <= LeapMonth[DateKind])
					LeapDay = 0;
				Day = Days - (DaysToMonth[DateKind][Month - 1] + LeapDay);
				MonthDay[0] = Month;
				MonthDay[1] = Day;
				return MonthDay;
			}
		}
		return null;
	}

	public static int[] GregorianToSolar(int Year, int Month, int Day) {
		int LeapDay = 0;
		int Days = 0;
		boolean PrevGregorianLeap = false;

		if (IsDateValid(dkGregorian, Year, Month, Day)) {
			PrevGregorianLeap = IsLeapYear(dkGregorian, Year - 1);
			Days = DaysToDate(dkGregorian, Year, Month, Day);
			Year = Year - 622;
			if (IsLeapYear(dkSolar, Year))
				LeapDay = 1;
			else
				LeapDay = 0;
			if (PrevGregorianLeap && (LeapDay == 1))
				Days = Days + 287;
			else
				Days = Days + 286;
			if (Days > (365 + LeapDay)) {
				Year = Year + 1;
				Days = Days - (365 + LeapDay);
			}

			int[] MonthDay = DateOfDay(dkSolar, Days, Year);
			int[] YearMonthDay = new int[3];
			YearMonthDay[0] = Year;
			YearMonthDay[1] = MonthDay[0];
			YearMonthDay[2] = MonthDay[1];
			return YearMonthDay;

		}

		return null;

	}

	public static boolean useSolarDate(String language) {
		return "fa".equals(language);
	}

	/**
	 * 
	 * @param date
	 * @param locale
	 * @return
	 */
	public static String getDateString(java.util.Date date, String lang) {
		try {
			String result;
			if (DateUtility.useSolarDate(lang)) {
				String sol = DateUtility.gerToSolar((1900 + date.getYear()) + "/"
						+ (date.getMonth() + 1) + "/" + date.getDate(),
						PATTERNS1);
				String[] res = sol.split("/");
				result = res[0] + "/"
						+ (res[1].length() < 2 ? "0" + res[1] : res[1]) + "/"
						+ (res[2].length() < 2 ? "0" + res[2] : res[2]);
			} else {
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(date.getTime());
				int month = calendar.get(Calendar.MONTH) + 1;
				int day = calendar.get(Calendar.DAY_OF_MONTH);
				result = calendar.get(Calendar.YEAR) + "-"
						+ ((month < 10) ? "0" + month : month) + "-"
						+ ((day < 10) ? "0" + day : day);
			}
			return result;
		} catch (Exception e) {
			return "";
		}
	}

	public static Date getDate(String dateString, String lang) {
		if (DateUtility.useSolarDate(lang)) {
			return solarToDate(dateString);
		} else {
			return gerToDate(dateString);
		}
	}

	public static String convertGregorianToSolarString(Date date) {
		return getDateString(date, "fa");
	}

	public static int[] convertGregorianToSolar(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(date.getTime());
		return convertGregorianToSolar(c.get(Calendar.YEAR), c
				.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
	}

	// Golnari:this method not work properly if used alone
	private static int[] convertGregorianToSolar(int Year, int Month, int Day) {
		int LeapDay = 0;
		int Days = 0;
		boolean PrevGregorianLeap = false;

		if (IsDateValid(dkGregorian, Year, Month, Day)) {
			PrevGregorianLeap = IsLeapYear(dkGregorian, Year - 1);
			Days = DaysToDate(dkGregorian, Year, Month, Day);
			Year = Year - 622;
			if (IsLeapYear(dkSolar, Year))
				LeapDay = 1;
			else
				LeapDay = 0;
			if (PrevGregorianLeap && (LeapDay == 1))
				Days = Days + 287;
			else
				Days = Days + 286;
			if (Days > (365 + LeapDay)) {
				Year = Year + 1;
				Days = Days - (365 + LeapDay);
			}

			int[] MonthDay = DateOfDay(dkSolar, Days, Year);
			int[] result = new int[3];

			result[0] = Year;
			result[1] = MonthDay[0];
			result[2] = MonthDay[1];

			return result;
		}
		return null;
	}

	public static int[] SolarToGregorian(int Year, int Month, int Day) {
		int LeapDay = 0;
		int Days = 0;
		boolean PrevSolarLeap = false;
		if (IsDateValid(dkSolar, Year, Month, Day)) {
			PrevSolarLeap = IsLeapYear(dkSolar, Year - 1);
			Days = DaysToDate(dkSolar, Year, Month, Day);
			Year = Year + 621;
			if (IsLeapYear(dkGregorian, Year))
				LeapDay = 1;
			else
				LeapDay = 0;
			if (PrevSolarLeap && (LeapDay == 1))
				Days = Days + 80;
			else
				Days = Days + 79;
			if (Days > (365 + LeapDay)) {
				Year = Year + 1;
				Days = Days - (365 + LeapDay);
			}
			int[] MonthDay = DateOfDay(dkGregorian, Days, Year);
			int[] YearMonthDay = new int[3];
			YearMonthDay[0] = Year;
			YearMonthDay[1] = MonthDay[0];
			YearMonthDay[2] = MonthDay[1];
			return YearMonthDay;

		}

		return null;

	}

	// public static void ConvertSolarToGregorian(int Year, int Month, int Day)
	// {
	// int LeapDay = 0;
	// int Days = 0;
	// boolean PrevSolarLeap = false;
	// if (IsDateValid(dkSolar, Year, Month, Day)) {
	// PrevSolarLeap = IsLeapYear(dkSolar, Year - 1);
	// Days = DaysToDate(dkSolar, Year, Month, Day);
	// Year = Year + 621;
	// if (IsLeapYear(dkGregorian, Year))
	// LeapDay = 1;
	// else
	// LeapDay = 0;
	// if (PrevSolarLeap && (LeapDay == 1))
	// Days = Days + 80;
	// else
	// Days = Days + 79;
	// if (Days > (365 + LeapDay)) {
	// Year = Year + 1;
	// Days = Days - (365 + LeapDay);
	// }
	// int[] MonthDay = DateOfDay(dkGregorian, Days, Year);
	//				
	// GregorianYear = Year;
	// GregorianMonth = MonthDay[0];
	// GregorianDay = MonthDay[1];
	// }
	// }
	public static int[] convertSolarToGregorian(int Year, int Month, int Day) {
		int LeapDay = 0;
		int Days = 0;
		boolean PrevSolarLeap = false;
		if (IsDateValid(dkSolar, Year, Month, Day)) {
			PrevSolarLeap = IsLeapYear(dkSolar, Year - 1);
			Days = DaysToDate(dkSolar, Year, Month, Day);
			Year = Year + 621;
			if (IsLeapYear(dkGregorian, Year))
				LeapDay = 1;
			else
				LeapDay = 0;
			if (PrevSolarLeap && (LeapDay == 1))
				Days = Days + 80;
			else
				Days = Days + 79;
			if (Days > (365 + LeapDay)) {
				Year = Year + 1;
				Days = Days - (365 + LeapDay);
			}
			int[] MonthDay = DateOfDay(dkGregorian, Days, Year);
			int[] result = new int[3];
			result[0] = Year;
			result[1] = MonthDay[0];
			result[2] = MonthDay[1];
			return result;
		}
		return null;
	}

	public static int getMaximumSolarMonthDays(int year, int month) {
		if (month <= 6)
			return 31;
		if ((month >= 7) && (month <= 11))
			return 30;
		if (month == 12) {
			if (IsLeapYear(dkSolar, year))
				return 30;
			else
				return 29;
		}
		return 30;
	}

	public static boolean isValidSolar(int year, int month, int day) {
		if ((year >= 1000) && (year <= 1600))
			return IsDateValid(0, year, month, day);
		return false;
	}

	public static boolean isValidGrigor(int year, int month, int day) {
		if ((year >= 1000) && (year <= 2500))
			return IsDateValid(1, year, month, day);
		return false;
	}

	public static int[] getGrigorFromSolarDateString(String dateStr) {
		// System.out.println("dateStr: " + dateStr);
		StringTokenizer st = new StringTokenizer(dateStr, " -/");
		int year = 0, month = 0, day = 0;
		if (st.hasMoreTokens())
			year = Integer.parseInt(st.nextToken());
		if (st.hasMoreTokens())
			month = Integer.parseInt(st.nextToken());
		if (st.hasMoreTokens())
			day = Integer.parseInt(st.nextToken());

		int[] result = new int[3];
		if (isValidSolar(year, month, day)) {
			result = SolarToGregorian(year, month, day);
			return result;
		}
		if (isValidSolar(day, month, year)) {
			result = SolarToGregorian(day, month, year);
			return result;
		}
		return null;
	}

	public static String ConverToGregorianFromSolarDateString(String dateStr) {
		// System.out.println("Solar dateStr: " + dateStr);
		StringTokenizer st = new StringTokenizer(dateStr, " -/");
		int year = 0, month = 0, day = 0;
		if (st.hasMoreTokens())
			year = Integer.parseInt(st.nextToken());
		if (st.hasMoreTokens())
			month = Integer.parseInt(st.nextToken());
		if (st.hasMoreTokens())
			day = Integer.parseInt(st.nextToken());

		if (isValidSolar(year, month, day)) {
			int temp[] = convertSolarToGregorian(year, month, day);
			return temp[0] + "-" + temp[1] + "-" + temp[2];
		}
		if (isValidSolar(day, month, year)) {
			int temp[] = convertSolarToGregorian(day, month, year);
			return temp[0] + "-" + temp[1] + "-" + temp[2];
		}
		return null;
	}

	public static int[] getSolarFromGrigorDateString(String dateStr) {
		// System.out.println("dateStr: " + dateStr);
		StringTokenizer st = new StringTokenizer(dateStr, " -/");
		int year = 0, month = 0, day = 0;
		if (st.hasMoreTokens())
			year = Integer.parseInt(st.nextToken());
		if (st.hasMoreTokens())
			month = Integer.parseInt(st.nextToken());
		if (st.hasMoreTokens())
			day = Integer.parseInt(st.nextToken());

		int[] result = new int[3];
		if (isValidGrigor(year, month, day)) {
			result = GregorianToSolar(year, month, day);
			return result;
		}
		if (isValidGrigor(day, month, year)) {
			result = GregorianToSolar(day, month, year);
			return result;
		}
		return null;
	}

	public static String solarToGer(String dateStr) {
		int[] teep = solarToGerInt(dateStr);
		int year = teep[0], month = teep[1], day = teep[2];
		if (isValidSolar(year, month, day)) {
			int temp[] = convertSolarToGregorian(year, month, day);
			return temp[1] + "-" + temp[2] + "-" + temp[0];
		}
		if (isValidSolar(day, month, year)) {
			int temp[] = convertSolarToGregorian(day, month, year);
			return temp[1] + "-" + temp[2] + "-" + temp[0];
		}
		return null;
	}

	public static int[] solarToGerInt(String dateStr) {
		// System.out.println("Solar dateStr: " + dateStr);
		StringTokenizer st = new StringTokenizer(dateStr, " -/");
		int year = 0, month = 0, day = 0;
		if (st.hasMoreTokens())
			year = Integer.parseInt(st.nextToken());
		if (st.hasMoreTokens())
			month = Integer.parseInt(st.nextToken());
		if (st.hasMoreTokens())
			day = Integer.parseInt(st.nextToken());

		if (isValidSolar(year, month, day)) {
			return convertSolarToGregorian(year, month, day);
		}
		if (isValidSolar(day, month, year)) {
			return convertSolarToGregorian(day, month, year);
		}
		return null;
	}

	public static java.util.Date gerToDate(String dateStr, String format) {
		try {
			DateFormat formatter = new SimpleDateFormat(format);
			return (java.util.Date) formatter.parse(dateStr);

		} catch (ParseException e) {
			System.out.println("fail to parse gerogorain date format ");
		}
		return null;
	}

	public static java.util.Date gerToDate(String dateStr) {
		try {
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			return (java.util.Date) formatter.parse(dateStr);

		} catch (ParseException e) {
			System.out.println("fail to parse gerogorain date format ");
		}
		return null;
	}

	public static java.util.Date solarToDate(String dateStr) {
		try {
			return gerToDate(ConverToGregorianFromSolarDateString(dateStr),
					"yyyy-MM-dd");
		} catch (Exception e) {
			return null;
		}
	}

	public static String solarToGerDateTime(String dateStr) {
		// System.out.println("Solar dateStr: " + dateStr);
		StringTokenizer st = new StringTokenizer(dateStr, " -/:");
		int year = 0, month = 0, day = 0;
		int hour = 0, minute = 0, second = 0;
		String PMorAM = "AM";
		if (st.hasMoreTokens())
			year = Integer.parseInt(st.nextToken());
		if (st.hasMoreTokens())
			month = Integer.parseInt(st.nextToken());
		if (st.hasMoreTokens())
			day = Integer.parseInt(st.nextToken());
		if (st.hasMoreTokens())
			hour = Integer.parseInt(st.nextToken());
		if (st.hasMoreTokens())
			minute = Integer.parseInt(st.nextToken());
		if (st.hasMoreTokens())
			second = Integer.parseInt(st.nextToken());
		if (st.hasMoreTokens())
			PMorAM = st.nextToken();
		if (PMorAM.equalsIgnoreCase("\u0635\u0628\u062d"))
			PMorAM = "AM";
		else
			PMorAM = "PM";

		String hhour, mminute, ssecond;
		hhour = (hour <= 9) ? "0" + hour : hour + "";
		mminute = (minute <= 9) ? "0" + minute : minute + "";
		ssecond = (second <= 9) ? "0" + second : second + "";

		// if(PMorAM.equalsIgnoreCase("PM"))
		// hour += 12;
		// SimpleDateFormat format = new
		// SimpleDateFormat("MMM dd,yyyy hh:mm:ss a");
		if (isValidSolar(year, month, day)) {
			int temp[] = convertSolarToGregorian(year, month, day);
			String date = gregorianMonthName[temp[1] - 1] + " " + temp[2]
					+ ", " + temp[0] + " " + hhour + ":" + mminute + ":"
					+ ssecond + " " + PMorAM + " " + "IRST";
			// Timestamp ts = new
			// Timestamp(temp[1],temp[2],temp[3],hour,minute,second,0);
			// String date = format.format(ts);
			return date;
			// return temp[1] + "-" + temp[2] + "-" + temp[0];

		}
		if (isValidSolar(day, month, year)) {
			int temp[] = convertSolarToGregorian(day, month, year);
			return temp[1] + "-" + temp[2] + "-" + temp[0];
		}
		return null;
	}

	// // farshid Updated
	// // added to have month First
	public static String gerToSolar(String dateStr, SimpleDateFormat sdf) {
		try {
			java.util.Date date = (java.util.Date) sdf.parse(dateStr);
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(date);
			int year = gc.get(GregorianCalendar.YEAR);
			int month = gc.get(GregorianCalendar.MONTH) + 1;
			int day = gc.get(GregorianCalendar.DATE);
			if (isValidGrigor(year, month, day)) {
				int[] temp = convertGregorianToSolar(year, month, day);
				String m, d;
				m = (temp[1] <= 9) ? "0" + String.valueOf(temp[1]) : ""
						+ temp[1];
				d = (temp[2] <= 9) ? "0" + String.valueOf(temp[2]) : ""
						+ temp[2];
				// farshid updated - correcting sequence in date fields
				return d + "-" + m + "-" + temp[0];
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String dateTimeGerToSolar(String dateTimeStr,
			SimpleDateFormat sdf) {
		try {
			java.util.Date date = (java.util.Date) sdf.parse(dateTimeStr);
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(date);
			int year = gc.get(GregorianCalendar.YEAR);
			int month = gc.get(GregorianCalendar.MONTH);
			int day = gc.get(GregorianCalendar.DATE);
			int hour = gc.get(GregorianCalendar.HOUR);
			int minute = gc.get(GregorianCalendar.MINUTE);

			if (isValidGrigor(year, month, year)) {
				int[] temp = convertGregorianToSolar(year, month, day);
				String m, d;
				if ((temp[1] <= 9))
					m = "0" + String.valueOf(temp[1]);
				else
					m = String.valueOf(temp[1]);
				if ((temp[2] <= 9))
					d = "0" + String.valueOf(temp[2]);
				else
					d = String.valueOf(temp[2]);
				return temp[0] + "-" + m + "-" + d + " " + hour + ":" + minute;
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static String gerToSolar(String dateStr, int pattern) {
		String year = "";
		String month = "";
		String day = "";
		if (pattern == PATTERNS1) {
			year = dateStr.substring(0, 4);
			month = dateStr.substring(dateStr.indexOf('/'), dateStr
					.lastIndexOf('/'));
			day = dateStr.substring(dateStr.lastIndexOf('/'));
		} else if (pattern == PATTERNS2) {
			year = dateStr.substring(0, 4);
			month = dateStr.substring(dateStr.indexOf('-'), dateStr
					.lastIndexOf('-'));
			day = dateStr.substring(dateStr.lastIndexOf('-'));
		} else if (pattern == PATTERNS3) {
			year = dateStr.substring(0, 4);
			day = dateStr.substring(dateStr.indexOf('-'), dateStr
					.lastIndexOf('-'));
			month = dateStr.substring(dateStr.lastIndexOf('-'));
		}

		String str = year + "/" + month + "/" + day;

		return ConvertToSolarFromGregorianDateString(str);
	}

	public static int[] gerToSolar(Timestamp ts) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTimeInMillis(ts.getTime());
		return convertGregorianToSolar(gc.get(GregorianCalendar.YEAR), gc
				.get(GregorianCalendar.MONTH) + 1, gc
				.get(GregorianCalendar.DATE));
	}

	/**
	 * 
	 * @param year
	 * @param month
	 *            : 1,2,3
	 * @param day
	 * @param minute
	 * @param second
	 * @return
	 */
	public static Timestamp solToGer(int year, int month, int day, int minute,
			int second) {
		int[] gd = convertSolarToGregorian(year, month, day);
		GregorianCalendar gc = new GregorianCalendar(gd[0], gd[1] - 1, gd[2],
				minute, second);
		return new Timestamp(gc.getTimeInMillis());
	}

	/**
	 * 
	 * @param year
	 * @param month
	 *            : : 1,2,3
	 * @param day
	 * @return
	 */
	public static Timestamp solToGer(int year, int month, int day) {

		return solToGer(year, month, day, 0, 0);
	}

	public static String gerToSolTimestamp(Timestamp ts) {
		int[] fd = gerToSolar(ts);
		return fd[0] + "/" + fd[1] + "/" + fd[2];
	}

	/**
	 * public static String greToSolmmDDyyyy(String dateStr){
	 * //System.out.println("Gregorian dateStr: " + dateStr); StringTokenizer st
	 * = new StringTokenizer(dateStr, " -/"); int year = 0, month = 0, day = 0;
	 * if(st.hasMoreTokens()) month = Integer.parseInt(st.nextToken());
	 * if(st.hasMoreTokens()) day = Integer.parseInt(st.nextToken());
	 * if(st.hasMoreTokens()) year = Integer.parseInt(st.nextToken());
	 * 
	 * if(isValidGrigor(year, month, day)){ int[] temp =
	 * convertGregorianToSolar(year, month, day); String m,d; if((temp[1]<=9)) m
	 * = "0" + String.valueOf(temp[1]); else m = String.valueOf(temp[1]);
	 * if((temp[2]<=9)) d = "0" + String.valueOf(temp[2]); else d =
	 * String.valueOf(temp[2]); return temp[0] + "-" + m + "-" + d; }
	 * 
	 * return null; }
	 */
	/**
	 * @deprecated use gerToSol instead.
	 * @param dateStr
	 *            : Format yyyy/mm/dd
	 * @return
	 */
	private static String ConvertToSolarFromGregorianDateString(String dateStr) {
		// System.out.println("Gregorian dateStr: " + dateStr);
		StringTokenizer st = new StringTokenizer(dateStr, " -/");
		int year = 0, month = 0, day = 0;
		if (st.hasMoreTokens())
			year = Integer.parseInt(st.nextToken());
		if (st.hasMoreTokens())
			month = Integer.parseInt(st.nextToken());
		if (st.hasMoreTokens())
			day = Integer.parseInt(st.nextToken());

		if (isValidGrigor(year, month, day)) {
			int[] temp = convertGregorianToSolar(year, month, day);
			String m, d;
			if ((temp[1] <= 9))
				m = "0" + String.valueOf(temp[1]);
			else
				m = String.valueOf(temp[1]);
			if ((temp[2] <= 9))
				d = "0" + String.valueOf(temp[2]);
			else
				d = String.valueOf(temp[2]);
			return temp[0] + "/" + m + "/" + d;
		}
		if (isValidGrigor(day, month, year)) {
			int[] temp = convertGregorianToSolar(day, month, year);
			String m, d;
			if ((temp[1] <= 9))
				m = "0" + String.valueOf(temp[1]);
			else
				m = String.valueOf(temp[1]);
			if ((temp[2] <= 9))
				d = "0" + String.valueOf(temp[2]);
			else
				d = String.valueOf(temp[2]);
			return temp[0] + "/" + m + "/" + d;
		}
		return null;
	}

	/**
	 * @deprecated use dateTimeGerToSolar
	 * @param dateStr
	 * @return
	 */
	public static String ConvertToSolarFromGregorianDateTimeString(
			String dateStr) {
		// System.out.println("Gregorian dateTimeStr: " + dateStr);
		if (dateStr == null || dateStr == "")
			return null;
		// // farshid Updated
		// // Adding a '/' Character to method
		// // StringTokenizer st = new StringTokenizer(dateStr, " -,");
		StringTokenizer st = new StringTokenizer(dateStr, " /-,");
		// // end of adding
		// StringTokenizer st = new StringTokenizer(dateStr, " /-,");
		int year = 0, month = 0, day = 0;
		String monthStr = null, time = null, PMorAM = null;
		if (st.hasMoreTokens())
			monthStr = st.nextToken();
		if (st.hasMoreTokens())
			day = Integer.parseInt(st.nextToken());
		if (st.hasMoreTokens())
			year = Integer.parseInt(st.nextToken());
		if (st.hasMoreTokens())
			time = st.nextToken();
		if (st.hasMoreTokens())
			PMorAM = st.nextToken();
		month = getGregorianMonthNumber(monthStr);

		if (isValidGrigor(year, month, day)) {
			int temp[] = convertGregorianToSolar(year, month, day);
			String m, d;
			if ((temp[1] <= 9))
				m = "0" + String.valueOf(temp[1]);
			else
				m = String.valueOf(temp[1]);
			if ((temp[2] <= 9))
				d = "0" + String.valueOf(temp[2]);
			else
				d = String.valueOf(temp[2]);
			return temp[0]
					+ "-"
					+ m
					+ "-"
					+ d
					+ " "
					+ ConvertToSolarFromGregorianTimeString(time + " " + PMorAM);
		}
		if (isValidGrigor(day, month, year)) {
			int[] temp = convertGregorianToSolar(day, month, year);
			String m, d;
			if ((temp[1] <= 9))
				m = "0" + String.valueOf(temp[1]);
			else
				m = String.valueOf(temp[1]);
			if ((temp[2] <= 9))
				d = "0" + String.valueOf(temp[2]);
			else
				d = String.valueOf(temp[2]);
			return temp[0]
					+ "-"
					+ m
					+ "-"
					+ d
					+ ConvertToSolarFromGregorianTimeString(time + " " + PMorAM);
		}
		return null;
	}

	public static String ConvertToSolarFromGregorianTimeString(String timeStr) {
		// System.out.println("Gregorian TimeStr: " + timeStr);
		if (timeStr == null || timeStr == "")
			return null;
		StringTokenizer st = new StringTokenizer(timeStr, " :");
		int hour = 0, minute = 0, second = 0;
		String PMorAM = null, SolarPMorAM = null;
		if (st.hasMoreTokens())
			hour = Integer.parseInt(st.nextToken());
		if (st.hasMoreTokens())
			minute = Integer.parseInt(st.nextToken());
		if (st.hasMoreTokens())
			second = Integer.parseInt(st.nextToken());
		if (st.hasMoreTokens())
			PMorAM = st.nextToken();
		SolarPMorAM = getGregorianPMorAM(PMorAM);

		return (hour <= 9 ? "0" + hour : hour) + ":"
				+ (minute <= 9 ? "0" + minute : minute) + ":"
				+ (second <= 9 ? "0" + second : second) + " " + SolarPMorAM;

	}

	public static String getGregorianPMorAM(String PMorAM) {
		return PMorAM.equalsIgnoreCase("AM") ? "\u0635\u0628\u062d"
				: "\u0639\u0635\u0631";
	}

	public static String persianAlphaDate(String PersianDate) {
		int fd = 0, fm = 0, fy = 0, ty1, ty2;
		String fmonth;
		StringTokenizer st = new StringTokenizer(PersianDate, " -/");
		if (st.hasMoreTokens())
			fy = Integer.parseInt(st.nextToken());
		if (st.hasMoreTokens())
			fm = Integer.parseInt(st.nextToken());
		if (st.hasMoreTokens())
			fd = Integer.parseInt(st.nextToken());
		fmonth = "";
		String[] monthes = { "Ù�Ø±ÙˆØ±Ø¯ÛŒÙ†", "Ø§Ø±Ø¯ÛŒØ¨Ù‡Ø´Øª", "Ø®Ø±Ø¯Ø§Ø¯", "ØªÛŒØ±", "Ù…Ø±Ø¯Ø§Ø¯",
				"Ø´Ù‡Ø±ÛŒÙˆØ±", "Ù…Ù‡Ø±", "Ø¢Ø¨Ø§Ù†", "Ø¢Ø°Ø±", "Ø¯ÛŒ", "Ø¨Ù‡Ù…Ù†", "Ø§Ø³Ù�Ù†Ø¯" };
		fmonth = monthes[fm - 1];

		return fd + " " + fmonth + " " + fy;
	}

	/**
	 * 
	 * @param lang
	 *            en or fa
	 * @return
	 */
	public static String today(String lang, int dayOffset) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		calendar.add(Calendar.DAY_OF_MONTH, dayOffset);
		return getDateString(calendar.getTime(), lang);
	}

	public static void main(String[] args) {
		int[] result = gerToSolar(new Timestamp(System.currentTimeMillis()));
		System.out.println(result[0] + "/" + result[1] + "/" + result[2]);
		System.out.println("solarToDate> " + solarToDate("1388/07/18"));
		System.out.println("gerToDate>" + gerToDate("17/1/2008", "dd/MM/yyyy"));
		System.out.println("getDateString fa>"
				+ getDateString(new Date(System.currentTimeMillis()), "fa"));
		System.out.println("gerToSolar>" + gerToSolar("2009-10-10", PATTERNS3));
		System.out.println("persianAlphaDate>" + persianAlphaDate("1388/7/18"));
		System.out.println("getDate fa>" + getDate("1388/7/18", "fa"));
		System.out.println("getDate en>" + getDate("2009-10-10", "en"));
		System.out.println("today fa>" + today("fa", 0));
	}

}
