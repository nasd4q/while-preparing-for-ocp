/*
    Various experiments with Paths, Path.of, toUri() etc.
 */

package relativeparent;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestClass {
    public static void main(String[] args) {
        final Path current = new File(".").toPath();
        final Path nullPath = null;

        System.out.println(current); //.
        System.out.println(current.getParent()); // null

        //System.out.println(current.resolve(nullPath));// resolve(aNullPath) throws NullPointerException

        System.out.println(current.resolveSibling("src"));//src

        System.out.println(current.relativize(new File("src/moveandcopy").toPath()));//src/moveandcopy

        //System.out.println(current.toAbsolutePath().relativize(Paths.get("src")));//Exception in thread
        // "main" java.lang.IllegalArgumentException: 'other' is different type of Path

        //System.out.println(current.relativize(Paths.get("src").toAbsolutePath()));//Exception in thread
        //"main" java.lang.IllegalArgumentException: 'other' is different type of Path

        System.out.println("HERE");
        System.out.println(current.toAbsolutePath());
        System.out.println(current.toAbsolutePath().relativize(new File("/./src/moveandcopy").toPath()));
        System.out.println(current.toAbsolutePath().resolve("../../allo/./non/"));
        System.out.println(current.toAbsolutePath().resolve("/../../allo/./non/"));
        System.out.println(current.toAbsolutePath().resolve("../../allo/./non/").relativize(Paths.get("/src/moveandcopy")));
        System.out.println(current.toAbsolutePath().relativize(new File("/src/moveandcopy").toPath()));

        System.out.println("THERE");
        System.out.println(Paths.get("/./Users/apple/dev/../allo").relativize(
                Paths.get("/Users/apple/./finder/one.txt")));

        System.out.println("resolveSibling on no parents with null - throws NPE");
        //System.out.println(current.resolveSibling(nullPath));//NullPointerException

        System.out.println("resolveSibling on no parents with empty");
        System.out.println(current.resolveSibling(Path.of("")));// prints out a line with nothing

        System.out.println("URI");
        System.out.println(current.toUri());//file:///Users/apple/dev/learning/java/WhilePreparingForOCP/./
        System.out.println(current.normalize().toUri());//file:///Users/apple/dev/learning/java/WhilePreparingForOCP/


    }
}
