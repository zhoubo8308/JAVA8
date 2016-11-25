package lambdas;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;

/**
 * Created by bzou on 2016/11/18.
 */
public class FunctionalInterfaceDemo {

    public static void main(String ... args){
        String [] words = {"hello","good", "morning","wow"};
        Arrays.sort(words,(first,sencond)->Integer.compare(first.length(),sencond.length()));
        System.out.println(words[0]);

        //你不能把一个lambda表达式赋值给一个object对象，因为object不是function interface,
        // BiFunction是一个function interface
        BiFunction<String,String,Integer> comp = (first,sencond)->Integer.compare(first.length(),sencond.length());

        //Arrays.sort(words,comp);
        repeatMessages("hello",20);

      //  Runnable runnable = () -> {System.out.print(""); Thread.sleep(1000);};

        Callable<Void> callable = () -> {System.out.print(""); Thread.sleep(1000);return null;};
    }

    public static void repeatMessages(String text, int count){
        Runnable r = ()->{
            for(int i=0;i<count;i++){
                System.out.println(text);
                Thread.yield();
            }
        };
        new Thread(r).start();

    }

}
