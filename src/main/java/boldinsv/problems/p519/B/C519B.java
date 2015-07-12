package boldinsv.problems.p519.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class C519B {

    interface Data {
        void init();
    }

    interface Readable {
        Data read();
    }

    interface Answer {
        void print();
    }

    interface Printable {
        Answer answer();
    }
    public static class Algorithm implements Readable, Printable {
        private int n;
        private long[] an;
        private long[] bn;
        private long[] cn;

        public ArrayList<String> answer = new ArrayList<String>();

        public Algorithm() {}

        public Algorithm(long[] an, long[] bn, long[] cn) {
            this.an = an;
            this.bn = bn;
            this.cn = cn;
        }

        public int getN() {
            return n;
        }

        public long[] getAn() {
            return an;
        }

        public long[] getBn() {
            return bn;
        }

        public long[] getCn() {
            return cn;
        }

        private class InputData implements Data {

            private long[] createArray(String line) {
                String[] items = line.split(" ");
                long[] array = new long[items.length];

                for (int index = 0; index < items.length; index++) {
                    array[index] = Integer.parseInt(items[index]);
                }
                return array;
            }

            public void init() {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String[] items;

                try {
                    n = Integer.parseInt(reader.readLine());
                    an = createArray(reader.readLine());
                    bn = createArray(reader.readLine());
                    cn = createArray(reader.readLine());

                    reader.close();
                } catch (IOException ioe) {
                    System.out.println(ioe);
                }
            }
        }

        public Data read() {
            return new InputData();
        }

        private String search(long[] a, long[] b) {
            for (int index = 0; index < a.length; index++) {
                if (index >= b.length) {
                    return "" + a[index];
                }

                if (a[index] != b[index]) {
                    return "" + a[index];
                }
            }
            return "NoN";
        }

        public void run() {
            Arrays.sort(an);
            Arrays.sort(bn);
            Arrays.sort(cn);

            answer.clear();
            answer.add(search(an, bn));
            answer.add(search(bn, cn));
        }

        private class OutputData implements Answer {

            public void print() {
                for (String line: answer) {
                    System.out.println(line);
                }
            }
        }

        public Answer answer() {
            return new OutputData();
        }
    }

    public static void main(String[] args) {
        Algorithm algorithm = new Algorithm();
        algorithm.read().init();
        algorithm.run();
        algorithm.answer().print();
    }
}
