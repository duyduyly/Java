Fast:
- [Number Format](#number-format)
- [Formating Dates And Times](#formating-dates-and-times)
- [Internationalization](#internationalization)


## Number Format📈

Example 1:

```java
    double num = 12345.678;

    NumberFormat f1 = new DecimalFormat("###,###,###.0");
    System.out.println(f1.format(num));
```
```text
12,345.7
```

Example 2:
```java
    double num = 12345.678;

    NumberFormat f2 = new DecimalFormat("000,000.000000");
    System.out.println(f2.format(num));
```
```text
012,345.678000
```

Example 3:
```java
    double num = 12345.678;

    NumberFormat f3 = new DecimalFormat("$#,###,###.##");
    System.out.println(f3.format(num));
```
```text
$12,345.68
```


## Formating Dates and Times⌚
| Symbol | Meaning          | Example               |
|--------|------------------|-----------------------|
| y      | Year             | 25,2025               |
| M      | Month            | 2,02,Feb, February    |
| d      | Day              | 7, 07                 |
| h      | Hour             | 8, 08                 |
| m      | Minutes          | 25                    |
| S      | Second           | 17                    |
| a      | a.m / p.m        | AM, PM                |
| z      | Time zone name   | Central European, CET |
| Z      | Time Zone offset | -600                  |


Example 1:
```java
    LocalDate date = LocalDate.now();
    LocalDateTime localDateTime = LocalDateTime.now();
    LocalTime localTime = LocalTime.now();

    System.out.println(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
    System.out.println(localTime.format(DateTimeFormatter.ISO_LOCAL_TIME));
    System.out.println(localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
```

```text
2025-05-26
11:02:20.8602558
2025-05-26T11:02:20.8602558
```

Example 2:
```java
    var dt = LocalDateTime.of(2025, Month.DECEMBER, 14,19,6,14);
    var dtFm1 = DateTimeFormatter.ofPattern("dd.MM.yyyy. hh:mm:ss");
    System.out.println(dt.format(dtFm1));

    var dtFm2 = DateTimeFormatter.ofPattern("MMM-dd-yy HH:mm:ss");
    System.out.println(dt.format(dtFm2));

    var dtFm3 = DateTimeFormatter.ofPattern("MMMM-dd-yy hh:mm:ss a");
    System.out.println(dt.format(dtFm3));

    System.out.println(dtFm3.format(dt));
```
```text
14.12.2025. 07:06:14
Dec-14-25 19:06:14
December-14-25 07:06:14 PM
December-14-25 07:06:14 PM
```

More Example:
```java
    var f4 = DateTimeFormatter.ofPattern("'Date:' dd.MM.yy. '| Time:' hh:mm:ss");
    System.out.println(f4.format(dt));

    var f5 = DateTimeFormatter.ofPattern("MMM.dd.yyyy. 'at' HH'h'm'm'ss's'");
    System.out.println(f5.format(dt));
```
```text
Date: 14.12.25. | Time: 07:06:14
Dec.14.2025. at 19h6m14s
```


## Internationalization
#### What is localization?
- you can make your program adaptable to multiple locales of geographic regions
- this includes:
  - translating string to difference languages
  - outputting dates in the correct format
  - outputting numbers in the correct format
  - etc...

Example 1:
```java
    Locale locale = Locale.getDefault();
    System.out.println(locale);
    
    System.out.println();
    System.out.println(Locale.ITALIAN);
    System.out.println(Locale.ITALY);
    System.out.println(new Locale("hi", "IN"));
```
```text
en_US

it
it_IT
hi_IN
```
`en` is language (mandatory)  
`US` country (optional)

- Create Locale and change Default Local `Code example` in Demo2 class

#### Localizing Number
- Difference countries have different conventions when it comes to number
- Localization can help to display the number in the appropriate locale format
- For this purpose we use NumberFormat factory methods

#### NumberFormat Factory Methods
| Method                                                             | Description                             |
|--------------------------------------------------------------------|-----------------------------------------|
| `getInstance()`, `getInstance(Locale locale)`                      | Generate purpose formatter              |
| `getNumberInstance()`, `getNumberInstance(Locale locale)`          | Same as getInstance                     |
| `getCurrencyInstance()`, `getCurrencyInstance(Locale locale)`      | For formatting monetary amount          |
| `getPercentInstant()`, `getPercentInstant(Locale locale)`          | For formatting percentages              |
| `getIntegerInstance()`,`getIntegerInstance(Locale locale)`         | Rounds decimal number before displaying |
| `getCompactNumberInstance()`,`getInstance(Locale locale, Style s)` | return compact Number Formatter         |

__Formatting Example:__
```java
        double myNum = 1234.568;

        var us = NumberFormat.getInstance(Locale.US);
        System.out.println(us.format(myNum));

        var it = NumberFormat.getInstance(Locale.ITALY);
        System.out.println(it.format(myNum));

        var ca = NumberFormat.getInstance(Locale.CANADA_FRENCH);
        System.out.println(ca.format(myNum));
```
```text
1,234.568
1.234,568
1 234,568
```
#
__Formatting Currencies__
```java
        //Formatting currencies
        double price = 12.3;

        var usCurrency = NumberFormat.getCurrencyInstance(Locale.US);
        System.out.println(usCurrency.format(price));

        var ukCurrency = NumberFormat.getCurrencyInstance(Locale.UK);
        System.out.println(ukCurrency.format(price));

        var gerCurrency = NumberFormat.getCurrencyInstance(Locale.GERMANY);
        System.out.println(gerCurrency.format(price));
```
```text
$12.30
£12.30
12,30 €
```

#
__Formatting percentages__
```java
        double discount = 0.151;

        var usDiscount = NumberFormat.getPercentInstance(Locale.US);
        System.out.println(usDiscount.format(discount));

        var gerDiscount = NumberFormat.getPercentInstance(Locale.GERMANY);
        System.out.println(gerDiscount.format(discount));
```
```text
15%
15 %
```

#### Warning when parsing with number 
```java
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
    // NOTE: if you use non-US like locale, the parsing will throw an exception
}
```
#
#### Using CompactNumberFormat (new In java 17)

__Example:__
```java
        int comNumber  = 8_765_432;

        var us1 = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);
        System.out.println(us1.format(comNumber));

        var us2 = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.LONG);
        System.out.println(us2.format(comNumber));

        var us3 = NumberFormat.getCompactNumberInstance(Locale.GERMANY, NumberFormat.Style.SHORT);
        System.out.println(us3.format(comNumber));

        var us4 = NumberFormat.getCompactNumberInstance(Locale.GERMANY, NumberFormat.Style.LONG);
        System.out.println(us4.format(comNumber));
```
```text
9M
9 million
9 Mio.
9 Millionen
```
#
#### DateTimeFormatter Factory Methods
| Method                                                             | Description                    |
|--------------------------------------------------------------------|--------------------------------|
| `ofLocalizadDate(FormatStyle dateStyle)`                           | For formatting dates           |
| `ofLocalizedTime(FormatStyle timeStyle)`                           | For Formatting Times           |
| `ofLocalizedDateTime(FormatStyle dateStyle,FormatStyle timeStyle)` | For formatting dates and times |
| `ofLocalizedDateTime(FormatStyle dateTimeStyle)`                   | For formatting dates and times |

__Example:__
```java
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
```
```text
14/09/2023
2023 Sep 14
Croatian
Croatia
4,32 HRK
```

