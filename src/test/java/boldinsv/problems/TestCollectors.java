package boldinsv.problems;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by sergeytoropov on 17.02.17.
 */
public class TestCollectors {
    public static void main(String[] args) {
        System.out.println("toList");
        List<Integer> listInt = IntStream.range(0, 10).mapToObj(Integer::valueOf)
                .collect(Collectors.toList());

        listInt.stream().forEach(System.out::print);

        System.out.println("");
        System.out.println("toSet");
        Set<Integer> setInt = IntStream.iterate(0, i -> i + 1).limit(10).mapToObj(Integer::new)
                .collect(Collectors.toSet());

        setInt.stream().forEach(System.out::print);

        System.out.println();
        System.out.println("joining");
        String s = IntStream.range(0, 5).mapToObj(String::valueOf).collect(Collectors.joining("-|-", "Start: ", " :End!"));
        System.out.println(s);

        System.out.println("");
        System.out.println("mapping");
        List<String> listStr = IntStream.range(0, 5).mapToObj(Integer::valueOf)
                .collect(Collectors.mapping(value -> String.valueOf((value * 10)) + ", ", Collectors.toList()));

        listStr.stream().forEach(System.out::print);

        System.out.println();
        System.out.println("collectingAndThen");

        listInt = IntStream.range(0, 10).mapToObj(Integer::new)
                .collect(Collectors.collectingAndThen(Collectors.toList(), (list) -> {
                    return list.stream().map(val -> val * 100).collect(Collectors.toList());
                }));

        listInt.stream().forEach(System.out::print);

        System.out.println(
                "\n\nCounting = " + IntStream.range(0, 5).mapToObj(Integer::valueOf).collect(Collectors.counting())
        );

        System.out.println(
                "\n\nSummingInt = " + IntStream.range(0, 5).mapToObj(Integer::valueOf)
                        .collect(Collectors.summingInt(v -> v))
        );

        System.out.println();
        System.out.println("Reducing");
        Integer sum = IntStream.range(0, 5).mapToObj(Integer::new)
                .collect(Collectors.reducing(0, v -> v * 10, (acc, item) -> acc += item * 10));
        System.out.println("Sum = " + sum);

        System.out.println();
        System.out.println("groupingBy");
        Map<Integer, List<Integer>> mapInt = IntStream.range(0, 100).mapToObj(Integer::valueOf)
                .collect(Collectors.groupingBy(value -> value % 2 == 0 ? 0 : 1));
        mapInt.entrySet().stream().forEach(item -> {
            System.out.println("key = " + item.getKey() + " list: " + item.getValue());
        });

        System.out.println();
        System.out.println("groupingBy 2 ");
        Map<Integer, String> mapIntStr = IntStream.range(0, 100).mapToObj(Integer::valueOf)
                .collect(Collectors.groupingBy(value -> value % 2 == 0 ? 0 : 1, Collectors.mapping(String::valueOf, Collectors.joining())));
        mapIntStr.entrySet().stream().forEach(item -> {
            System.out.println("key = " + item.getKey() + " list: " + item.getValue());
        });


        System.out.println();
        System.out.println("partional By");
        Map<Boolean, List<Integer>> mapBoolInt = IntStream.range(0, 10).mapToObj(Integer::valueOf)
                .collect(Collectors.partitioningBy(value -> value % 2 == 0 ? true : false));
        mapBoolInt.entrySet().stream().forEach(item -> {
            System.out.println("key = " + item.getKey() + " list: " + item.getValue());
        });

        System.out.println();
        System.out.println("partional By 2");
        Map<Boolean, String> mapBoolStr = IntStream.range(0, 10).mapToObj(Integer::valueOf)
                .collect(Collectors.partitioningBy(value -> value % 2 == 0 ? true : false,
                        Collectors.mapping(String::valueOf, Collectors.joining(", | "))));
        mapBoolStr.entrySet().stream().forEach(item -> {
            System.out.println("key = " + item.getKey() + " list: " + item.getValue());
        });


        System.out.println();
        System.out.println("Map");
        Map<Integer, String> map = IntStream.range(0, 10).mapToObj(Integer::valueOf)
                .collect(Collectors.toMap(v -> v * 10, v -> "Name is a " + String.valueOf(v) + "!"));

        System.out.println(map.getClass());
        map.entrySet().stream().forEach(item -> {
            System.out.println("key = " + item.getKey() + " list: " + item.getValue());
        });

        System.out.println();
        System.out.println("Map 3");
        Map<Integer, String> map3 = IntStream.range(0, 10).mapToObj(Integer::valueOf)
                .collect(Collectors.toMap(v -> v * 10,
                        v -> "[" + String.valueOf(v) + "]",
                        (key, value) -> {
                            System.out.println("key = " + key + ", value = " + value);
                            return "1";
                        }, TreeMap::new));

        System.out.println(map3.getClass());
        map3.entrySet().stream().forEach(item -> {
            System.out.println("key = " + item.getKey() + " list: " + item.getValue());
        });


        System.out.println();
        System.out.println("sumarazingInt");
        IntSummaryStatistics iss = IntStream
                .range(0, 10)
                .mapToObj(Integer::valueOf)
                .collect(Collectors.summarizingInt(v -> v * 10));
        System.out.println(iss);
    }
}
