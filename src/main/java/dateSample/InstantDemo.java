package dateSample;

import java.time.Duration;
import java.time.Instant;

/**
 * Created by bzou on 2016/11/16.
 * The Instant and Duration class are immutable.
 * and all methods such as multipleBy and minus return a new instance
 */
public class InstantDemo {
    public static void main(String [] args){
        Instant start = Instant.now();
        try{
            Thread.currentThread().sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }
        Instant end = Instant.now();
        //between paramter:The specified temporal objects must support the SECONDS unit. For full accuracy,
        // either the NANOS unit or the NANO_OF_SECOND field should be supported
        Duration timeElapsed = Duration.between(start,end);
        System.out.println("start = "+ start + " end = " +end);
        System.out.println(timeElapsed.toMillis());
        System.out.println(timeElapsed.toNanos());
        System.out.println(timeElapsed.toMinutes());
        System.out.println(timeElapsed.toHours());
        System.out.println(timeElapsed.toDays());
        System.out.println(timeElapsed.multipliedBy(10).minus(timeElapsed).isNegative());
    }

}

/**
 *
 * start = 2016-11-16T08:14:06.843Z end = 2016-11-16T08:14:08.843Z
 2000
 2000000000
 0
 0
 0
 false

 */
