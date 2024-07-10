package java8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.Map;

/**
 * LocalDate
 * LocalTime
 * LocalDateTime
 */
public class DateAndTimeAPI {

    public static void main(String[] args) {
        demoLocalDate();
    }

    //The LocalDate represents a date in ISO format (yyyy-MM-dd) without time.
    public static void demoLocalDate() {
        LocalDate now = LocalDate.now();
        LocalDate localDate1 = LocalDate.of(2023, 1, 3);
        LocalDate localDate2 = LocalDate.parse("2016-08-16");
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        LocalDate yesterday = LocalDate.now().minusDays(1);
        LocalDate lastMonth = LocalDate.now().minusMonths(1);
        LocalDate nextMonth = LocalDate.now().plusMonths(1);
        LocalDate previousMonthSameDay = LocalDate.now().minus(1, ChronoUnit.MONTHS);
        Integer day = now.getDayOfMonth();
        Integer month = now.getMonthValue();
        Integer year = now.getYear();
        Integer dayOfYear = now.getDayOfYear();
        Integer dayOfWeek = now.getDayOfWeek().getValue();
        LocalDate todayInVietNam = LocalDate.now(ZoneId.of("UTC+00:00"));//can change timezone for ZoneId

        //get time start day
        LocalDateTime beginningOfDay = LocalDate.parse("2016-06-12").atStartOfDay();

        //get first day of Month
        LocalDate firstDayOfMonth = LocalDate.parse("2016-06-12")
                .with(TemporalAdjusters.firstDayOfMonth());


        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("now", now);
        objectMap.put("localDate1", localDate1);
        objectMap.put("localDate2", localDate2);
        objectMap.put("tomorrow", tomorrow);
        objectMap.put("yesterday", yesterday);
        objectMap.put("lastMonth", lastMonth);
        objectMap.put("nextMonth", nextMonth);
        objectMap.put("previousMonthSameDay", previousMonthSameDay);
        objectMap.put("day", day);
        objectMap.put("month", month);
        objectMap.put("year", year);
        objectMap.put("dayOfYear", dayOfYear);
        objectMap.put("dayOfWeek", dayOfWeek);
        objectMap.put("beginningOfDay", beginningOfDay);
        objectMap.put("firstDayOfMonth", firstDayOfMonth);
        objectMap.put("todayInVietNam", todayInVietNam);
        show(objectMap);


        String dateA = "2016-06-12";
        String dateB = "2016-06-11";
        //compare two date
        boolean notBefore = LocalDate.parse(dateA)
                .isBefore(LocalDate.parse(dateB));

        boolean isAfter = LocalDate.parse(dateA)
                .isAfter(LocalDate.parse(dateB));

        boolean isEqual = LocalDate.parse(dateA)
                .isEqual(LocalDate.parse(dateB));

        //check leap year
        boolean leapYear = LocalDate.parse("2016-04-02").isLeapYear();


        System.out.println("Compare: ");
        Map<String, Object> compare = new HashMap<>();
        compare.put("notBefore", notBefore);
        compare.put("isAfter", isAfter);
        compare.put("leapYear", leapYear);
        compare.put("isEqual", isEqual);
        show(compare);
    }


    public static void show(Map<String, Object> localDateMap) {
        localDateMap.forEach((key, value) -> {
            System.out.println(key + ": " + value);
        });
        System.out.println("---------------------------");
        System.out.println();
    }
}
