package numberformat;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class TestClass {
    public static void main(String[] args) throws ParseException {
        // NumberFormat.parse(String) throws ParseException which is a checked exception !


        String amt = "$92,807.99";
        NumberFormat cf = NumberFormat.getCurrencyInstance(Locale.US);
        double value = (Double) cf.parse(amt);
        System.out.println(value); // 92807.99

        String amt2 = "$92,807";
        NumberFormat cf2 = NumberFormat.getCurrencyInstance(Locale.US);
        //double value2 = (Double) cf2.parse(amt2); // java.lang.ClassCastException:
        // class java.lang.Long cannot be cast to class java.lang.Double

        double value2 = cf2.parse(amt2).doubleValue();
        System.out.println(value2); // 92807.0

        int value3 = cf2.parse(amt2).intValue();
        System.out.println(value3); // 92807
    }
}
