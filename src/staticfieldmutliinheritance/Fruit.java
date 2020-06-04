/*
    Can't inherit a public static field ambiguously from an interface and a super class simultaneously.
    Well in fact can inherit both, but have to disambiguate when using.
 */

package staticfieldmutliinheritance;

interface Eatable {
    int types = 10;
}


class Food implements Eatable {
    public static int types = 20;
}

public class Fruit extends Food implements Eatable {
    public void method(){
        System.out.println(((Eatable) this).types); // will print 10
        System.out.println(super.types); // will print 20
    }

    public static void main(String[] args) {
        //types = 30; // Error:(24, 9) java: reference to types is ambiguous
        //System.out.println(types); // Error:(25, 28) java: reference to types is ambiguous
        new Fruit().method();
    }
}