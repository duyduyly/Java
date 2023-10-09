package date_time;

import org.junit.Assert;
import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

public class DateUtilsTest {

    private DateUtils dateUtils;

    public DateUtilsTest() {
        dateUtils = new DateUtils();
    }

    @Test
    public void parseStringDateToDate_FormatValid_Success() {
        String pattern = "dd-MM-yyyy hh:mm:ss";
        String dateStr = "20-01-2002 13:22:22";
        Date date = dateUtils.parseStringDateToDate(pattern, dateStr);

        Assert.assertNotNull(date);
    }

    @Test
    public void parseStringDateToDate_FormatInValid_ReturnNull() {
        String pattern = "yyyy-MM-dd hh:mm:ss";
        String dateStr = "ABC";
        Date date = dateUtils.parseStringDateToDate(pattern, dateStr);

        Assert.assertNull(date);
    }

    @Test
    public void parseLongTimeToDate() {
        Date date = dateUtils.parseLongTimeToDate(System.currentTimeMillis());
        System.out.println("parseLongTimeToDate: " + date);
    }

    @Test
    public void parseLongTimeToDateUseInstant() {
        Long currentTime = Instant.now().getEpochSecond();
        Date date = dateUtils.parseLongTimeToDateUseInstant(currentTime);
        System.out.println("parseLongTimeToDateUseInstant: " + date);
    }

    @Test
    public void parseDateToString() {
        String dateFormat = "dd-MM-yyyy hh:mm:ss";
        Date date = new Date();
        String dateStr = dateUtils.parseDateToString(dateFormat, date);
        System.out.println("parseDateToString: " + dateStr);
    }

    @Test
    public void parseDateToLocalDate() {
        Date date = new Date();
        LocalDate localDate = dateUtils.parseDateToLocalDate(date);
        System.out.println("parseDateToString: " + localDate);
    }

    @Test
    public void parseLocalDateToDate() {
        LocalDate localDate = LocalDate.now();
        String dateFormat = "dd-MM-yyyy hh:mm:ss";
        Date Date = dateUtils.parseLocalDateToDate(dateFormat, localDate);
        System.out.println("parseDateToString: " + Date);
    }
}
