/*
    Returning int where Long is return type won't compile !
 */

package boxingchallenge;

public class TestClass {
    public static Long compute() {
        //return 2; //Error:(5, 16) java: incompatible types: int cannot be converted to java.lang.Long
        //return 2l;
        // or
        return 2L;
    }

    public static void main(String[] args) {
        System.out.println(compute());
    }
}
