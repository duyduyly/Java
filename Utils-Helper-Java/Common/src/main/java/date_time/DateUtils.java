package date_time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

    public Date parseStringDateToDate(String pattern, String dateStr) {
        Date date = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            date = simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
        }

        return date;
    }

    public Date parseLongTimeToDate(Long time) {
        return new Date(time);
    }

    public Date parseLongTimeToDateUseInstant(Long dateUnit) {
        Instant instant = Instant.ofEpochSecond(dateUnit);
        Date date = Date.from(instant);
        return date;
    }

    public String parseDateToString(String formatDate, Date date) {
        String dateStr = "";
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatDate);
            dateStr = simpleDateFormat.format(date);
        } catch (Exception e) {
            System.err.println(e);
        }
        return dateStr;
    }

    public LocalDate parseDateToLocalDate(Date date) {
        LocalDate localDate = null;
        try {
            Instant instant = date.toInstant();
            ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
            localDate = zdt.toLocalDate();
        } catch (Exception e) {
            System.err.println(e);
        }
        return localDate;
    }

    public Date parseLocalDateToDate(String dateFormat, LocalDate localDate) {
        Date date;
        try {
            date = new SimpleDateFormat(dateFormat)
                    .parse(localDate.format(DateTimeFormatter.ofPattern(dateFormat)));
        } catch (Exception e) {
            date = null;
        }
        return date;
    }

}
