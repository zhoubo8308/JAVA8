package lambdas.defaultMethod;

/**
 * Created by bzou on 2016/11/21.
 */
public interface Person {
    long getId();
    default String getName() { return "John Q. Public"; }

}
