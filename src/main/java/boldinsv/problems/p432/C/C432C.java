package boldinsv.problems.p432.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class C432C {

    public static ArrayList<Integer> simpleNumbers() {
        return simpleNumbers(100001);
    }

    public static ArrayList<Integer> simpleNumbers(int capacity) {
        boolean[] row = new boolean[capacity];

        row[0] = false;
        row[1] = false;
        for (int index = 2; index < row.length; index++) {
            row[index] = true;
        }

        for (int number = 2; number * number < row.length; number++) {
            if (row[number]) {

                for (int index = number * number; index < row.length; index += number) {
                    row[index] = false;
                }
            }
        }

        ArrayList<Integer> simpleNumbers = new ArrayList<Integer>();

        for (int number = 0; number < row.length; number++) {
            if (row[number]) {
                simpleNumbers.add(number);
            }
        }
        return simpleNumbers;
    }

    interface Data {
        void init();
    }

    interface Readable {
        Data data();
    }

    interface Answer {
        void print();
    }

    interface Printable {
        Answer answer();
    }

    public static class Algorithm implements Readable, Printable {
        public int[] numbers;
        public int[] sortNumbers;
        public ArrayList<String> answer = new ArrayList<String>();

        public void run() {
            ArrayList<Integer> simpleNumbers = simpleNumbers();

            answer = new ArrayList<String>();
            for (int number = 0; number < sortNumbers.length; number++) {
                while (sortNumbers[number] > number) {

                    int index = Collections.binarySearch(simpleNumbers, sortNumbers[number] - number + 1);
                    if (index < 0) {
                        index = -1 * (index + 1) - 1;
                    }

                    int fromIndex = sortNumbers[number] - simpleNumbers.get(index) + 1;
                    int toIndex = sortNumbers[number];

                    answer.add((fromIndex + 1) + " " + (toIndex + 1));

                    sortNumbers[numbers[fromIndex]] = toIndex;
                    sortNumbers[numbers[toIndex]] = fromIndex;

                    int temp = numbers[fromIndex];
                    numbers[fromIndex] = numbers[toIndex];
                    numbers[toIndex] = temp;
                }
            }
        }

        private class InputData implements Data {
            public void init() {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String[] items;

                try {
                    int length = Integer.parseInt(reader.readLine());

                    items = reader.readLine().split(" ");
                    numbers = new int[items.length];
                    sortNumbers = new int[items.length];

                    for (int index = 0; index < items.length; index++) {
                        numbers[index] = Integer.parseInt(items[index]) - 1;
                        sortNumbers[numbers[index]] = index;
                    }

                } catch (IOException ioe) {
                    System.out.println(ioe);
                }
            }
        }

        private class OutputData implements Answer {
            public void print() {
                System.out.println(answer.size());
                for (String line: answer) {
                    System.out.println(line);
                }
            }
        }

        public Data data() {
            return new InputData();
        }

        public Answer answer() {
            return new OutputData();
        }
    }

    public static void main(String[] args) {
        Algorithm algorithm = new Algorithm();

        algorithm.data().init();
        algorithm.run();
        algorithm.answer().print();
    }
}
