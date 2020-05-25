package basic;

import java.time.ZoneId;

public class ZoneIdsTest {
    public static void main(String[] args) {
        ZoneId.getAvailableZoneIds().stream()
                .filter(z -> z.contains("US") || z.contains("Europe"))
                .sorted()
                .forEach(System.out::println);

        System.out.println(ZoneId.systemDefault()); // Europe/Paris
    }
}
