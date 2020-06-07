/*
    1st - catching Throwable, Error, Exception or RE or any specific RE such as IndexOutOfBoundsException
            even after empty try block compiles fine

          (won't compile for any specific checked exception such as IOException though)
          "src/_enabled1/TestClass.java:13: error:
          exception IOException is never thrown in body of corresponding try statement"

    2nd -   java -ea -cp out _enabled1/TestClass
            java -ea:_enabled1.TestClass -cp out _enabled1/TestClass
            java -ea:_enabled1... -cp out _enabled1/TestClass
                all will enable assertions for TestClass

            java -ea:... -cp out _enabled1/TestClass
                won't enable assertions for TestClass though !

            although -ea:_enabled1... also enables assertions for subpackages such as _enabled1.subpack
 */

package _enabled1;

import java.io.IOException;

public class TestClass {
    public static int method() {
        int i = 0;
        try {
        } catch (Exception e) {
            System.out.println("hello");
        }
        System.out.println("i : " + i);
        return i;
    }

    public static void main(String[] args) {
        assert method()==10 : "i is not equal to 10...";
    }
}
