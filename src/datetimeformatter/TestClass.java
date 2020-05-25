package datetimeformatter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class TestClass {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2020, Month.JANUARY, 20);
        LocalTime time = LocalTime.of(11, 12, 34);
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        DateTimeFormatter shortF = DateTimeFormatter
                .ofLocalizedDateTime(FormatStyle.SHORT);
        DateTimeFormatter mediumF = DateTimeFormatter
                .ofLocalizedDateTime(FormatStyle.MEDIUM);


        System.out.println(shortF.format(dateTime)); // 20/01/2020 11:12
        System.out.println(mediumF.format(dateTime)); // 20 janv. 2020 à 11:12:34

        Locale.setDefault(Locale.US);

        // These 2 assignements necessary for the change in formatting style change to be reflected...
        DateTimeFormatter shortF2 = DateTimeFormatter
                .ofLocalizedDateTime(FormatStyle.SHORT);
        DateTimeFormatter mediumF2 = DateTimeFormatter
                .ofLocalizedDateTime(FormatStyle.MEDIUM);

        System.out.println(shortF2.format(dateTime)); // 1/20/20, 11:12 AM
        System.out.println(mediumF2.format(dateTime)); // Jan 20, 2020, 11:12:34 AM

    }
}
