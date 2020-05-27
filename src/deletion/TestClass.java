/*
    Calling delete() on a non existent file simply returns false.
 */

package deletion;

import java.io.File;
import java.util.Arrays;

public class TestClass {
    static File playground = new File("Playground");

    private static void deleteAFile(String name) {
        assert playground.exists() : "Playground file not found !";

        File fileToDelete = new File(playground, name);

        for (File file : playground.listFiles()) {
            System.out.println(file);
        }
        boolean delete = fileToDelete.delete();
        System.out.println("deleted : " + delete);
        Arrays.asList(playground.listFiles()).forEach(System.out::println);
    }

    public static void main(String[] args) {
        deleteAFile("abc");
    }
}
