/*
    2 items getting made sure here :
        1st -> try (giveAResource()) won't compile
            "Error:(24, 26) java: the try-with-resources resource must either be a variable declaration
            or an expression denoting a reference to a final or effectively final variable"
        2nd -> you can absolutely catch any subclass of "declared thrown" exception, of course !

 */

package trywithresources;

import java.io.IOException;

class Resource implements AutoCloseable {

    @Override
    public void close() throws IOException {
        System.out.println("closing");
    }
}

public class TestClass {
    public static Resource giveAResource() {
        return new Resource();
    }

    public static void main(String[] args) {
        //try(giveAResource()) {

        try(final Resource resource = giveAResource()) {

        } catch (java.io.InvalidClassException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
