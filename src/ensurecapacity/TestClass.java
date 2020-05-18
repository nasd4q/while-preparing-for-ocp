package ensurecapacity;

import java.lang.*;

public class TestClass {

    public static void main(String[] args) {

        StringBuilder str1 = new StringBuilder("tuts point");
        System.out.println("string1 = " + str1);

        // returns the current capacity of the string Builder 1
        System.out.println("Old Capacity = " + str1.capacity());
      /* increases the capacity, as needed, to the specified amount in the 
         given StringBuilder object */

        // returns twice the capacity plus 2
        str1.ensureCapacity(28);
        System.out.println("New Capacity = " + str1.capacity());
        System.out.println("string1 = " + str1);


        StringBuilder str2 = new StringBuilder("compile online");
        System.out.println("string2 = " + str2);

        // returns the current capacity of string Builder 2
        System.out.println("Old Capacity = " + str2.capacity());
      /* returns the old capacity as the capacity ensured is less than the
         old capacity */
        str2.ensureCapacity(29);
        System.out.println("New Capacity = " + str2.capacity());
        System.out.println("string1 = " + str1);

    }
}  