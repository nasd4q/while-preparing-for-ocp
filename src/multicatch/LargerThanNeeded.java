/*
    --> Using multi catch is only possible if both sides of the | sign are not "related by subclassing"
    --> And using this still does not allow to simply catch any random exception if that exception
        is never thrown by the preceding try block in the first place
            (nor in initialization nor while closing for try-w-r)

    BONUS
         --> FileInputStream constructors throws "only" FileNotFoundException
                    ie no need to catch whole class of IOException... (works also though)
             (these constructors can also throw the RuntimeException SecurityException,
                when the "SecurityManager" detects a "security violation")
         --> calling new FileInputStream(String) or new FileInputStream(File) with an argument
                denoting a directory rather than a regular file will throw FileNotFoundException
          (like if arg denotes non existent file or file that can't be opened for reading for another reason...)

 */

package multicatch;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LargerThanNeeded {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("."); // throwing
            // java.io.FileNotFoundException: . (Is a directory)
        }
        /*
        // Error:(18, 9) java: exception java.nio.file.NoSuchFileException
        // is never thrown in body of corresponding try statement
        catch (FileNotFoundException|NoSuchFileException e) {
            e.printStackTrace();
        }
        */
        /*
        //Error:(25, 28) java: Alternatives in a multi-catch statement cannot be related by subclassing
        //Alternative java.nio.file.NoSuchFileException is a subclass of alternative java.io.IOException
        catch (IOException|NoSuchFileException e) {
            e.printStackTrace();
        }
        */
        catch (IOException|RuntimeException e) {   // COMPILES FINE
            e.printStackTrace();
        }
    }
}
