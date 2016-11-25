package lambdas.defaultMethod;

/**
 * Created by bzou on 2016/11/21.
 */
public class Student implements Named,Person {
    @Override
    public long getId() {
        return 0;
    }

    @Override
    public String getName() {
        //Person.super?
        return Person.super.getName();
    }
}
