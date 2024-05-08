package telran.time.application;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.util.Locale;



record MonthYearStartPoint(int month, int year, String startPoint) {
	
}
public class PrintCalendar {

	private static final int TITLE_OFFSET = 5;
	private static final int COLUMN_WIDTH = 4;
	private enum WeekDayNames {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY}
	
	
	
	//ready
	public static void main(String[] args)  {
		try {
			MonthYearStartPoint monthYearStartPoint = getMonthYearStartPoint(args);
			printCalendar(monthYearStartPoint);
		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
//ready
	private static  MonthYearStartPoint getMonthYearStartPoint(String[] args) throws Exception{
		int monthNumber = getMonth(args);
		int year = getYear(args);
		String startPoint = getStartPoint(args);
		return new MonthYearStartPoint(monthNumber, year, startPoint);
	}
//ready
	private static String getStartPoint(String[] args) throws Exception {
	    if (args.length < 3) {
	        throw new Exception("Start day of the week is missing");
	    }
	    String startPoint = args[2].toUpperCase(); 
	    try {
	        WeekDayNames.valueOf(startPoint); 
	        return startPoint;
	    } catch (IllegalArgumentException e) {
	        throw new Exception("Invalid start day of the week");
	    }
	}
//ready
	private static int getYear(String[] args) throws Exception {
		int year = args.length < 2 ? getCurrentYear() : getYear(args[1]);
		return year;
	}
//ready
	private static int getYear(String yearStr) throws Exception {
		try {
			int res = Integer.parseInt(yearStr);
			return res;
		} catch (NumberFormatException e) {
			throw new Exception("year must be an integer number");
		}
		
	}
//ready
	private static int getCurrentYear() {
		
		return LocalDate.now().getYear();
	}

	//ready
	private static int getMonth(String[] args) throws Exception{
		int month = args.length == 0 ? getCurrentMonth() : getMonthNumber(args[0]);
		return month;
	}
// ready
	private static int getMonthNumber(String monthStr)throws Exception {
		try {
			int result = Integer.parseInt(monthStr);
			if (result < 1) {
				throw new Exception("Month cannot be less than 1");
			}
			if(result > 12) {
				throw new Exception("Month cannot be greater than 12");
			}
			return result;
		} catch (NumberFormatException e) {
			throw new Exception("Month must be a number");
		}
	}
//ready
	private static int getCurrentMonth() {
		
		return LocalDate.now().get(ChronoField.MONTH_OF_YEAR);
	}

	
	private static void printCalendar(MonthYearStartPoint monthYearStartPoint) {
		printTitle(monthYearStartPoint);
		printWeekDays(monthYearStartPoint);
		printDays(monthYearStartPoint);
		
		
	}
//ready
	private static void printDays(MonthYearStartPoint monthYearStartPoint) {	 
	    int nDays = getDaysInMonth(monthYearStartPoint);
	    int currentWeekDay = getFirstDayOfMonth(monthYearStartPoint);
	    int firstOffset = getFirstOffset(currentWeekDay, monthYearStartPoint);
	    System.out.printf("%s", " ".repeat(firstOffset));
	    for(int day = 1; day <= nDays; day++) {
	        System.out.printf("%" + COLUMN_WIDTH +"d", day);
	        currentWeekDay++;
	        int startPointIndex = getStartIndex(monthYearStartPoint);
	        if ((currentWeekDay-1-startPointIndex) % 7 == 0) {
	            System.out.println();
	        }
	    }
	}

//ready
	private static int getFirstOffset(int currentWeekDay, MonthYearStartPoint monthYearStartPoint) {
	    int startPointIndex = getStartIndex(monthYearStartPoint); ;
	    int firstWeekDayIndex = (currentWeekDay - 1 - startPointIndex + 7) % 7; // Вычисляем индекс первого дня месяца в упорядоченном массиве дней недели
	    return COLUMN_WIDTH * firstWeekDayIndex;
	}


	//ready
	private static int getFirstDayOfMonth(MonthYearStartPoint monthYearStartPoint) {
		LocalDate ld = LocalDate.of(monthYearStartPoint.year(), monthYearStartPoint.month(),
				1);
		return ld.get(ChronoField.DAY_OF_WEEK);
	}
	//ready
	private static int getDaysInMonth(MonthYearStartPoint monthYearStartPoint) {
		YearMonth ym = YearMonth.of(monthYearStartPoint.year(), monthYearStartPoint.month());
		return ym.lengthOfMonth();
	}

	//ready
	private static void printWeekDays(MonthYearStartPoint monthYearStartPoint) {
	    System.out.printf("%s", " ".repeat(1));    
	    
	    DayOfWeek[] sortedWeekDays = new DayOfWeek[7];	   
	    int startIndex = getStartIndex(monthYearStartPoint); 
	    for (int i = 0; i < 7; i++) {
	        sortedWeekDays[i] = DayOfWeek.of((startIndex + i) % 7 + 1);
	    }
	     
	   
	    for (DayOfWeek weekday : sortedWeekDays) {
	        System.out.printf("%" + COLUMN_WIDTH + "s", weekday.getDisplayName(TextStyle.SHORT, Locale.ENGLISH));
	    }
	    System.out.println();
	}
		
	
	//ready
	private static void printTitle(MonthYearStartPoint monthYearStartPoint) {
		String monthName = Month.of(monthYearStartPoint.month())
				.getDisplayName(TextStyle.FULL, Locale.getDefault());
		System.out.printf("%s%s %d\n"," ".repeat(TITLE_OFFSET), monthName, monthYearStartPoint.year());
		
		
	}
	
	//ready
	private static int getStartIndex(MonthYearStartPoint monthYearStartPoint) {
		 DayOfWeek startPoint = DayOfWeek.valueOf(monthYearStartPoint.startPoint().toUpperCase());
		 int startIndex = startPoint.getValue() - 1; 
		return startIndex;
	}
	
}