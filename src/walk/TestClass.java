package walk;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

interface ThrowingUnaryOperator<T> {
    T apply(T t) throws Exception;
}

public class TestClass {
    public static void main(String[] args) throws IOException {
        System.out.println("original :" + Paths.get(""));
        System.out.println("original :" + Paths.get("").toRealPath());
        Path src = Paths.get("src");
        final Stream<Path> walk = Files.walk(src, 9090);
        walk.map(wrapped(Path::toRealPath)).forEach(System.out::println);

        System.out.println("Files.list :");
        final Stream<Path> list = Files.list(src);
        list.forEach(System.out::println);

        System.out.println("Files.find :");
        Files.find(src,100,(p,a)->true).forEach(System.out::println);
    }

    private static <T> UnaryOperator<T> wrapped(ThrowingUnaryOperator<T> tc) {
        return new UnaryOperator<T>() {
            @Override
            public T apply(T t) {
                try {
                    return tc.apply(t);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };
    }
}
