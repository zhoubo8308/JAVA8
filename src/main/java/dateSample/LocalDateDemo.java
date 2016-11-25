package dateSample;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

/**
 * Created by bzou on 2016/11/16.
 */
public class LocalDateDemo {
    public static void main(String [] args){
        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.of(1982,4,16);
        LocalDate yestoday = LocalDate.of(2016, Month.NOVEMBER,15);

        System.out.println("today = " + today);
        System.out.println("birthday = " + birthday);
        System.out.println("yestoday = " + yestoday);

        System.out.println("plus 100 days = " + today.plusDays(100));
        System.out.println("plus 20 months = " + today.plusMonths(20));
        System.out.println("plus 5 years = " + today.plusYears(5));
        //ChronoUnit 中小于day的单位不支持,会抛出异常
        System.out.println("plus 50 years = " + today.plus(50, ChronoUnit.YEARS));
        //plus method accept Period , not accept Duration?
        System.out.println("plus 5 days = " + today.plus(Period.ofDays(5)));

        System.out.println("programmer's day of 2016 = " + LocalDate.of(2016,1,1).plusDays(255));
        System.out.println("this year has passed " + LocalDate.of(2016,1,1).until(today,ChronoUnit.DAYS));
        System.out.println("last day of next month " + LocalDate.of(2016,11,30).plusMonths(1));
        System.out.println("birthday of week " + birthday.getDayOfWeek());
        System.out.println("birthday of week value " + birthday.getDayOfWeek().getValue());

        System.out.println("get day of month = " + today.getDayOfMonth());
        System.out.println("get day of week = " + today.getDayOfWeek());
        System.out.println("get day of year = " + today.getDayOfYear());
        System.out.println("get month = " + today.getMonth());
        System.out.println("get month value = " + today.getMonthValue());
    }


}
