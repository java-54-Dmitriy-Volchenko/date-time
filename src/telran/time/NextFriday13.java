package telran.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public class NextFriday13 implements TemporalAdjuster {

	@Override
	public Temporal adjustInto(Temporal temporal) {
		  LocalDate date = LocalDate.from(temporal);
		  LocalDate nextFriday = date.with(DayOfWeek.FRIDAY);
	        if (date.getDayOfWeek() == DayOfWeek.FRIDAY && date.getDayOfMonth() < 13) {
	            nextFriday = nextFriday.plusWeeks(1);
	        }
	        while (nextFriday.getDayOfMonth() != 13) {
	            nextFriday = nextFriday.plusWeeks(1);
	        }
		return nextFriday;
	}

}