/*
        javac -d out src/strangeenum/Level.java
        java -cp out strangeenum.Level
 */

package strangeenum;

public enum Level {
    HIGH{
        @Override
        public String asLowerCase() {
            return HIGH.toString().toLowerCase();
        }
    },
    MEDIUM{
        @Override
        public String asLowerCase() {
            return MEDIUM.toString().toLowerCase();
        }
    },
    LOW{
        @Override
        public String asLowerCase() {
            return LOW.toString().toLowerCase();
        }
    };

    public abstract String asLowerCase();

    public static void main(String ... args) {
        System.out.println(LOW.asLowerCase()); // low
    }
}
