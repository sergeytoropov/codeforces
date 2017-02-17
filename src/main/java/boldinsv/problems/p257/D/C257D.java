package boldinsv.problems.p257.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.stream.Collector;
import java.util.stream.IntStream;

public class C257D {
    private static class Answer {
        public char signature = '-';
        public StringJoiner result = new StringJoiner("");
    }

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(reader.readLine());
            int[] a = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            reader.close();

            int[] b = new int[n];
            IntStream
                    .iterate(n - 2, i -> i - 1)
                    .limit(n - 1)
                    .reduce(a[n - 1], (s, i) -> {
                        if (a[i] > s) {
                            s = a[i] - s;
                        } else {
                            b[i] = 1;
                            s -= a[i];
                        }
                        return s;
                    });

            Collector<Integer, Answer, String> answerCollector =
                    Collector.of(
                        () -> new Answer(),
                        (acc, element) -> {
                            if (element == 0) {
                                acc.signature = acc.signature == '+' ? '-' : '+';
                            }
                            acc.result.add(String.valueOf(acc.signature));
                        },
                        (j1, j2) -> new Answer(), // Заглушка
                        (answer) -> answer.result.toString()
                    );

            System.out.println(
                    Arrays.stream(b).mapToObj(Integer::valueOf).collect(answerCollector));

        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }
}
