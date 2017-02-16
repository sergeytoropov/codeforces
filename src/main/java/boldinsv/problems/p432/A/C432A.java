package boldinsv.problems.p432.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

public class C432A {
    public static int n;
    public static int k;
    public static int[] peoples;
    public static int answer;

    public static void init() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] items;

        try {
            items = reader.readLine().split(" ");

            n = Integer.parseInt(items[0]);
            k = Integer.parseInt(items[1]);

            peoples = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            reader.close();
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public static void run() {
        Arrays.sort(peoples);

        answer = IntStream
                .range(0, peoples.length)
                .filter(index -> ((5 - peoples[index]) >= k))
                .filter(index -> ((index + 1) % 3 == 0))
                .map(index -> 1)
                .sum();
    }

    public static void print() {
        System.out.println("" + answer);
    }

    public static void main(String[] args) {
        init();
        run();
        print();
    }
}
