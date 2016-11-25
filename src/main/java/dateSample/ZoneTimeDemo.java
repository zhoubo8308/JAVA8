package dateSample;

import java.time.*;

/**
 * Created by bzou on 2016/11/17.
 */
public class ZoneTimeDemo {
    public static void main(String ... args){
        System.out.println("available zone ids : "+ZoneId.getAvailableZoneIds());
        LocalDateTime now = LocalDateTime.now();
        ZonedDateTime zonedDateTime = now.atZone(ZoneId.of("Europe/Berlin"));
        Instant instant = zonedDateTime.toInstant();
        System.out.println("now in local : "+now);
        System.out.println("now in Germany : "+now.atZone(ZoneId.of("Europe/Berlin")));
        System.out.println("zonedate to instant : "+ instant);
        System.out.println("with zone same instant : "+ zonedDateTime.withZoneSameInstant(ZoneId.of("Asia/Shanghai")));

    }
}
