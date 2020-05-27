/*
    Testing the fact that passing null as first "File" arg of File constructor makes that constructor
        revert to the constructor which only take one arg - the String representing the path to the file
 */

package creating;

import java.io.File;

public class TestClass {
    public static void main(String[] args) {
        File fil = null;
        File file = new File(fil,"./src");
        File[] files = file.listFiles();
        for (File f : files) {
            System.out.println(f);
        } // ./src/creating
    }
}
