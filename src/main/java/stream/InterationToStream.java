package stream;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Created by bzou on 2016/11/23.
 */
public class InterationToStream {
    public static void main(String ...args)throws Exception{
        String contents = new String(Files.readAllBytes(Paths.get("d:/debug.log")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
        long count = words.stream().filter(w->w.length()>12).count();
        System.out.println("words count = " + count);
        System.out.println("parallelized count = " + words.parallelStream().filter(w->w.length()>12).count());
    }
}
