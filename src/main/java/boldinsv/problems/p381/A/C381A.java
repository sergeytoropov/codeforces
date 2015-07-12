package boldinsv.problems.p381.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class C381A {
    public static int n;
    public static Deque<Integer> numbers;
    public static int sumSergey;
    public static int sumDima;

    public static void init() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] items;

        try {
            n = Integer.parseInt(reader.readLine());
            items = reader.readLine().split(" ");

            numbers = new LinkedList<Integer>();
            for (String item: items) {
                numbers.add(Integer.parseInt(item));
            }

            reader.close();
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public static void run() {
        final int SERGEY = 0;
        final int DIMA   = 1;
        int state = SERGEY;

        sumSergey = 0;
        sumDima   = 0;
        while (numbers.size() > 0) {
            int max = numbers.peekFirst() > numbers.peekLast() ? numbers.pollFirst() : numbers.pollLast();

            switch (state) {
                case SERGEY:
                    state = DIMA;

                    sumSergey += max;
                    break;

                case DIMA:
                    state = SERGEY;

                    sumDima += max;
                    break;
            }
        }
    }

    public static void print() {
        System.out.println(sumSergey + " " + sumDima);
    }

    public static void main(String[] args) {
        init();
        run();
        print();
    }
}
