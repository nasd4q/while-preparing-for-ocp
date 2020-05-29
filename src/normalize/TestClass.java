/*
    Paths.get("").equals(Paths.get(".").normalize()) returns true, but not without normalize !
    Paths.get(".").getName(0) returns a path that equals Paths.get(".")
 */

package normalize;

import java.nio.file.Path;
import java.nio.file.Paths;

public class TestClass {
    public static void main(String[] args) {
        final Path path = Paths.get("."); // h1
        int count = 0;
        System.out.println(Paths.get("").equals(path)); // true !
        System.out.println(path);
        for(int i=0; i<path.getNameCount(); ++i) {
            System.out.println("path.getName(i) avec i = " + i + " : " + path.getName(i)); }
    }
}

class TestClass2 {
    public static void main(String ... args) {
        System.out.println(Paths.get("").equals(Paths.get("."))); // false
        System.out.println(Paths.get("").equals(Paths.get(".").normalize())); // true
        System.out.println(Paths.get(".").getName(0)); // .
        System.out.println(Paths.get(".").getName(0).equals(Paths.get("."))); // true
        System.out.println(Paths.get("").getName(0)); // [outputs an empty line]
        System.out.println(Paths.get("").getNameCount()); // 1
        //System.out.println(Paths.get("").getName(1)); // throws java.lang.IllegalArgumentException
    }
}