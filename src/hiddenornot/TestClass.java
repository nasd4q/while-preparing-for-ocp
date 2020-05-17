/*
    Inheritance and hiding of static fields
 */

package hiddenornot;

interface Movable {
    int location = 0; // Comment this line and you need cast - System.out.println(m.location); not compiling
    void move(int by);
    public void moveBack(int by);
}

class Donkey implements Movable {
    int location = 200;

    public void move(int by) {
        location = location + by;
    }

    public void moveBack(int by) {
        location = location - by;
    }
}

public class TestClass {
    public static void main(String[] args) {
        Movable m = new Donkey();
        m.move(10);
        m.moveBack(20);
        System.out.println(m.location); // 0
        System.out.println(((Donkey) m).location); // 190
    }
}