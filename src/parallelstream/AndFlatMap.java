/*
    flatMap doesn't seem to change the parallelism of a stream : if was parallel then stays parallel, and if
    was sequential then stays sequential. The parallelism of "inner" streams doesn't seem to affect it.
 */

package parallelstream;

import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AndFlatMap {
    public static void main(String[] args) {
        Stream<String> cats = Stream.of("leopard","lynx","ocelot","puma");
        Stream<String> bears = Stream.of("panda","grizzly","polar");

        System.out.println(cats.isParallel()); // false

        cats.parallel();

        System.out.println(cats.isParallel()); // true

        Stream<String> joinedStream = Stream.of(cats,bears).flatMap(x->x);
        Stream<String> joinedStreamParallel = Stream.of(cats,bears).parallel().flatMap(x->x);

        System.out.println(joinedStream.isParallel()); // false
        System.out.println(joinedStreamParallel.isParallel()); // true
    }
}
