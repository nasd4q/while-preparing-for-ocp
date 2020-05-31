/*
    From enthuware :
    public static void copy(String fileName1, String fileName2) throws Exception {
        try (InputStream is = new FileInputStream(fileName1);
             OutputStream os = new FileOutputStream(fileName2);) {
            byte[] buffer = new byte[1024];
            int bytesRead = 0;
            while ((bytesRead = is.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
                System.out.println("Read and written bytes " + bytesRead);
            }
        }
    }
    Given the following method:
     What will happen fileName1 contains only 100 bytes and fileName2 contains 200 bytes?

     ----> The answer is that this code correctly overwrites the file at fileName2
                so that fileName2 ends up with a size of 100 bytes
                and that regardless of the preexistence of a file at fileName2
     ----> It also works fine when copy utilizes any size for the buffer instead of 1024
     ----> Caution that if os.write is called with a negative value for the len (3rd param),
                java.lang.IndexOutOfBoundsException is thrown
 */
package copyingwithstreams;

import java.io.*;
import java.util.Random;

public class TestClass {

    public static void copy(String fileName1, String fileName2) throws Exception {
        try (InputStream is = new FileInputStream(fileName1);
             OutputStream os = new FileOutputStream(fileName2)) {
            byte[] buffer = new byte[12];
            int bytesRead = 0;
            while ((bytesRead = is.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
                System.out.println("Read and written bytes " + bytesRead);
            }
        }
    }

    public static void createAXBytesFile(String filename, int numberOfBytes) throws IOException {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filename))) {
            Random r = new Random();
            byte[] bs = new byte[numberOfBytes];
            r.nextBytes(bs);
            bos.write(bs);
        }
    }

    public static long getNumberOfBytes(String filename) {
        return new File(filename).length();
    }

      /*       sample files creation
    public static void main(String[] args) throws IOException {
        String sourceFile = "playground/sourcefileone.txt";
        String targetFile = "playground/targetfile.txt";
        System.out.println(getNumberOfBytes(sourceFile)); // 0 when no sourcefileone.txt file exists.
        System.out.println(getNumberOfBytes(targetFile)); // 0 when no targetfile.txt file exists.

        //createAXBytesFile(sourceFile,100);
        //createAXBytesFile(targetFile, 200);

        System.out.println(getNumberOfBytes(sourceFile)); // 100
        System.out.println(getNumberOfBytes(targetFile)); // 200

    }
    // */

    // /*
    public static void main(String[] args) throws Exception {
        String sourceFile = "playground/sourcefileone.txt";
        String targetFile = "playground/targetfile.txt";
        copy(sourceFile, targetFile);
    }
    // */

}
