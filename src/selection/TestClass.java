/*
    studies method selection among various overloaders...
 */

package selection;

public class TestClass{
    void probe(int... x) { System.out.println("In ..."); }  //1

    void probe(Integer x) { System.out.println("In Integer"); } //2

    void probe(long x) { System.out.println("In long"); } //3

    void probe(Long x) { System.out.println("In LONG"); } //4

    void probe(Object o) { System.out.println("In Object"); } //4


    public static void main(String[] args){
        Integer a = 4; new TestClass().probe(a);
            // Order of preference : probe(Integer), then probe(Object), then probe(long) then probe(int...)
            // DOES NOT COMPILE if only probe(Long) available :
            // "Error:(13, 46) java: incompatible types: java.lang.Integer cannot be converted to java.lang.Long

        //int b = 4; new TestClass().probe(b);
            // Order of preference : probe(long), then probe(Integer), then probe(Object), then probe(int...)
            // DOES NOT COMPILE if only probe(Long) available :
            // Error:(18, 42) java: incompatible types: int cannot be converted to java.lang.Long

        //long l = 46; new TestClass().probe(l);
            // Order of preference : probe(long), then probe(Long), then probe(Object)
            // DOES NOT COMPILE otherwise.
    }
}

