package lambdas;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by bzou on 2016/11/29.
 */
public class Exercise {
    public List<File> getSubDictories(String path){
        File file = new File(path);
        File [] files = file.listFiles(f->f.isDirectory());
        return Arrays.asList(files);
    }

    /**
     * extention was captured
     * @param path
     * @param extention
     * @return
     */
    public List<String> getExtentions(String path,String extention){
        File file = new File(path);
        String [] files = file.list((f, name) -> {
            if (name.indexOf(extention) != -1)
                return true;
            else
                return false;
        });
        return Arrays.asList(files);
    }

    /**
     * sort files, directory first, each group sort by name
     * @param files
     */
    public void sortFiles(File [] files){
        Arrays.sort(files,(f1,f2)->{
           int v =  Boolean.compare(f1.isFile(),f2.isFile());
           if(v != 0)
               return v;
           return f1.getName().compareTo(f2.getName());
        });
    }

    /**
     * sort files, directory first, each group sort by name
     * This is more elegant
     * @param files
     */
    public void sortFiles2(File [] files){
        Arrays.sort(files, Comparator.comparing(File::isFile).thenComparing(File::getName));
    }


    /**
     * 1. define a functional interface RunnableEx which have a run method throws exception
     * 2. define a uncheck method,take the RunnableEx as parameter,return a Runnable object
     * @param r
     * @return
     */
    public static Runnable uncheck(RunnableEx r){
        return ()->{
            try{
                r.run();
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        };
    }

    /**
     * 使用它会有编译错误
     * @param r
     * @return
     */
    public static Runnable uncheck2(Callable<Void> r){
        return ()->{
            try{
                r.call();
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        };
    }

    public static Runnable andthen(Runnable r1,Runnable r2){
        return ()->{
            r1.run();
            r2.run();
        };
    }



    public static void main(String ...args){
        Exercise exercise = new Exercise();
        /*for(File f: exercise.getSubDictories("e:/code")){
            System.out.println("all sub directories: " + f.getName());
        }

        for(String name: exercise.getExtentions("e:/code","zip")){
            System.out.println("zip: "+name);
        }*/

        /*File [] before = new File("e:/code").listFiles();
        exercise.sortFiles(before);
        for(File f: before){
            System.out.println(f.getName());
        }*/


        /*new Thread(uncheck(()->{
            System.out.print("zzzz");
            Thread.sleep(1000);
        })).start();*/

       /* new Thread(andthen(
                ()->System.out.println("hello"),
                ()->System.out.println("world")
        )).start();*/

       /* String[] names = { "Peter", "Paul", "Mary" };
        List<Runnable> runners = new ArrayList<>();
        for (String name : names)
            runners.add(() -> System.out.println(name));
        //can't compile, i is not effectively final
        for(int i=0;i<names.length;i++){
            runners.add(() -> System.out.println(names[i]));
        }

        for(Runnable r: runners){
            new Thread(r).start();
        }*/

       MyCollection<String> myCollection = new MyCollection();
        myCollection.add("hello");
        myCollection.add("hi");
        myCollection.add("big");
        myCollection.forEachIf(System.out::println,x->x.length()>2);

    }

    @FunctionalInterface
    public interface RunnableEx{
        void run() throws Exception;
    }


    public interface Colletion2<T> extends Collection<T>{
        default void forEachIf(Consumer<T> action, Predicate<T> filter){
            forEach(x->{
                if(filter.test(x)){
                    action.accept(x);
                }
            });
        }
    }

    public static class MyCollection<T> extends ArrayList<T> implements Colletion2<T>{

    }
}



