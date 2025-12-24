package localization;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class Demo2 {

    public static void main(String[] args) {
        Locale locale = Locale.getDefault();
        System.out.println(locale);

        System.out.println(Locale.ITALIAN);
        System.out.println(Locale.ITALY);
        System.out.println(new Locale("hi", "IN"));


        System.out.println();

        //Create locate
        Locale myLocale = new Locale.Builder()
                .setLanguage("en")
                .setRegion("US")
                .build();

        System.out.println(Locale.getDefault());

        System.out.println();
        //Change Default Locale
        Locale locale1 = new Locale("fr");
        Locale.setDefault(locale1);
        System.out.println(Locale.getDefault());

        System.out.println();

        //formatting number
        double myNum = 1234.568;

        var us = NumberFormat.getInstance(Locale.US);
        System.out.println(us.format(myNum));

        var it = NumberFormat.getInstance(Locale.ITALY);
        System.out.println(it.format(myNum));

        var ca = NumberFormat.getInstance(Locale.CANADA_FRENCH);
        System.out.println(ca.format(myNum));

        System.out.println();
        //Formatting currencies
        double price = 12.3;

        var usCurrency = NumberFormat.getCurrencyInstance(Locale.US);
        System.out.println(usCurrency.format(price));

        var ukCurrency = NumberFormat.getCurrencyInstance(Locale.UK);
        System.out.println(ukCurrency.format(price));

        var gerCurrency = NumberFormat.getCurrencyInstance(Locale.GERMANY);
        System.out.println(gerCurrency.format(price));


        System.out.println();
        //formatting percentages
        double discount = 0.151;

        var usDiscount = NumberFormat.getPercentInstance(Locale.US);
        System.out.println(usDiscount.format(discount));

        var gerDiscount = NumberFormat.getPercentInstance(Locale.GERMANY);
        System.out.println(gerDiscount.format(discount));

        System.out.println();
        //using CompactNumberFormat
        int comNumber  = 8_765_432;

        var us1 = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);
        System.out.println(us1.format(comNumber));

        var us2 = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.LONG);
        System.out.println(us2.format(comNumber));

        var us3 = NumberFormat.getCompactNumberInstance(Locale.GERMANY, NumberFormat.Style.SHORT);
        System.out.println(us3.format(comNumber));

        var us4 = NumberFormat.getCompactNumberInstance(Locale.GERMANY, NumberFormat.Style.LONG);
        System.out.println(us4.format(comNumber));

        System.out.println();
        // localizing dates and times
        var dtf = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        var fr = new Locale("fr", "FR");
        var dt = LocalDateTime.of(2023, Month.SEPTEMBER, 14, 9, 14, 57);
        System.out.println(dtf.withLocale(fr).format(dt));

        var dtf2 = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
        var usCus = new Locale("us", "EN");
        System.out.println(dtf2.withLocale(usCus).format(dt));

        // displaying locale
        var hr = new Locale("hr", "HR");
        var priceDis = 4.32;
        System.out.println(hr.getDisplayLanguage(Locale.US));

        System.out.println(hr.getDisplayCountry(Locale.US));

        System.out.println(NumberFormat.getCurrencyInstance(hr).format(priceDis));

    }

    //warning about parsing numbers
    private void parsingNumber() throws ParseException {
        String myNum = "15.72";
        var us = NumberFormat.getInstance(Locale.US);
        System.out.println(us.parse(myNum));
        // 15.72
        var fr = NumberFormat.getPercentInstance(Locale.FRANCE);
        System.out.println(fr.parse(myNum));
        // throws java.text.ParseException
        // (in France, decimal point is not a dot, but a comma)
    }

    private void parsingNumberWithCurrency() throws ParseException {
        String price = "$12,345.67";
        var us = NumberFormat.getInstance(Locale.US);
        double priceValue = (Double) us.parse(price);
        System.out.println(priceValue);
        // 12345.67
    }
}
