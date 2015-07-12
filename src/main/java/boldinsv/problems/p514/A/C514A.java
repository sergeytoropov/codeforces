package boldinsv.problems.p514.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C514A {

    public static class Init {
        public int[] digit;

        public Init() throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            char[] array = reader.readLine().toCharArray();
            reader.close();

            digit = new int[array.length];
            for (int index = 0; index < array.length; index++) {
                digit[index] = array[index] - '0';
            }
        }
    }

    public static class Algoritm {
        private final int MAGIC = 9;
        private int[] digit;
        private int[] minimum;
        private enum Position {FIRST, NEXT};

        public Algoritm(int[] digit) {
            this.digit = digit;
            minimum = new int[digit.length];
        }

        public void run() {
            Position position = Position.FIRST;
            for (int index = 0; index < digit.length; index++) {
                int value = digit[index];
                int delta = MAGIC - value;

                minimum[index] = value;
                switch (position) {
                    case FIRST:
                        if (delta < value && delta > 0) {
                            minimum[index] = delta;
                        }
                        position = Position.NEXT;
                        break;

                    case NEXT:
                        if (delta < value) {
                            minimum[index] = delta;
                        }
                        position = Position.NEXT;
                        break;
                }
            }
        }

        @Override
        public String toString() {
            StringBuilder str = new StringBuilder();

            for (int index = 0; index < minimum.length; index++) {
                str.append(minimum[index]);
            }
            return str.toString();
        }
    }

    public static void main(String[] args) throws IOException {
        Init init = new Init();
        Algoritm algoritm = new Algoritm(init.digit);
        algoritm.run();
        System.out.println(algoritm.toString());
    }
}
