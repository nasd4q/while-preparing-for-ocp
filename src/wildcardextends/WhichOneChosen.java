package wildcardextends;


import java.util.ArrayList;
import java.util.List;


public class WhichOneChosen {
    <T> T method1(List<? extends T> list) {
        return list.get(0);
    }

    public static void main(String[] args) {
        List rawL = new ArrayList<>(List.of("Hello", "There"));
        List<? extends CharSequence> wildCardL = new ArrayList<>(List.of("Hello", "There"));
        List<String> typedL = new ArrayList<>(List.of("Hello", "There"));

        final WhichOneChosen inst = new WhichOneChosen();

        final Object o = inst.method1(rawL);
        final CharSequence charSequence = inst.method1(wildCardL);
        final String s = inst.method1(typedL);

        //final String s2 = inst.method1(rawL);//Error:(26, 39) java: incompatible types:
        // java.lang.Object cannot be converted to java.lang.String

        //final String s2 = inst.method1(wildCardL);//Error:(27, 39) java: incompatible types:
        // inference variable T has incompatible bounds
        //lower bounds: java.lang.String,java.lang.Object
        //lower bounds: capture#1 of ? extends java.lang.CharSequence

        final String o2 = (String) inst.method1(rawL);// COMPILES AND RUNS FINE
        final String o3 = (String) inst.method1(wildCardL);// COMPILES AND RUNS FINE

        System.out.println(o2 + ", " + o3);// Hello, Hello

    }

}

