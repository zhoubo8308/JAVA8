package dateSample;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 * Created by bzou on 2016/11/17.
 */
public class FormattingDemo {
    public static void main(String ... args){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("E yyyy-MM-dd HH:mm");
        System.out.println("iso_data_time formatted : "+ DateTimeFormatter.ISO_DATE_TIME.format(now));
        System.out.println("long formatted : "+ formatter.format(now));
        System.out.println("custom formatted : "+ customFormatter.format(now));
        System.out.println("with local formatted : " + formatter.withLocale(Locale.CHINA).format(now.atZone(ZoneId.of("Asia/Shanghai"))));
        System.out.println("church's day " + LocalDate.parse("1903-06-04"));
        System.out.println("church's day " + ZonedDateTime.parse("1969-07-16 03:32:00-0400",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssxx")));
    }
}
