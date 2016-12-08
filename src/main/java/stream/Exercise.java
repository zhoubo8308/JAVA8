package stream;

import javax.swing.text.html.Option;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.*;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * Created by bzou on 2016/12/1.
 */
public class Exercise {

    public static void main(String ...args)throws Exception{
        String [] words = {"hello","good", "morning","wow","fuckyoujimture"};
      //  System.out.println(getCount_multithreaded(Arrays.asList(words)));
      //  ex2();
       // ex3();

       /* long a = 25214903917L;
        long c = 11L;
        long m = (long)Math.pow(2,48);
      ex5(a,c,m,1L);*/
       // Stream<Double> stream = Stream.generate(Math::random);
      //  System.out.println(isInfinite(stream));

        /*Double [] temp = {1.0,2.0,3.0,4.0,5.0};
        List<Double> doubles = Arrays.asList(temp);
        System.out.println(ex10(doubles.stream()));*/

        //ex12(Stream.of(words));
        ex13(Stream.of(words));
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

    public static void ex5(Long a, Long c, Long m, Long seed){
        Stream<Long> longStream = Stream.iterate(seed,n->(a*n+c)%m);
        System.out.println(longStream.limit(10).collect(Collectors.toList()));
    }

    /**
     * It is an improvement for characterStream method in section 2.3
     * @param s
     * @return
     */
    public static  Stream<Character> ex6(String s){
       return s.chars().mapToObj(n->(char)n);
    }

    public static <T> boolean isInfinite(Stream<T> stream){
        long size = 999999L;
        if(stream.count() > size){
            return true;
        }
        return  false;
    }

    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second){
        return null;

    }

    private static class ZippedIterator<T> implements Iterator<T> {
        private final Iterator<T> itFirst;
        private final Iterator<T> itSecond;
        boolean firstsTurn;

        public ZippedIterator(Iterator<T> itFirst, Iterator<T> itSecond) {
            this.itFirst = itFirst;
            this.itSecond = itSecond;
            firstsTurn = true;
        }

        @Override
        public boolean hasNext() {
            return !firstsTurn || (itFirst.hasNext() && itSecond.hasNext());
        }

        @Override
        public T next() {
            T nextVal = firstsTurn ? itFirst.next() : itSecond.next();
            firstsTurn = !firstsTurn;
            return nextVal;
        }
    }

    //from http://stackoverflow.com/questions/24511052/java8-iterator-to-stream
    private static <T> Stream<T> asStream(Iterator<T> sourceIterator) {
        Iterable<T> iterable = () -> sourceIterator;
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    public static double ex10(Stream<Double> doubleStream){
       /* Optional<Double> optional = doubleStream.reduce((x,y)->x+y);
        //java.lang.IllegalStateException: stream has already been operated upon or closed
        long count = doubleStream.count();
        return optional.get()/count;*/
        Optional<Double> optional = doubleStream.reduce((x,y)->x+y);
        return 0.0;
    }

    public static void ex12(Stream<String> words){
        AtomicInteger[] shortWords = new AtomicInteger[12];
        for(int i=0;i<shortWords.length;i++){
            shortWords[i] = new AtomicInteger();
        }
        words.parallel().forEach(
                s -> { if (s.length() < 12) shortWords[s.length()].getAndIncrement(); }
        );
        System.out.println(Arrays.toString(shortWords));

    }

    public static void ex13(Stream<String> words){
        Map<Integer,Long> wordslenght = words.collect(groupingBy(String::length, counting()));
        wordslenght.forEach((k,v)->{
            System.out.println("key="+k+"; value="+v);
        });
    }




}
