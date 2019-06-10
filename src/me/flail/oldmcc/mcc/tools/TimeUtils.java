package me.flail.oldmcc.mcc.tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeUtils {

	public class Time {

		public String monthName(int month) {
			switch (month - 1) {

			case 1:
				return "January";
			case 2:
				return "Febuary";
			case 3:
				return "March";
			case 4:
				return "April";
			case 5:
				return "May";
			case 6:
				return "June";
			case 7:
				return "July";
			case 8:
				return "August";
			case 9:
				return "September";
			case 10:
				return "October";
			case 11:
				return "November";
			case 12:
				return "December";
			default:
				return month + "";

			}
		}

		public String currentDayTime() {

			String time = new SimpleDateFormat("(HH:mm:ss)").format(Calendar.getInstance().getTime());

			return time;

		}

		public String serverTime() {

			long second = System.currentTimeMillis() / 1000;

			long minute = second / 60;

			long hour = minute / 60;

			long day = hour / 24;

			String time = "Day: " + day + " Hour: " + hour + " Minute: " + minute + " Second: " + second;

			return time;

		}

	}

}
