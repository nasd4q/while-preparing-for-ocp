package basic;

import java.util.Locale;
import java.util.ResourceBundle;

public class TestClass {
    public static void main(String[] args) {
        Locale us = new Locale("en", "US");
        Locale france = new Locale("fr", "FR");
        printProperties(us);
        System.out.println();
        printProperties(france);
    }

    public static void printProperties(Locale locale) {
        ResourceBundle rb = ResourceBundle.getBundle("i18n.Zoo", locale); // i18n package mentioned
        System.out.println(rb.getString("hello"));
        System.out.println(rb.getString("open"));
    }
}
