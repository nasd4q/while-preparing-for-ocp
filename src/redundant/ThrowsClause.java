/*
    Redundant throws clause compiles (and runs) fine.
 */

package redundant;

import java.io.IOException;

public class ThrowsClause {
    public static void method() throws Exception, IOException {
        throw new IOException();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("allright");
    }
}
