/*
    List.of(Collection c) ? Works but return a List<Collection> (not a List<Elements> ...)

    List.of, List.copyOf, Collections.unmodifiableList :
       * returned objects will throw java.lang.UnsupportedOperationException if modification is attempted
       * only unmodifiableList supprots null : List.of and List.copyOf don't
       * While List.of and List.copyOf will be new independent lists, unmodifiableList is only an
             "unmodifiable" view, and will change with the list passed as argument on construction
 */

package ofcopyof;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TestClass {
    public static void main(String[] args) {
        final Collection al = new ArrayList<>(List.of("hello", "there", "one"));
        System.out.println(al); // [hello, there, one]

        final List<Collection> listof = List.of(al);
        System.out.println(listof); // [[hello, there, one]]

        final List listcopyof = List.copyOf(al);
        System.out.println(listcopyof);//[hello, there, one]

        //final List unmodifiableList = Collections.unmodifiableList(al); // NOT COMPILING
        // Error:(24, 36) java: method unmodifiableList in class java.util.Collections
        // cannot be applied to given types;
        //  required: java.util.List<? extends T>
        //  found:    java.util.Collection
        //  reason: cannot infer type-variable(s) T
        //    (argument mismatch; java.util.Collection cannot be converted to java.util.List<? extends T>)

        final List unmodifiableList = Collections.unmodifiableList((List<?>)al);
        System.out.println(unmodifiableList);//[hello, there, one]

        //listof.remove("one");// throws java.lang.UnsupportedOperationException

        //listcopyof.remove("one");// throws java.lang.UnsupportedOperationException

        //unmodifiableList.remove("one");// throws java.lang.UnsupportedOperationException

        al.remove("one");

        System.out.println(al); // [hello, there]
        System.out.println(listof); // [[hello, there]]
        System.out.println(listcopyof);//[hello, there, one]
        System.out.println(unmodifiableList);//[hello, there]
    }
}

