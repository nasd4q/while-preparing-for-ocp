/*
    Shows it is OK to use an anonymous class as field initializing value.

    By the way anonymous class can also be used as method call arguments... and probably anywhere you might need them
 */

package anonymousclassasfield;

interface I {
    int giveAnInt();
}

public class TestClass {
    I myI = new I() {
        @Override
        public int giveAnInt() {
            return 37;
        }
    };

    public static void main(String... args) {
        System.out.println(new TestClass().myI.giveAnInt()); // 37
    }
}
