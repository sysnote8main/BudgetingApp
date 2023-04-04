package com.github.sysnote8main.BudgetingApp.Modules;

import com.github.sysnote8main.BudgetingApp.Enums.DayTypes;

import java.util.Calendar;

public class SimpleDate {
    static int[] days_per_month = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	static Calendar cal = Calendar.getInstance();
	public static int getYear() {
		return cal.get(Calendar.YEAR);
	}
	public static int getMonth() {
		return cal.get(Calendar.MONTH);
	}
    public static int getDay() {return cal.get(Calendar.DAY_OF_MONTH);}
	public static DayTypes getDay_of_month() {
		return DayTypes.values()[cal.get(Calendar.DAY_OF_MONTH)];
	}
    private static String digitPadding(int digit, Object obj) {
        return String.format("%0"+String.valueOf(digit)+"d",obj);
    }
    public static String getFormattedText() {
        return String.join("-",digitPadding(2,getYear()),digitPadding(2,getMonth()),digitPadding(2,getDay()));
    }
    public static int getDaysOnMonth(int month) {
        return days_per_month[month-1];
    }
}
