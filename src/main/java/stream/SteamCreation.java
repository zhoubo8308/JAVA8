package stream;

import java.util.DoubleSummaryStatistics;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by bzou on 2016/11/23.
 */
public class SteamCreation {
    public static void main(String ... args){
        Stream<String> echos = Stream.generate(()->"echo");
        System.out.println(echos.findFirst().get());
        Stream<Double> randoms = Stream.generate(Math::random);
        System.out.println(randoms.findFirst().get());


        Optional<Double> result = inverse(4.0).flatMap(SteamCreation::squareRoot);
        System.out.println(result.get());

        Optional<Double> result2 = Optional.of(-4.0).flatMap(SteamCreation::inverse).flatMap(SteamCreation::squareRoot);
        System.out.println(result2.isPresent());
    }

    public static Optional<Double> inverse(Double x) {
        return x == 0 ? Optional.empty() : Optional.of(1 / x);
    }

    public static Optional<Double> squareRoot(Double x) {
        return x < 0 ? Optional.empty() : Optional.of(Math.sqrt(x));
    }


}
