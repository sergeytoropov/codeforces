package boldinsv.problems.p380.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;

public class C380A {
    public static final int TYPE   = 0;
    public static final int VALUE  = 1;
    public static final int LI     = 2;
    public static final int CI     = 3;
    public static final int AMOUNT = 4;

    public static final int TYPE_VALUE    = 1;
    public static final int TYPE_SEQUENCE = 2;

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
        public int sequenceLength = 0;
        public long[][] table;
        public long[] indexes;
        public long[] sequence;
        public LinkedList<Long> answer = new LinkedList<Long>();

        public static int bSearch(long[] array, int left, int right, long index) {
            if (left == right) {
                return left;
            }

            if (right - left == 1) {
                if (index <= array[left]) {
                    return left;
                }
                return right;
            }

            int middle = (left + right) >> 1;

            return index <= array[middle] ? bSearch(array, left, middle, index) : bSearch(array, middle + 1, right, index);
        }

        public void createSequence() {
            int pos = 0;

            sequence = new long[sequenceLength];
            for (int index = 0; index < table[TYPE].length; index++) {

                switch ((int) table[TYPE][index]) {
                    case TYPE_VALUE:
                        if (pos < sequence.length) {
                            sequence[pos++] = table[VALUE][index];
                        }
                        break;

                    case TYPE_SEQUENCE:
                        for (int i = 0; i < table[CI][index]; i++) {
                            for (int j = 0; j < table[LI][index]; j++) {

                                if (pos < sequence.length) {
                                    sequence[pos++] = sequence[j];
                                }
                            }
                        }
                        break;
                }

                if (pos >= sequence.length) {
                    break;
                }
            }
        }

        public void run() {
            answer.clear();

            createSequence();
            for (int i = 0; i < indexes.length; i++) {
                int pos = Algorithm.bSearch(table[AMOUNT], 0, table[AMOUNT].length - 1, indexes[i]);

                switch ((int) table[TYPE][pos]) {
                    case TYPE_VALUE:
                        answer.add(table[VALUE][pos]);
                        break;

                    case TYPE_SEQUENCE:
                        int index = (int) (((indexes[i] - table[AMOUNT][pos - 1]) - 1) % table[LI][pos]);
                        answer.add(sequence[index]);
                        break;
                }
            }
        }

        private class InputData implements Data {

            public void init() {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String[] items;

                try {
                    long amount = 0;
                    int m = Integer.parseInt(reader.readLine());

                    table = new long[5][m];
                    for (int index = 0; index < m; index++) {
                        items = reader.readLine().split(" ");

                        if (index > 0) {
                            amount = table[AMOUNT][index - 1];
                        }

                        switch (Integer.parseInt(items[0])) {
                            case 1:
                                table[TYPE][index] = TYPE_VALUE;
                                table[VALUE][index] = Long.parseLong(items[1]);
                                table[AMOUNT][index] = amount + 1;
                                break;

                            case 2:
                                int li = Integer.parseInt(items[1]);

                                if (sequenceLength < li) {
                                    sequenceLength = li;
                                }

                                table[TYPE][index] = TYPE_SEQUENCE;
                                table[LI][index] = li;
                                table[CI][index] = Long.parseLong(items[2]);
                                table[AMOUNT][index] = amount + (table[LI][index] * table[CI][index]);
                                break;
                        }
                    }

                    int n = Integer.parseInt(reader.readLine());
                    indexes = new long[n];

                    items = reader.readLine().split(" ");
                    for (int index = 0; index < items.length; index++) {
                        indexes[index] = Long.parseLong(items[index]);
                    }

                } catch (IOException ioe) {
                    System.out.println(ioe);
                }

            }
        }

        private class OutputData implements Answer {

            public void print() {
                StringBuilder builder = new StringBuilder();

                Iterator<Long> iter = answer.iterator();
                while (iter.hasNext()) {
                    long value = iter.next();
                    builder.append(value);
                    builder.append(" ");
                }

                System.out.println(builder.toString().trim());
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
