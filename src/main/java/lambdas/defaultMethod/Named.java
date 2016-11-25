package lambdas.defaultMethod;

/**
 * Created by bzou on 2016/11/21.
 */
public interface Named {
    default  String getName(){
        return "bob zhou";
    }

    //编译器还是会要student重写getName来消除模糊性。
    //String getName();
}
