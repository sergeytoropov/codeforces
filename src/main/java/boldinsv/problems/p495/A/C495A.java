package boldinsv.problems.p495.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C495A {
    //
    // Представление числа в виде массива. 1 черта светиться, 0 черта выключена.
    //
    //    __1__
    //   |     |
    //  0|     |2
    //   |__3__|
    //   |     |
    //  4|     |6
    //   |__5__|
    //

    private static final int[] ZERO  = new int[] { 1, 1, 1, 0, 1, 1, 1 };
    private static final int[] ONE   = new int[] { 0, 0, 1, 0, 0, 0, 1 };
    private static final int[] TWO   = new int[] { 0, 1, 1, 1, 1, 1, 0 };
    private static final int[] THREE = new int[] { 0, 1, 1, 1, 0, 1, 1 };
    private static final int[] FOUR  = new int[] { 1, 0, 1, 1, 0, 0, 1 };
    private static final int[] FIVE  = new int[] { 1, 1, 0, 1, 0, 1, 1 };
    private static final int[] SIX   = new int[] { 1, 1, 0, 1, 1, 1, 1 };
    private static final int[] SEVEN = new int[] { 0, 1, 1, 0, 0, 0, 1 };
    private static final int[] EIGHT = new int[] { 1, 1, 1, 1, 1, 1, 1 };
    private static final int[] NINE  = new int[] { 1, 1, 1, 1, 0, 1, 1 };

    private static final int[][] digits = new int[][] { ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE };

    public static boolean equals(int a, int b) {
        if (a < 0 || a > 9 || b < 0 || b > 9) {
            return false;
        }

        for (int index = 0; index < digits[a].length; index++) {
            int line = digits[a][index];

            if (line == 1 && line != digits[b][index]) {
                return false;
            }
        }
        return true;
    }

    public static class Init {
        public int[] n;

        public Init() throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            char[] digits = reader.readLine().toCharArray();
            reader.close();

            n = new int[digits.length];
            for (int index = 0; index < digits.length; index++) {
                n[index] = digits[index] - '0';
            }
        }
    }

    public static class Algorithm {
        private int[] digit;

        public Algorithm(int[] digit) {
            this.digit = digit;
        }

        private boolean n1(int b) {
            return C495A.equals(digit[0], b);
        }

        private boolean n2(int b) {
            return C495A.equals(digit[1], b);
        }

        public int run() {
            int amount = 0;

            for (int i = 0; i < 10; i++) {
                if (n1(i)) {

                    for (int j = 0; j < 10; j++) {
                        if (n2(j)) {
                            amount += 1;
                        }
                    }
                }
            }
            return amount;
        }
    }

    public static void main(String[] args) throws IOException {
        Init init = new Init();
        Algorithm algorithm = new Algorithm(init.n);
        System.out.println("" + algorithm.run());
    }
}
