package dateSample;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * Created by bzou on 2016/11/17.
 */
public class LocalTimeDemo {
    public static void main(String ...args){
        System.out.println("now = " + LocalTime.now());
        System.out.println("bedtime = " + LocalTime.of(22,30));
        System.out.println("wakeup time = " + LocalTime.of(22,30).plusHours(9));
        System.out.println("wakeup time2 = " + LocalTime.of(22,30).plus(Duration.of(9, ChronoUnit.HOURS)));
        System.out.println("tosecondofDay = " + LocalTime.now().toSecondOfDay());
        System.out.println("tonanoofDay = " + LocalTime.now().toNanoOfDay());

        System.out.println("now with date = " + LocalDateTime.now());
        System.out.println("now add 50000 seconds = " + LocalDateTime.now().plus(50000,ChronoUnit.SECONDS));
    }
}
