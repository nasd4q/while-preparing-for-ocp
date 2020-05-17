// A subinterface can override a default method
// A class can redundantly declare to implement an interface

package interfaces;

interface House {
    public default String getAddress() {
        return "101 Main Str";
    }
}

interface Bungalow extends House {
    @Override
    public default String getAddress() {
        return "101 Smart Str";
    }
}

class MyHouse implements House, Bungalow {
}

public class TestClass {
    public static void main(String[] args) {
        House ci = new MyHouse();  //1
        System.out.println(ci.getAddress()); // prints 101 Smart Str
        System.out.println(((Bungalow)ci).getAddress()); // prints 101 Smart Str
    }
}