/*
            non-static in super .... static in sub by same signature

    There can't be no static overridder of non static method !
    - ie - if a method (abstract or not) is non static in a superclass or in an interface (default method or not)
        then it can't be "overridden" by a static method.
        Even when declaring the static overridding method as private



            static in super .... non static in sub by same signature

    In case of class extending class, "overriding" a static method with non static one doesn't compile neither.
        (Hiding the super class static method by redefining the static method works though.)

    In case of class implementing interface, you can "pseudo - override" the static method of the interface
             with non static one.
        (And hiding the super interface static method with another static method still works.)

 */

package staticvsnonstatic;

abstract class A {
    public int getAnInt() { return 37;}
    //abstract public int getAnInt();
    static void print() {
        System.out.println("Class A is printing.");
    }
    //private int getAnInt() { return 37;} // ONLY THIS ALLOWS EXTENDER B TO DEFINE static public int getAnInt()
}

interface AI {
    default int getAnInt() { return 37;}
    //int getAnInt();
    static void print() { // IMPLICITELY PUBLIC ! ... UNLESS DECLARED PRIVATE !
        System.out.println("Interface AI is printing.");
    }
}


//class B extends A{
class B implements AI{
    //static int getAnInt() { return 37;}  // NEVER COMPILES : "cannot override / implement getAnInt()
                                            //                       - overriding method is static"
    //void print(){ System.out.println("An instance of Class B is printing.");}
    //case extends A - DOES NOT COMPILE NEITHER : "cannot override print() - overridden method is static"
        //case implements AI - DOES COMPILE AND WORK FINE

    static void print(){ System.out.println("Class B is printing.");}
        //case extends A - works fine - which method is called depends on caller's declared type
        //case implements AI - works fine too, AI's print is only called with "AI.print()"
}




public class TestClass {
    public static void main(String...args) { // For the case B implements AI
        B b = new B();
        b.print(); // ouptputs "An instance of Class B is printing"
                    // In case of class B extends A{} - outputs "Class A is printing."
        //B.print(); //DOES NOT COMPILE - print() cannot be referenced from a static context
                    // In case of class B extends A{} - outputs "Class A is printing."
        //((AI)b).print(); //DOES NOT COMPILE - illegal static interface method call
        //AI ai = b;
        //ai.print(); // STILL NOT COMPILING - illegal static interface method call
        AI.print(); // outputs "Interface AI is printing."


        // In case B extends A and both defines static void print() method,
        //((A)b).print(); // in this "hiddening case", this would still call super - ie A - 's static print

    }
}
