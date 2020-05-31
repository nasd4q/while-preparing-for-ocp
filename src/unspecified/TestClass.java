package unspecified;

import java.util.ArrayList;

public class TestClass {
    public static void main(String[] args) {
        var Data = new ArrayList<>(); // COMPILES FINE !
        System.out.println(Data.getClass());
        Data.add("Hello");
        final Object o = Data.get(0);
    }

}
