package telran.time.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

import org.junit.jupiter.api.Test;

import telran.time.BarMizvaAdjuster;
import telran.time.NextFriday13;

class DateTimeTest {

	@Test
	void barMizvaAdjusterTest() {
		LocalDate birthDate = LocalDate.parse("1799-06-06");
		LocalDate expected = LocalDate.of(1812, 6, 6);
		assertEquals(expected, birthDate.with(new BarMizvaAdjuster()));
	}
	@Test
	void nextFriday13Test() {
		  NextFriday13 nextFriday13 = new NextFriday13();
      
	        LocalDate testDate1 = LocalDate.of(2024, 4, 11); 
	        LocalDate expectedDate1 = LocalDate.of(2024, 9, 13); 
	        LocalDate result1 = testDate1.with(nextFriday13);
	        assertEquals(expectedDate1, result1);

	       
	        LocalDate testDate2 = LocalDate.of(2024, 4, 13); 
	        LocalDate expectedDate2 = LocalDate.of(2024, 9, 13); 
	        LocalDate result2 = testDate2.with(nextFriday13);
	        assertEquals(expectedDate2, result2);
	      
	                     
	        LocalDate testDate4 = LocalDate.of(2024, 4, 13); 
	        LocalDate expectedDate4 = LocalDate.of(2024, 9, 13); 
	        LocalDate result4 = testDate4.with(nextFriday13);
	        assertEquals(expectedDate4, result4);
	    }
	
	@Test
	void friday13RangeTest() {
		//TODO
	}
	
	@Test
	void displayCurrentDateTimeTest() {
		//The following test implies printing out
		//all current date/time in Time Zones containing string Canada
		
		displayCurrentDateTime("Canada");
		
	};
	
	private void displayCurrentDateTime(String zonePart) {
		  String[] allZoneIds = ZoneId.getAvailableZoneIds().toArray(new String[0]);
		  for (int i = 0; i < allZoneIds.length; i++) {
		        String zoneId = allZoneIds[i];
		        
		        if (zoneId.contains(zonePart)) {
		           
		            ZonedDateTime currentTime = ZonedDateTime.now(ZoneId.of(zoneId));
		           
		            String formattedTime = formatZonedDateTime(currentTime);
		            System.out.println(formattedTime);
		        }
	
		  }
	}
	private String formatZonedDateTime(ZonedDateTime zonedDateTime) {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm z");
	    return zonedDateTime.format(formatter);
}
}

	