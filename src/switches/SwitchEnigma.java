package switches; // Careful package can't be named switch - reserved keyword

public class SwitchEnigma { // also shows that use of a final char var in case label is possible
    public static void main(String... args) {
        char i;
        final char j = 45; //without final, "case j" won't compile. equals '-'
                           //j must also denote a unique case - no two cases with same value or won't compile
        LOOP:
        for (i = 0; i < 5; i++) {
            switch (i++) {
                case '0':
                    System.out.println("A");
                case 1:
                    System.out.println("B");
                    break LOOP;
                case 2:
                    System.out.println("C");
                    break;
                case 3:
                    System.out.println("D");
                    break;
                case 4:
                    System.out.println(i == 5);
                case 'E':
                    System.out.println("F");
                case j:
                    System.out.println("Case " + j);
            }
        } // Will print C then true then F then Case -
    }
}

class IsEmptySwitchValid { // Empty switch compiles
    public static void main(String args[]) {
        int i = 0; // must be initialized for "switch (i)" to compile though
        switch (i) {
        }

        //switch(i) //Uncommenting this line will cause "Error:(39, 18) java: '{' expected" though !
    }
}

class SwitchAndAutoboxing { // switch works with Autoboxings type
    public static void main(String... args) {
        int i = 45;
        Integer integer = Integer.valueOf(46);
        switch (integer) {
            default:
            case 45:
                System.out.println("45");
                break;
            case 46:
                System.out.println("46");
                break;
        }
    }
}

