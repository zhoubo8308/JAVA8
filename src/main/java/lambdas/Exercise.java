package lambdas;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

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
        String [] files = file.list((f,name)->{
            if(name.indexOf(extention)!=-1)
                return true;
            else
                return false;
        });
        return Arrays.asList(files);
    }

    public static void main(String ...args){
        Exercise exercise = new Exercise();
        for(File f: exercise.getSubDictories("e:/code")){
            System.out.println("all sub directories: " + f.getName());
        }

        for(String name: exercise.getExtentions("e:/code","zip")){
            System.out.println("zip: "+name);
        }


    }

}
