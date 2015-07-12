package boldinsv.problems.p544.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C544B {
    public static int n;
    public static int k;
    public static int[][] map;
    public static boolean answer;

    public static void init() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            try {
                String[] items = reader.readLine().split(" ");

                n = Integer.parseInt(items[0]);
                k = Integer.parseInt(items[1]);
            } finally {
                reader.close();
            }
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public static void run() {
        int counter = 0;

        map = new int[n][n];
        answer = k == 0 ? true : false;

        stop:
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {

                if (i % 2 == 0 && j % 2 == 0 || i % 2 != 0 && j % 2 != 0) {
                    counter += 1;

                    if (counter > k) {
                        break stop;
                    } else if (counter == k) {
                        answer = true;
                    }

                    map[i][j] = 1;
                }
            }
        }
    }

    public static void print() {
        if (answer) {
            System.out.println("YES");

            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {

                    if (map[i][j] == 1) {
                        System.out.print("L");
                    } else {
                        System.out.print("S");
                    }
                }
                System.out.println();
            }
        } else {
            System.out.println("NO");
        }
    }

    public static void main(String[] args) {
        init();
        run();
        print();
    }
}
