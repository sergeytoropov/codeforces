package boldinsv.problems.p551.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C551A {
    public static int n;
    public static int[] ai;
    public static int[] result;

    public static void init() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            try {
                n = Integer.parseInt(reader.readLine());

                String[] items = reader.readLine().split(" ");
                ai = new int[items.length];

                for (int index = 0; index < items.length; index++) {
                    ai[index] = Integer.parseInt(items[index]);
                }
            } finally {
                reader.close();
            }
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public static void run() {
        result = new int[ai.length];

        for (int index = 0; index < ai.length; index++) {
            int position = 1;

            for (int j = 0; j < ai.length; j++) {
                if (ai[index] < ai[j]) {
                    position += 1;
                }
            }
            result[index] = position;
        }
    }

    public static void print() {
        String answer = "";

        for (int position: result) {
            answer += position + " ";
        }
        System.out.println(answer.trim());
    }

    public static void main(String[] args) {
        init();
        run();
        print();
    }
}
