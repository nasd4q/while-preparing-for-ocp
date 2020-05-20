/*
    Thank god this won't compile
 */

package plusplus;

public class Doubling {
    public static void main(String...args){
        int i = 0;
        //System.out.println(--i++);// Error:(6, 32) java: unexpected type, required: variable, found: value
        //System.out.println(--(i++));// Error:(6, 32) java: unexpected type, required: variable, found: value
    }
}
