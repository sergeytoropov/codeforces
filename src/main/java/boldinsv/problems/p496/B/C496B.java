package boldinsv.problems.p496.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C496B {

    public static class Init {
        public int n;
        public String value;

        public Init() throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(reader.readLine());
            value = reader.readLine();
            reader.close();
        }
    }

    public static class Code implements Comparable<Code> {
        public int[] value;

        public Code(String value) {
            char[] digits = value.toCharArray();

            this.value = new int[digits.length];
            for (int index = 0; index < digits.length; index++) {
                this.value[index] = digits[index] - '0';
            }
        }

        public Code plusOne() {
            for (int index = 0; index < value.length; index++) {
                value[index] = (value[index] + 1) % 10;
            }
            return this;
        }

        public Code shiftRight() {
            if (value.length > 0) {
                int last = value[value.length - 1];

                for (int index = value.length - 1; index > 0; index--) {
                    value[index] = value[index - 1];
                }
                value[0] = last;
            }
            return this;
        }

        public int lastDigit() {
            if (value.length > 0) {
                return value[value.length - 1];
            }
            return -1;
        }

        @Override
        public boolean equals(Object obj) {
            Code code = (Code) obj;

            if (value.length != code.value.length) {
                return false;
            }

            for (int index = 0; index < value.length; index++) {
                if (value[index] != code.value[index]) {
                    return false;
                }
            }
            return true;
        }

        public static void copy(int[] a, int[] b) {
            int indexA = a.length - 1;

            for (int indexB = b.length - 1; indexB >= 0; indexB--) {
                b[indexB] = 0;

                if (indexA >= 0) {
                    b[indexB] = a[indexA--];
                }
            }
        }

        public int compareTo(Code o) {
            int maxSize = (value.length > o.value.length) ? value.length : o.value.length;
            int[] a = new int[maxSize];
            int[] b = new int[maxSize];

            copy(value, a);
            copy(o.value, b);

            for (int index = 0; index < maxSize; index++) {
                if (a[index] < b[index]) {
                    return -1;
                } else if (a[index] > b[index]) {
                    return 1;
                }
            }
            return 0;
        }

        @Override
        protected Object clone() {
            StringBuilder builder = new StringBuilder("");

            for (int index = 0; index < value.length; index++) {
                builder.append(value[index]);
            }
            return new Code(builder.toString());
        }
    }

    interface Answer {
        void print();
    }

    interface Printable {
        Answer answer();
    }

    public static class Algorithm implements Printable {
        private Code code;
        private Code min;
        private enum Actions {PLUS, RIGHT};

        public Algorithm(Code code) {
            this.code = code;
            this.min = (Code) code.clone();
        }

        public void run() {
            Code currentCode = (Code) code.clone();
            int amount = 0;

            Actions action = Actions.PLUS;
            while (true) {
                switch (action) {
                    case PLUS:
                        action = Actions.PLUS;

                        currentCode.plusOne();
                        if (currentCode.lastDigit() == 0) {
                            action = Actions.RIGHT;
                        }
                        break;

                    case RIGHT:
                        action = Actions.PLUS;

                        currentCode.shiftRight();
                        if (min.compareTo(currentCode) >= 0) {
                            min = (Code) currentCode.clone();
                        }
                        amount += 1;
                        break;
                }

                if (amount > code.value.length) {
                    break;
                }
            }
        }

        private class A implements Answer {

            public void print() {
                StringBuilder builder = new StringBuilder("");

                for (int index = 0; index < min.value.length; index++) {
                    builder.append(min.value[index]);
                }
                System.out.println(builder.toString());
            }
        }

        public Answer answer() {
            return new A();
        }
    }

    public static void main(String[] args) throws IOException {
        Init init = new Init();
        Algorithm algorithm = new Algorithm(new Code(init.value));
        algorithm.run();
        algorithm.answer().print();
    }
}
