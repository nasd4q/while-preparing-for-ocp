/*
    It is ok to have a method of same signature as constructor.
 */

package methodvsconstructor;

class Outer {
    public void Outer() {
        System.out.println("executing Outer()");
    }
    protected class Inner    {    }

    public static void main(String[] args) {
        Outer o = new Outer();
        o.Outer(); // executing Outer() !!
    }
}