package localization;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class Demo1 {

    public static void main(String[] args) {
        double num = 12345.678;

        NumberFormat f1 = new DecimalFormat("###,###,###.0");
        System.out.println(f1.format(num));

        NumberFormat f2 = new DecimalFormat("000,000.000000");
        System.out.println(f2.format(num));

        NumberFormat f3 = new DecimalFormat("$#,###,###.##");
        System.out.println(f3.format(num));


        LocalDate date = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalTime localTime = LocalTime.now();

        System.out.println(date.getDayOfWeek());
        System.out.println(date.getMonth());
        System.out.println(date.getYear());
        System.out.println(date.getDayOfYear());


        System.out.println(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println(localTime.format(DateTimeFormatter.ISO_LOCAL_TIME));
        System.out.println(localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));


        //custom
        var dt = LocalDateTime.of(2025, Month.DECEMBER, 14,19,6,14);
        var dtFm1 = DateTimeFormatter.ofPattern("dd.MM.yyyy. hh:mm:ss");
        System.out.println(dt.format(dtFm1));

        var dtFm2 = DateTimeFormatter.ofPattern("MMM-dd-yy HH:mm:ss");
        System.out.println(dt.format(dtFm2));

        var dtFm3 = DateTimeFormatter.ofPattern("MMMM-dd-yy hh:mm:ss a");
        System.out.println(dt.format(dtFm3));

        System.out.println(dtFm3.format(dt));

        var f4 = DateTimeFormatter.ofPattern("'Date:' dd.MM.yy. '| Time:' hh:mm:ss");
        System.out.println(f4.format(dt));

        var f5 = DateTimeFormatter.ofPattern("MMM.dd.yyyy. 'at' HH'h'm'm'ss's'");
        System.out.println(f5.format(dt));
    }
}
