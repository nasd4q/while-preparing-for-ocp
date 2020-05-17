// A class can be defined to implement 2 interfaces with competing default method but must then disambiguate
// - ie override both methods (at once)

package interfaces;

interface House2 {
    public default String getAddress() {
        return "101 Main Str";
    }
}

interface Bungalow2 {
    public default String getAddress() {
        return "101 Smart Str";
    }
}

class MyHouse2 implements Bungalow2, House2 {
    @Override
    public String getAddress() {
        return House2.super.getAddress();
    }
}

public class TestClass2 {
    public static void main(String[] args) {
        House2 ci = new MyHouse2();  //1
        System.out.println(ci.getAddress()); // prints 101 Main Str
        System.out.println(((Bungalow2)ci).getAddress()); // prints 101 Main Str
        System.out.println(ci.getAddress()); // prints 101 Main Str
    }
}