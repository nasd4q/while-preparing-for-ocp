/*
        Arrays.BinarySearch and comparator not "consistent with equals"

    It appears like it is possible to "fool"
        Arrays.<T>binarySearch(@NotNull T[] a,T key,@Nullable java.util.Comparator<? super T> c)
    - ie - if the comparator returns 0 for not-equals elements then the binarySearch will still
        behave as if having found the key in the array ...

        example :
        Arrays.binarySearch(new String[]{"d", "bbb", "aaaa"}, "ffff", Comparator.comparing(String::length))
            returns 2




    Caution : Arrays.binarySearch(new Object[]{"d", "bbb", "aaaa"},
                        "ffff", Comparator.comparing(x-> ((String) x).length())) // COMPILES FINE

                    - BUT -

             Arrays.binarySearch(new Object[]{"d", "bbb", "aaaa"},
                        "ffff", Comparator.comparing((String x)-> x.length())) // WONT COMPILE
        Error:(85, 23) java: no suitable method found for binarySearch(java.lang.Object[],
        java.lang.String,java.util.Comparator<java.lang.String>)
    method java.util.Arrays.<T>binarySearch(T[],T,java.util.Comparator<? super T>) is not applicable
      (inference variable T has incompatible bounds
        lower bounds: T,java.lang.String,java.lang.Object
        lower bounds: java.lang.String,java.lang.Object)
    method java.util.Arrays.<T>binarySearch(T[],int,int,T,java.util.Comparator<? super T>) is not applicable
      (cannot infer type-variable(s) T
        (actual and formal argument lists differ in length))





    And calling this method with null value for the 3rd argument (c) will simply cause the method
        to use T's "natural ordering"

    Caution when implementing or extending generic class :
        if using untyped version then override replacing type argument with "Object"
        if using typed version then override replacing type argument with the right type !

    Calling "System.out.println(a1);" where a1 is of type String[]
                      will simply print something like [Ljava.lang.String;@548c4f57 !!
 */

package binarysearch;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class ByLengthAndUntyped implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        return ((String) o1).length() - ((String) o2).length();
    }
}

class ByLengthAndTyped implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        return o1.length() - o2.length();
    }
}

class ByLengthAndGeneric<T> implements Comparator<T> {
    @Override
    public int compare(T o1, T o2) {
        return o1.toString().length() - o2.toString().length();
    }
}

public class FoolingTheAlgorithm {
    static String[] getArray() {
        return new String[]{"d", "bbb", "aaaa"};
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void main(String[] args) {

        String[] a1 = getArray();
        System.out.println(List.of(a1).stream().collect(Collectors.joining(",", "[", "]")));
        Arrays.sort(a1);
        System.out.println(List.of(a1).stream().collect(Collectors.joining(",", "[", "]")));
        System.out.println(Arrays.binarySearch(a1, "bbb")); //1
        System.out.println(Arrays.binarySearch(a1, "bb")); //-2
        System.out.println(Arrays.binarySearch(a1, "e")); //-4

        a1 = getArray();
        System.out.println(List.of(a1).stream().collect(Collectors.joining(",", "[", "]")));
        Arrays.sort(a1, new ByLengthAndGeneric<>());
        System.out.println(List.of(a1).stream().collect(Collectors.joining(",", "[", "]")));
        System.out.println(Arrays.binarySearch(a1, "bbb", new ByLengthAndTyped())); //1
        System.out.println(Arrays.binarySearch(a1, "bb", new ByLengthAndGeneric<String>())); //-2
        System.out.println(Arrays.binarySearch(a1, "e", new ByLengthAndUntyped())); //0
        int ffff = Arrays.<String>binarySearch(a1, "ffff", new ByLengthAndUntyped());
        System.out.println(ffff); //2
        System.out.println("ffff".equals(a1[2])); // false
        System.out.println(new ByLengthAndGeneric().compare("ffff", "aaaa"));//0

        System.out.println(
                Arrays.binarySearch(
                        new String[]{"d", "bbb", "aaaa"},
                        "ffff",
                        Comparator.comparing(String::length)
                )
        );//2
    }
}
