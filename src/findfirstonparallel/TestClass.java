/*
    regarding findFirst() method of java.util.stream.Stream<T> interface
                          (public interface Stream<T> extends BaseStream<T,​Stream<T>>)
            declared like this : Optional<T> findFirst()

    javadoc says :
         Returns an Optional describing the first element of this stream, or an empty Optional if
         the stream is empty. If the stream has no encounter order, then any element may be returned.
         This is a short-circuiting terminal operation.

        Returns:
        an Optional describing the first element of this stream, or an empty Optional if the stream is empty
        Throws:
        NullPointerException - if the element selected is null



    if applied like this : aListOfString.parallelStream().findFirst()
        - then it appears to always indeed return the first element of the list

    but if applied like this : aListOfString.parallelStream().unordered().findFirst()
        - then it might return any element of the list


        equal.parallelStream().unordered().findFirst()
            - or -
        equal.stream().unordered().parallel().findFirst()
            - ... same results : findFirst can return any element
 */

package findfirstonparallel;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestClass {
    public static void main(String[] args) {
        List<String> equal= IntStream.range(0, 100)
                .mapToObj(i->new String("test")) // don't do this in normal code
                .collect(Collectors.toList());
        Map<String, Integer> map = IntStream.range(0, equal.size())
                .collect(IdentityHashMap::new, (m, i)->m.put(equal.get(i),i), Map::putAll);

        System.out.println(equal.stream().distinct().count());

        equal.parallelStream().distinct().map(map::get)
                .findFirst().ifPresent(System.out::println);

        equal.parallelStream().unordered().distinct().map(map::get)
                .findFirst().ifPresent(System.out::println);

        System.out.println("Tests : ");
        equal.parallelStream().findFirst().map(s->indexOfSameReference(equal,s)).ifPresent(System.out::println);
        equal.parallelStream().unordered().findFirst().map(s->indexOfSameReference(equal,s)).ifPresent(System.out::println);
        equal.stream().findFirst().map(s->indexOfSameReference(equal,s)).ifPresent(System.out::println);
        equal.stream().unordered().findFirst().map(s->indexOfSameReference(equal,s)).ifPresent(System.out::println);
        equal.stream().unordered().parallel().findFirst().map(s->indexOfSameReference(equal,s)).ifPresent(System.out::println);
        equal.parallelStream().unordered().sequential().findFirst().map(s->indexOfSameReference(equal,s)).ifPresent(System.out::println);

    }

    private static <T> int indexOfSameReference(List<? extends T> list, T element) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == element) {
                return i;
            }
        }
        return -1;
    }
}

class littleQuestionAnswerer{
    public static void main(String[] args) {
        /*
        StringBuilder c = new StringBuilder("charseq");
        String s = "charseq";
        System.out.println(s==c); // WONT COMPILE
        // Error:(44, 29) java: incomparable types: java.lang.String and java.lang.StringBuilder
        */
        CharSequence c = new StringBuilder("charseq");
        String s = "charseq";
        System.out.println(s==c); // Compiles fine and prints false

        CharSequence c2 = "charseq";
        String s2 = "charseq";
        System.out.println(s2==c2); // Compiles fine and prints true
    }
}

class littleQuestionAnswerer2{
    public static void main(String[] args) {
        final Stream<String> hello = Stream.of("Hello", null, "3");
        System.out.println(hello.getClass());
        System.out.println(hello.sequential() == hello);//true
        System.out.println(hello.parallel() == hello);//true
        String s = "3";
        final Stream<String> hello2;
        System.out.println((hello2 = hello.filter(svar->svar!=s)) == hello);//false

        //System.out.println(hello.count()); // Exception in thread "main"
        //      java.lang.IllegalStateException: stream has already been operated upon or closed
        //System.out.println(hello2.count()); // prints 2
        //System.out.println(hello2.filter(svar -> !"Hello".equals(svar)).findAny()); // Exception
        //      in thread "main" java.lang.NullPointerException
        hello2.filter(svar -> !"Hello".equals(svar)).forEach(System.out::println); //null
    }
}
