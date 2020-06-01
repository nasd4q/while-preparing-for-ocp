package reducevscollect;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class User {
    String name = "Al";
    int age = 34;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    static User of(String name, int age) {
        return new User(name, age);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

public class TestClass {
    static Stream<User> users() {
        return Stream.of(
                User.of("Al", 34),
                User.of("Gordon", 29),
                User.of("John", 34),
                User.of("Jack", 50),
                User.of("Boris", 12),
                User.of("Zack", 39),
                User.of("Pops", 71));
    }

    public static void main(String[] args) {
        final HashSet<String> collections1 = users().reduce(new HashSet<String>(),
                (h, s) -> {
                    h.add(s.name);
                    return h;
                }, (h, k) -> {
                    h.addAll(k);
                    return h;
                });
        System.out.println(collections1);

        final Set<String> collect = users().map(User::getName).collect(Collectors.toUnmodifiableSet());
        System.out.println(collect);

        System.out.println(users().collect(Collectors.summarizingInt(User::getAge)));

        final Stream<User> users = users();
        System.out.println(users.isParallel());
        users.sorted(Comparator.nullsFirst(Comparator.comparing(User::getName)))
                .map(User::getName).forEach(System.out::println);
        final Map<Integer, String> collect1 = users().collect(Collectors.groupingBy(u -> u.getAge()/10,
                Collectors.mapping(User::getName, Collectors.joining("+"))));
        System.out.println(collect1);

        final IntSummaryStatistics reduce = users().reduce(
                new IntSummaryStatistics(),
                (iss, u) -> {iss.accept(u.getAge()); return iss;},
                (issA, issB) -> {issA.combine(issB);return issA;});
        System.out.println(reduce);

        /*
        Comparator<Person> byHeight = Comparator.comparing(Person::getHeight);
        Map<City, Optional<Person>> tallestByCity
                = people.stream().collect(
                groupingBy(Person::getCity,
                        reducing(BinaryOperator.maxBy(byHeight))));

         */
    }
}

