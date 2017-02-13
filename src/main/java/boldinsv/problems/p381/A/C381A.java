package boldinsv.problems.p381.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.IntStream;

public class C381A {
    public static int n;
    public static Deque<Integer> numbers;
    public static Result result;

    public static void init() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            n = Integer.parseInt(reader.readLine());

            numbers = new LinkedList<>();
            Arrays.asList(reader.readLine().split(" ")).stream()
                    .map(Integer::parseInt)
                    .forEach(value -> numbers.add(value));

            reader.close();
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public static class Result {
        public enum Gamers {SERGEY, DIMA};
        public Gamers activeGamer = Gamers.SERGEY;

        public int max = 0;
        public int sumSergey = 0;
        public int sumDima = 0;

        public Result() {}

        public Result(Integer max) {
            this.max = max;
        }
    }

    public static void run() {
        result = IntStream
                .range(0, numbers.size())
                .mapToObj(index -> new Result(numbers.peekFirst() > numbers.peekLast() ? numbers.pollFirst() : numbers.pollLast()))
                .reduce(new Result(), (result, element) -> {
                    switch (result.activeGamer) {
                        case SERGEY:
                            result.activeGamer = Result.Gamers.DIMA;
                            result.sumSergey += element.max;
                            break;

                        case DIMA:
                            result.activeGamer = Result.Gamers.SERGEY;
                            result.sumDima += element.max;
                            break;
                    }
                    return result;
                });
    }

    public static void print() {
        System.out.println(result.sumSergey + " " + result.sumDima);
    }

    public static void main(String[] args) {
        init();
        run();
        print();
    }
}
