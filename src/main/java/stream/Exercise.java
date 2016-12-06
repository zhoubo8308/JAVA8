package stream;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by bzou on 2016/12/1.
 */
public class Exercise {

    public static void main(String ...args)throws Exception{
        String [] words = {"hello","good", "morning","wow"};
      //  System.out.println(getCount_multithreaded(Arrays.asList(words)));
      //  ex2();
        ex3();
    }

    private static int getCount_multithreaded(List<String> words) throws Exception {
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("cores in my machined = " + cores);
        ExecutorService pool = Executors.newFixedThreadPool(cores);

        int wordsPerSegment = words.size() / cores;

        Set<Future<Integer>> results = new HashSet<>();

        for (int i = 0; i < cores; i++) {
            List<String> sublist = words.subList(wordsPerSegment * i, wordsPerSegment * (i + 1));

            Future<Integer> future = pool.submit(() -> {
                int count = 0;
                for (String w : sublist) {
                    if (w.length() > 4) count++;
                }
                return count;
            });
            results.add(future);
        }

        int result = 0;
        for (Future<Integer> calcResult : results) {
            result += calcResult.get();
        }
        pool.shutdown();
        return result;
    }

    public static void ex2()throws  Exception{
        String contents = new String(Files.readAllBytes(Paths.get("d:/debug.log")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
        words.stream().filter(w->{
           // System.out.println(w + " ");
            return w.length()>12;
        }).limit(5).forEach(w->System.out.println("LONG WORD FOUND: " + w));

        System.out.println("All Done");
    }

    /**
     * parallel was slower?
     * @throws Exception
     */
    public static void ex3()throws  Exception{
        String contents = new String(Files.readAllBytes(Paths.get("d:/movie.srt")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
        Instant start = Instant.now();
        System.out.println(words.stream().filter(w->w.length()>12).count());
        Instant end = Instant.now();
        System.out.println("stream cost :" + Duration.between(start,end).toMillis());

        Instant start2 = Instant.now();
        System.out.println(words.parallelStream().filter(w->w.length()>12).count());
        Instant end2 = Instant.now();
        System.out.println("parallelStream cost :" +Duration.between(start2,end2).toMillis());
    }

    public static void ex4(){
        int values[] = {1,4,9,16};
        //the Type is int[]
        Stream<int []> stream= Stream.of(values);
        IntStream intStream = IntStream.of(values);
        IntStream intStream2 = Arrays.stream(values);

    }


}
