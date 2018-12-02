package java8;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

/**
 * java8日期api测试
 */
public class DateAPI {


    @Test
    public void clock(){
        //时钟
        Clock clock = Clock.systemDefaultZone();
        long millis = clock.millis();
        System.out.println(millis);

        Instant instant = clock.instant();
        Date date = Date.from(instant);
    }


    @Test
    public void timezones(){
        //时区
        System.out.println(ZoneId.getAvailableZoneIds());
        // prints all available timezone ids
        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");
        // ZoneRules[currentStandardOffset=+01:00]
        System.out.println(zone1.getRules());
        // ZoneRules[currentStandardOffset=-03:00]
        System.out.println(zone2.getRules());
    }

    @Test
    public void localtime(){
        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");

        LocalTime now1 = LocalTime.now(zone1);
        LocalTime now2 = LocalTime.now(zone2);
        // false
        System.out.println(now1.isBefore(now2));

        long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
        long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);

        // -3
        System.out.println(hoursBetween);
        // -239
        System.out.println(minutesBetween);


        LocalTime late = LocalTime.of(23, 59, 59);
        // 23:59:59
        System.out.println(late);

        DateTimeFormatter germanFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale.GERMAN);
        LocalTime leetTime = LocalTime.parse("13:37", germanFormatter);
        // 13:37
        System.out.println(leetTime);
    }

    @Test
    public void localdate(){
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        LocalDate yesterday = tomorrow.minusDays(2);

        System.out.println(yesterday);
        LocalDate independenceDay = LocalDate.of(2014, Month.JULY, 4);
        DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();
        // FRIDAY
        System.out.println(dayOfWeek);

        DateTimeFormatter germanFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(Locale.GERMAN);

        LocalDate xmas = LocalDate.parse("24.12.2014", germanFormatter);
        // 2014-12-24
        System.out.println(xmas);
    }

    @Test
    public void localdatetime(){
        LocalDateTime sylvester = LocalDateTime.of(2014, Month.DECEMBER, 23, 23, 59, 59);

        DayOfWeek dayOfWeek = sylvester.getDayOfWeek();
        // WEDNESDAY
        System.out.println(dayOfWeek);

        Month month = sylvester.getMonth();
        // DECEMBER
        System.out.println(month);

        long minuteOfDay = sylvester.getLong(ChronoField.MINUTE_OF_DAY);
        // 1439
        System.out.println(minuteOfDay);

        //------------------------------------------------------
        Instant instant = sylvester
                .atZone(ZoneId.systemDefault())
                .toInstant();

        Date legacyDate = Date.from(instant);
        // Wed Dec 31 23:59:59 CET 2014
        System.out.println(legacyDate);


        //-------------------------------------------
        //datetimeformatter不可变，线程安全
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy - HH:mm");
        LocalDateTime parsed = LocalDateTime.parse("Nov 03, 2014 - 07:13", formatter);
        String string = formatter.format(parsed);
        // Nov 03, 2014 - 07:13
        System.out.println(string);

    }
}
