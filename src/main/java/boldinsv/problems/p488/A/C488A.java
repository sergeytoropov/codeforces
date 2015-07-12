package boldinsv.problems.p488.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C488A {

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
        private long a;
        private long b;

        public long getA() {
            return a;
        }

        public Algorithm() {}

        public Algorithm(long a) {
            this.a = a;
        }

        private class InputData implements Data {
            public void init() {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

                try {
                    a = Long.parseLong(reader.readLine());
                    reader.close();
                } catch (IOException ioe) {
                    System.out.println(ioe);
                }
            }
        }

        public Data read() {
            return new InputData();
        }

        private boolean happyNumber(long number) {
            char[] digits = ("" + number).toCharArray();

            for (int index = 0; index < digits.length; index++) {
                int digit = digits[index] - '0';

                if (digit != 0 && digit % 8 == 0) {
                    return true;
                }
            }
            return false;
        }
        public void run() {
            long number = a;

            while (!happyNumber(++number)) {
            }

            b = number - a;
        }

        private class OutputData implements Answer {

            public void print() {
                System.out.println("" + b);
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
