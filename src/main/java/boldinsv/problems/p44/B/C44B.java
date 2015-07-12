package boldinsv.problems.p44.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C44B {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] items = reader.readLine().split(" ");

        int amountL = Integer.parseInt(items[0]);
        int a = Integer.parseInt(items[1]);
        int b = Integer.parseInt(items[2]);
        int c = Integer.parseInt(items[3]);
        int answer = 0;

        for (int bottle2l = 0; bottle2l <= c; bottle2l++) {
            for (int bottle1l = 0; bottle1l <= b; bottle1l++) {
                int bottle05l = (amountL - (bottle2l * 2) - bottle1l) * 2;

                if (bottle05l >= 0 && bottle05l <= a) {
                    answer++;
                }
            }
        }

        System.out.println("" + answer);
    }
}


