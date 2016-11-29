package stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by bzou on 2016/11/28.
 */
public class ExtractingAndCombining {

    public static void main(String ...args){
        //limit
        Stream<Double> randoms = Stream.generate(Math::random).limit(100);

        //concat
        Stream<Character> combined = Stream.concat(
                characterStream("Hello"), characterStream("World"));

        //peek
        Object[] powers = Stream.iterate(1.0, p -> p * 2)
                .peek(e -> System.out.println("Fetching " + e))
                .limit(20).toArray();
    }

    public static Stream<Character> characterStream(String s) {
        List<Character> result = new ArrayList<>();
        for (char c : s.toCharArray()) result.add(c);
        return result.stream();
    }

}
