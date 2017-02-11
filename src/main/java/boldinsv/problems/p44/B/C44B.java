package boldinsv.problems.p44.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

public class C44B {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] items = reader.readLine().split(" ");

        int amountL = Integer.parseInt(items[0]);
        int a = Integer.parseInt(items[1]);
        int b = Integer.parseInt(items[2]);
        int c = Integer.parseInt(items[3]);

        int answer = IntStream
                .rangeClosed(0, c)
                .map(bottle2l -> {
                    return IntStream
                            .rangeClosed(0, b)
                            .filter(bottle1l -> {
                                int bottle05l = (amountL - (bottle2l * 2) - bottle1l) * 2;
                                return bottle05l >= 0 && bottle05l <= a;
                            })
                            .map(bottle1l -> 1)
                            .sum();
                })
                .sum();

        System.out.println("" + answer);
    }
}


