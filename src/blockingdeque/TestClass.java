package blockingdeque;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Stream;

public class TestClass {
    private static final BlockingDeque<Integer> deque = new LinkedBlockingDeque<>(10);

    private static void useDeque() throws InterruptedException {
        deque.addAll(streamIterateTest());
        System.out.println(deque.offer(13));

        System.out.println(deque.offer(14,5, TimeUnit.SECONDS));
        System.out.println(deque.offerLast(15));
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(streamIterateTest());
        useDeque();

    }

    private static List<Integer> streamIterateTest() {
        List<Integer> collect = Stream.iterate(1, i -> ++i).limit(10)
                .collect(
                () -> new ArrayList<Integer>(),
                (a, i) -> a.add(i),
                (a1, a2) -> {
                    //System.out.println("Combiner used !");
                    a1.addAll(a2);
                });
        return collect;
    }
}
