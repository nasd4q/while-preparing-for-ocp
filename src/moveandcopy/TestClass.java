/*

Given:          Path p1 = Paths.get("c:\\temp\\test1.txt");
                Path p2  = Paths.get("c:\\temp\\test2.txt");
Which of the following code fragments moves the file test1.txt to test2.txt, even if test2.txt exists?
 */

package moveandcopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


public class TestClass {
    static Path p1 = Paths.get("playground/test1.txt");
    static Path p2 = Paths.get("playground/","test2.txt");

    public static void main(String[] args) throws IOException {
        //Files.copy(p1,p2, StandardCopyOption.ATOMIC_MOVE);// Exception in thread "main" java.lang.UnsupportedOperationException: Unsupported copy option
        Files.copy(p1,p2, StandardCopyOption.REPLACE_EXISTING);
        Files.delete(p1);
    }
}
