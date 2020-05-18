package implementingwithoutdeclaring;

interface I1{
    int giveAnInt();
}

class C1{
    public int giveAnInt() {
        return 37;
    }
}

public class TestClass {
    public static void main(String... args) {
        Object o = new C1();
        System.out.println(((I1) o).giveAnInt()); // throws a java.lang.ClassCastException
    }
}
