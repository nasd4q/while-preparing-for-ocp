package basic;

import java.time.*;

public class Example {
    public static void main(String[] args) {
        System.out.println(LocalDate.now()); // 2020-05-25
        System.out.println(LocalTime.now()); //17:54:05.395425
        System.out.println(LocalDateTime.now()); //2020-05-25T17:54:05.395671
        System.out.println(ZonedDateTime.now()); //2020-05-25T17:54:05.396044+02:00[Europe/Paris]

        // System.out.println(LocalDate.of(2022, 0, 31));
        //     java.time.DateTimeException: Invalid value for MonthOfYear (valid values 1 - 12)

        System.out.println(LocalDate.of(2022, 1, 31));

        // System.out.println(LocalDate.of(2022, Month.APRIL, 31));
        //     java.time.DateTimeException: Invalid date 'APRIL 31'

        System.out.println(LocalDate.of(2022, Month.APRIL, 30));

    }
}
