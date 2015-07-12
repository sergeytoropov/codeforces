package boldinsv.problems.p432.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class C432C2 {

    public static int[] simpleNumbers(int capacity) {
        int[] array = new int[capacity];
        int length = 0;

        for (int number = 1; number <= capacity; number++) {
            boolean simple = true;

            for (int n = 2; n <= capacity; n++) {

                if (number % n == 0 && number != n) {
                    simple = false;
                    break;
                }
            }

            if (simple) {
                array[length++] = number;
            }
        }

        return Arrays.copyOf(array, length);
    }

    public static int binarySearch(int[] array, int value) {
        int index = -1;

        if (array.length > 0) {
            int left = 0;
            int right = array.length - 1;

            while (true) {
                if (left == right) {
                    index = left;
                    break;
                }

                if (right - left == 1) {
                    index = value < array[right] ? left : right;
                    break;
                }

                int middle = (left + right) / 2;

                if (value > array[middle]) {
                    left = middle;
                } else {
                    right = middle;
                }

            }
        }
        return index;
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
        public int[] indexes;
        public int[] sortNumbers;
        public int[] simpleNumbers;
        public ArrayList<String> answer = new ArrayList<String>();

        public void swap(int indexFrom, int indexTo) {
            final int EXIT = 0;
            final int SIMPLE_INDEX = 1;
            final int SWAP_ITEM = 2;
            final int ANSWER = 3;
            final int SEARCH_SIMPLE_INDEX = 4;
            final int NEXT_SWAP = 5;
            int state;

            int beginIndex = indexFrom;

            state = SIMPLE_INDEX;
            for (boolean isContinue = true; isContinue; ) {
                switch (state) {
                    case EXIT:
                        isContinue = false;
                        break;

                    case SIMPLE_INDEX:
                        state = SEARCH_SIMPLE_INDEX;

                        if (Arrays.binarySearch(simpleNumbers, (indexTo - indexFrom + 1)) > 0) {
                            state = SWAP_ITEM;
                        }
                        break;

                    case SWAP_ITEM:
                        state = ANSWER;

                        int temp;

                        temp = indexes[indexFrom];
                        indexes[indexFrom] = indexes[indexTo];
                        indexes[indexTo] = temp;

                        temp = numbers[indexFrom];
                        numbers[indexFrom] = numbers[indexTo];
                        numbers[indexTo] = temp;
                        break;

                    case ANSWER:
                        state = NEXT_SWAP;

                        answer.add((indexFrom + 1) + " " + (indexTo + 1));
                        break;

                    case SEARCH_SIMPLE_INDEX:
                        state = SWAP_ITEM;

                        int delta = C432C2.binarySearch(simpleNumbers, (indexTo - indexFrom - 1));
                        indexFrom = indexTo - simpleNumbers[delta] + 1;
                        break;

                    case NEXT_SWAP:
                        state = EXIT;

                        if (beginIndex != indexFrom) {
                            state = SIMPLE_INDEX;

                            indexTo = indexFrom;
                            indexFrom = beginIndex;
                        }
                        break;
                }
            }

        }

        public int search(int indexFrom, int value) {
            for (int index = indexFrom; index < numbers.length; index++) {
                if (value == numbers[index]) {
                    return index;
                }
            }
            return -1;
        }

        public int search(int value) {
            return indexes[value];
        }

        public void init() {
            answer.clear();
            //simpleNumbers = C432C.simpleNumbers(numbers.length / 5 + 1);
            simpleNumbers = C432C2.simpleNumbers(20001);
            sortNumbers = Arrays.copyOf(numbers, numbers.length);
            Arrays.sort(sortNumbers);
        }

        public void sort() {
            final int EXIT = 0;
            final int ITEM = 1;
            final int SEARCH = 2;
            final int SWAP = 3;
            int state;

            int index = 0;
            int indexFrom = 0;
            int indexTo = 0;

            state = ITEM;
            for (boolean isContinue = true; isContinue; ) {
                switch (state) {
                    case EXIT:
                        isContinue = false;
                        break;

                    case ITEM:
                        state = EXIT;

                        if (index < numbers.length) {
                            state = SEARCH;

                            if (numbers[index] == sortNumbers[index]) {
                                state = ITEM;

                                index++;
                            }
                        }
                        break;

                    case SEARCH:
                        state = SWAP;

                        indexFrom = index;
                        //indexTo = search(indexFrom, sortNumbers[indexFrom]);
                        indexTo = search(sortNumbers[indexFrom]);
                        break;

                    case SWAP:
                        state = ITEM;

                        swap(indexFrom, indexTo);
                        index++;
                        break;
                }
            }
        }

        public void run() {
            init();
            sort();
        }

        private class InputData implements Data {
            public void init() {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String[] items;

                try {
                    int length = Integer.parseInt(reader.readLine());

                    items = reader.readLine().split(" ");
                    numbers = new int[items.length];
                    indexes = new int[items.length];

                    for (int index = 0; index < items.length; index++) {
                        numbers[index] = Integer.parseInt(items[index]) - 1;
                        indexes[numbers[index]] = index;
                    }

                } catch (IOException ioe) {
                    System.out.println(ioe);
                }
            }
        }

        private class OutputData implements Answer {
            public void print() {
                System.out.println("" + answer.size());
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
