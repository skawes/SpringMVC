package com.awes.TestJava;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TestJava {
	public static void main(String[] args) {
		String ddd [] = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		Date d = new Date();
		System.out.println("The current month is " + ddd[d.getMonth()-1]);
		int year = Calendar.getInstance().get(Calendar.YEAR);
		System.out.println(year);
	}
}
