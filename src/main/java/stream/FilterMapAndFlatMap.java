package stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by bzou on 2016/11/28.
 */
public class FilterMapAndFlatMap {
    public static void main(String ... args){
        List<String> words = new ArrayList<>();
        words.add("hello");
        words.add("word");
        words.add("bitch");
        words.add("nicolas welson");
        Stream<String> stream = words.stream();
        //filter
        Stream<String> longwords = stream.filter(s->s.length()>12);
        //it should have a terminal operation, or else nothing was printed.
        longwords.peek(System.out::println).limit(20).toArray();

        //map
        Stream<String> upcasewords = words.stream().map(String::toUpperCase);
        upcasewords.peek(System.out::println).limit(20).toArray();

        Stream<Character> firstChars = words.stream().map(s->s.charAt(0));
        firstChars.peek(System.out::println).limit(20).toArray();

        Stream<Stream<Character>> result = words.stream().map(s->characterStream(s));
        System.out.print(result.toArray());

        //flatMap
        Stream<Character> letters = words.stream().flatMap(w -> characterStream(w));

    }

    //change a String to a Character Stream
    public static Stream<Character> characterStream(String s) {
        List<Character> result = new ArrayList<>();
        for (char c : s.toCharArray()) result.add(c);
        return result.stream();
    }
}
