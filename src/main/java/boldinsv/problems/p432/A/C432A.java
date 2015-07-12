package boldinsv.problems.p432.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

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

            items = reader.readLine().split(" ");

            peoples = new int[items.length];
            for (int index = 0; index < items.length; index++) {
                peoples[index] = Integer.parseInt(items[index]);
            }

            reader.close();
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public static void run() {
        Arrays.sort(peoples);

        answer = 0;
        stop:
        for (int index = 0; index < peoples.length; index++) {
            if ((5 - peoples[index]) < k) {
                break stop;
            }

            if ((index + 1) % 3 == 0) {
                answer++;
            }
        }
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
