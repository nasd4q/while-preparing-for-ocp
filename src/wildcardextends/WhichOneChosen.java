package wildcardextends;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class WhichOneChosen {
    <T> T method1(List<? extends T> list) {
        return list.get(0);
    }

    public static void main(String[] args) {
        List<CharSequence> listOne = new ArrayList<>();
        AtomicInteger i = new AtomicInteger(0);
        List listTwo = new ArrayList<Boolean>();
        Stream.of("tRue", "True","TRUE", "truE", "true.", "true,", " true", "true ", "true")
                .forEach(
                        ((Consumer<String>)listOne::add).andThen(
                                x->listTwo.add(Boolean.valueOf(x))
                        ).andThen(
                                x->System.out.println(listTwo.get(i.getAndIncrement()))
                        )
                ).;
        System.out.println(listOne);
        System.out.println(listTwo);
    }

}

