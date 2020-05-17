package stringbuilder;

public class DemoDelete {
    public static void main(String... args) {
        StringBuilder sb1 = new StringBuilder(); // 3-4 constructors ; no params, int capacity,
                                                 // NotNull CharSequence (and NotNull String)
        System.out.println(sb1.length());

        CharSequence cs = "New String 1";
        sb1.append(cs,4, 10); // cuts the charsequence and append only the section

        System.out.println(sb1); //prints out : String

        sb1.delete(5,5); //does nothing
        // sb1.delete(sb1.length()+1, sb1.length()+1); // would throw java.lang.StringIndexOutOfBoundsException though

        sb1.delete(0,3);
        System.out.println(sb1); //prints out ing

        sb1.delete(1,sb1.length() + 30); // No exception thrown

        System.out.println(sb1); // prints out i
        System.out.println(sb1.length()); // 1
    }
}
