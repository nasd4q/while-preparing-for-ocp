/*
    It is possible to use
        import static tostring.EnumPrinter.*;
    and then using the enums constant with just their name (example : "Canon") and not like this
        EnumPrinter.Canon
 */

package tostring;

import static tostring.EnumPrinter.*;

public class StaticImporterForEnum {
    public static void main(String[] args) {
        System.out.println(HP);
        System.out.println(Toshiba);
    }
}
