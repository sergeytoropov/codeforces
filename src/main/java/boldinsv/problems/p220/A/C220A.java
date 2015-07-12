package boldinsv.problems.p220.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class C220A {
    public static int n;
    public static int[] sequence;
    public static String answer;

    public static void init() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] items;

        try {
            n = Integer.parseInt(reader.readLine());

            items = reader.readLine().split(" ");
            sequence = new int[items.length];

            for (int index = 0; index < items.length; index++) {
                sequence[index] = Integer.parseInt(items[index]);
            }

            reader.close();
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public static int found(int[] sequence, int[] sortSequence, int beginIndex, int number) {
        for (int index = beginIndex; index < sequence.length; index++) {
            if (sequence[index] == number && sequence[index] != sortSequence[index]) {
                return index;
            }
        }
        return -1;
    }

    public static void run() {
        int swaping = 0;
        int[] sortSequence = Arrays.copyOf(sequence, sequence.length);

        answer = "YES";
        Arrays.sort(sortSequence);

        exit:
        for (int index = 0; index < sequence.length; index++) {
            if (sequence[index] != sortSequence[index]) {
                int position = found(sequence, sortSequence, index + 1, sortSequence[index]);

                swaping += 1;
                if (position == -1 || swaping > 1) {
                    answer = "NO";
                    break exit;
                }

                int temp = sequence[index];
                sequence[index] = sequence[position];
                sequence[position] = temp;
            }
        }
    }

    public static void print() {
        System.out.println(answer);
    }

    public static void main(String[] args) {
        init();
        run();
        print();
    }

}
