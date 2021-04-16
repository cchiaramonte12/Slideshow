/**
 * Homework 2a Cameron Chiaramonte, ccc7sej
 * 
 * Sources: TA OH, Big Java Book
 */
public class DateLibrary {

	/**
	 * this method tests if the date is in the format YYYY-MM-DD
	 * 
	 * @param date the string that is being checked for format
	 * @return returns true if the date is correct format and false if not
	 */
	public static boolean isValidDateFormat(String date) {
		if (date.length() != 10) {
			return false;
		}

		if (date.charAt(4) != '-') {
			return false;
		}

		if (date.charAt(7) != '-') {
			return false;
		}
		
		try { //making sure they are digits with a try catch
			Integer.parseInt(date.substring(0,4));
			Integer.parseInt(date.substring(5,7));
			Integer.parseInt(date.substring(8,10));
		}
		catch (NumberFormatException e) {
			return false;	
		}
		return true;
	}

	/**
	 * this method gets the year of the given date
	 * 
	 * @param date the string YYYY-MM-DD
	 * @return returns YYYY from the date or -1 if incorrect format or the year is
	 *         greater than 9999 or less than 0
	 */
	public static int getYear(String date) {
		if (isValidDateFormat(date) != true) { // checking that it is correct format
			return -1;
		}
		if (Integer.parseInt(date.substring(0, 4)) < 0 || Integer.parseInt(date.substring(0, 4)) > 9999) { // bounds
																											// for the
																											// year
			return -1;
		}
		return Integer.parseInt(date.substring(0, 4));
	}

	/**
	 * this method gets the month of the given date
	 * 
	 * @param date the string YYYY-MM-DD
	 * @return returns MM from the date string or -1 if the format is incorrect or
	 *         if the month is less than 1 or greater than 12
	 */
	public static int getMonth(String date) {

		if (isValidDateFormat(date) != true) { // checking format
			return -1;
		}
		if (Integer.parseInt(date.substring(5, 7)) < 1 || Integer.parseInt(date.substring(5, 7)) > 12) { // bounds
			return -1;
		}
		return Integer.parseInt(date.substring(5, 7));
	}

	/**
	 * this method gets the day from the date string input
	 * 
	 * @param date YYYY-MM-DD string that is given as input
	 * @return returns the DD from the given string date or -1 if the format is
	 *         incorrect or if the day is less than 1 or greater than 31
	 */
	public static int getDay(String date) {

		if (isValidDateFormat(date) != true) { // checking format
			return -1;
		}
		if (Integer.parseInt(date.substring(8, 10)) < 1 || Integer.parseInt(date.substring(8, 10)) > 31) { // day bounds
			return -1;
		}
		return Integer.parseInt(date.substring(8, 10));
	}

	/**
	 * this method checks to see if the year is a leap year
	 * 
	 * @param year this is the four digit integer year given to check
	 * @return returns true if the year is a leap year and false if not
	 */
	public static boolean isLeapYear(int year) {
		if (year % 4 == 0 && year % 100 == 0 && year % 400 == 0) { // these are all checking the remainder when divided
																	// by 4, 100, and 400
			return true;
		} else if (year % 4 == 0 && year % 100 == 0) {
			return false;
		} else if (year % 4 == 0) {
			return true;
		}
		return false;
	}

	/**
	 * this checks if the date is a real calendar valid date including the
	 * possibility of leap years and the different days in different months
	 * 
	 * @param date the String YYYY-MM-DD that is checked
	 * @return returns true if the date is in the calendar year and false if not
	 */
	public static boolean isValidDate(String date) {
		if (date.length() != 10) {
			return false;
		}
		if (isValidDateFormat(date) == false) { // checking format
				return false;
			}
		
		if (getMonth(date) == -1) {
			return false;
		}
		
		if (getDay(date) == -1) {
			return false;
		}
		
		if (getYear(date) == -1) {
			return false;
		}
		
		else {
			if (isValidDateFormat(date)) {
				// if leap year feb has 29
				if (DateLibrary.isLeapYear(getYear(date)) && getMonth(date) == 2) {
						if (getDay(date) > 29) {
							return false;
					}
				}
				// if no leap year feb has 28
				if (DateLibrary.isLeapYear(getYear(date)) == false && getMonth(date) == 2) {
						if (getDay(date) > 28) {
							return false;
						}
				}

				// these months all should have 31 or less days
				if (getMonth(date) == 1 || getMonth(date) == 3 || getMonth(date) == 5 || getMonth(date) == 7
						|| getMonth(date) == 8 || getMonth(date) == 10 || getMonth(date) == 12) {
					if (getDay(date) > 31) {
						return false;
					}
				}

				// these months should all have 30 or less days
				if (getMonth(date) == 4 || getMonth(date) == 6 || getMonth(date) == 9 || getMonth(date) == 11) {
					if (getDay(date) > 30) {
						return false;
					}
				}
			}

			return true;
		}
		}

	/**
	 * This method compares two dates to check their order
	 * 
	 * @param date1 a date in the form YYYY-MM-Dd
	 * @param date2 a date in the form YYYY-MM-DD
	 * @return 0 if the format is incorrect, 0 if the dates are equal, 1 if the
	 *         date1 comes after date2 and -1 otherwise
	 */
	public static int compare(String date1, String date2) {

		if (isValidDateFormat(date1) != true || isValidDateFormat(date2) != true) {
			return 0;
		}

		if (date1.compareTo(date2) == 0) {
			return 0;
		}

		if (date1.compareTo(date2) > 0) {
			return 1;
		}
		return -1;
	}

	/*
	 * main method tests
	 */
	public static void main(String[] args) {
		// creating dates
		String d1 = "2020-02-29";
		String d2 = "2004-02-01";
		String d3 = "2002-11-18";
		String d4 = "1993-02-20";

		// testing format
		System.out.println(isValidDateFormat(d1));
		System.out.println(isValidDateFormat(d4));

		// getting the year from dates
		System.out.println(getYear(d1));
		System.out.println(getYear(d3));

		// getting the month from dates
		System.out.println(getMonth(d1));
		System.out.println(getMonth(d2));

		// getting the day from dates
		System.out.println(getDay(d1));
		System.out.println(getDay(d3));

		// checking if the year is a leap year
		System.out.println(isLeapYear(2020));
		System.out.println(isLeapYear(2001));

		// checking the validity of the date in the calendar year
		System.out.println(isValidDate(d4));
		System.out.println(isValidDate(d1));

		// comparing two dates
		System.out.println(compare(d1, d2));
		System.out.println(compare(d2, d3));
	}

}
