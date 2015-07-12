package boldinsv.problems.p489.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

public class C489A {

    public static class Init {
        public int n;
        public long[] array;

        public Init() throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            n = Integer.parseInt(reader.readLine());
            String[] items = reader.readLine().split(" ");

            array = new long[items.length];
            for (int index = 0; index < items.length; index++) {
                array[index] = Integer.parseInt(items[index]);
            }
        }
    }

    interface Answer {
        void print();
    }

    interface Printable {
        Answer answer();
    }

    public static class Algorithm implements Printable {
        private long[] array;
        private LinkedList<String> answer = new LinkedList<String>();

        public Algorithm(long[] array) {
            this.array = array;
        }

        public static int search(long[] array, int from, int to, long key) {
            if (from < 0 || to >= array.length) {
                return -1;
            }

            for (int index = from; index <= to; index++) {
                if (array[index] == key) {
                    return index;
                }
            }
            return -1;
        }

        public int swap(long value, int index) {
            int toIndex = search(array, index + 1, array.length - 1, value);

            array[toIndex] = array[index];
            array[index] = value;

            return toIndex;
        }

        public void run() {
            answer.clear();

            long[] sortArray = Arrays.copyOf(array, array.length);
            Arrays.sort(sortArray);

            int swapCounter = 0;
            for (int index = 0; index < sortArray.length; index++) {

                if (sortArray[index] != array[index]) {
                    int toIndex = swap(sortArray[index], index);

                    answer.addLast(index + " " + toIndex);
                    swapCounter += 1;
                }
            }
            answer.addFirst("" + swapCounter);
        }

        private class A implements Answer {
            public void print() {
                ListIterator<String> iter = answer.listIterator();

                while (iter.hasNext()) {
                    System.out.println(iter.next());
                }
            }
        }

        public Answer answer() {
            return new A();
        }
    }

    public static void main(String[] args) throws IOException {
        Init init = new Init();
        Algorithm algorithm = new Algorithm(init.array);
        algorithm.run();
        algorithm.answer().print();
    }
}
