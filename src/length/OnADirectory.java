/*
    Learned that : calling length() on a File which isDirectory() returns a regular long (ie - throws
    no exception !) that seems to grow with the number of files contained in it, and independently of
    the sizes of the contained files themselves
 */

package length;

import java.io.File;

public class OnADirectory {
    private static final File playground = new File("Playground");

    public static void main(String[] args) {
        File folder = new File(playground, "folder");
        System.out.println(folder.isFile());
        System.out.println(folder.isDirectory());
        System.out.println(folder.length());

        File emptyfold = new File(playground, "emptyfold");

        System.out.println(emptyfold.isFile());
        System.out.println(emptyfold.isDirectory());
        System.out.println(emptyfold.length());

        File ggg = new File(playground, "folder/ggg");

        System.out.println(ggg.isFile());
        System.out.println(ggg.isDirectory());
        System.out.println(ggg.length());

        File hhh = new File(playground, "folder/hhh");

        System.out.println(hhh.isFile());
        System.out.println(hhh.isDirectory());
        System.out.println(hhh.length());
    }
}
