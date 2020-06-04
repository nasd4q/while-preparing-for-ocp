/*
    Auto-closeable resource are implicitely final.
 */

package trywithresources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class RedefiningResource {
    public static void main(String[] args) {
        try (InputStream is = new FileInputStream(".");) {
            //is = new FileInputStream("abc.text"); // Error:(11, 13) java:
                // auto-closeable resource is may not be assigned
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
