/*
    Basic test of System.console() readPassword method
 */

package basic;

import java.io.Console;


public class TestClass {
    public static void main(String[] args) {
        Console cons;
        char[] passwd;
        if ((cons = System.console()) != null &&
                (passwd = cons.readPassword("[%s]", "Password:")) != null) {
            System.out.print("The password entered : ");
            for (int i = 0; i < passwd.length; i++) {
                System.out.print(passwd[i]);
            }
            System.out.println("\njust printed");
            System.out.print("The password entered : ");
            for (int i = 0; i < passwd.length; i++) {
                System.out.write(passwd[i]);
            }
            System.out.println("\njust written");
            java.util.Arrays.fill(passwd, ' ');
        }
    }
}
