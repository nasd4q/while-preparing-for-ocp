package _enabled1.subpack;

public class CopyClass {
    public static int method() {
        int i = 0;
        try {
        } catch (Error e) {
            System.out.println("hello");
        }
        System.out.println("i : " + i);
        return i;
    }

    public static void main(String[] args) {
        assert method()==10 : "i is not equal to 10...";
    }
}
