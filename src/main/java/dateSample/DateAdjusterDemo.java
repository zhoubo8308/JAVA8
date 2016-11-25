package dateSample;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

/**
 * Created by bzou on 2016/11/17.
 */
public class DateAdjusterDemo {
    public static void main(String ...args){
        LocalDate firstTuesday = LocalDate.of(2016,11,28).with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));

        LocalDate nextMonday  = LocalDate.of(2016,11,28).with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        LocalDate thirdMondy  = LocalDate.of(2016,11,1).with(TemporalAdjusters.dayOfWeekInMonth(3,DayOfWeek.MONDAY));
        System.out.println(firstTuesday);
        System.out.println("nextMonday = "+nextMonday);
        System.out.println("3rd Monday = "+thirdMondy);
    }

    /*TemporalAdjusters NEXT_WORKDAY = w -> {
      LocalDate result = (LocalDate) w;
        do{
            result = result.plusDays(1);
        }while (result.getDayOfWeek().getValue()>=6);
        return result;
    };*/
}
