package arraylist;

import java.util.ArrayList;
import java.util.List;

public class ConstructorTest {
    public static void  main(String... args) {
        List<String> strings = new ArrayList<String>();
        strings.add("tweet");
        List<Object> objects = new ArrayList<Object>(strings);
        //  compiles because the constructor accepts a java.util.Collection<? extends E>
        //      (where E here is Object)
        objects.forEach(System.out::println);
    }
}
