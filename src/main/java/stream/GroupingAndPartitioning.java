package stream;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * Created by bzou on 2016/11/29.
 */
public class GroupingAndPartitioning {
    public static void main(String ...args){
        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
        //groupingBy
       /* Map<String, List<Locale>> countryToLocales = locales.collect(
                Collectors.groupingBy(Locale::getCountry));
        List<Locale> swissLocales = countryToLocales.get("CH");
        System.out.println(swissLocales);*/


        //partitioningBy
       /* Map<Boolean, List<Locale>> englishAndOtherLocales = locales.collect(
                Collectors.partitioningBy(l -> l.getLanguage().equals("en")));
        List<Locale> englishLocales = englishAndOtherLocales.get(true);
        System.out.println(englishLocales);*/

        Map<String, Set<Locale>> countryToLocales = locales.collect(
                groupingBy(Locale::getCountry, toSet()));//import static java.util.stream.Collectors.*
        Set<Locale> swissLocales = countryToLocales.get("CH");
        System.out.println(swissLocales);
    }

}
