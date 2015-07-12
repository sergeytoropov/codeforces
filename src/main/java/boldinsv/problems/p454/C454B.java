package boldinsv.problems.p454;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class C454B {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        String[] items = reader.readLine().split(" ");

        int[] digits = new int[n];
        int idx = 0;
        for (String item: items) {
            digits[idx] = Integer.parseInt(item);
            idx += 1;
        }

        final int stateA = 1;
        final int stateB = 2;
        final int stateC = 3;
        int state = stateA;

        boolean isStoped = false;
        int position = 0;
        for (int i = 1; i < digits.length; i++) {
            switch (state) {
                case stateA:
                    if (digits[i - 1] > digits[i]) {
                        position = i;
                        state = stateB;
                    }
                    break;

                case stateB:
                    if (digits[i - 1] > digits[i]) {
                        state = stateC;
                    }
                    break;

                case stateC:
                    isStoped = true;
                    break;
            }
            if (isStoped) {
                break;
            }
        }

        int answer = -1;
        switch (state) {
            case stateA:
                answer = 0;
                break;

            case stateB:
            	answer = -1;
                if (digits[0] >= digits[digits.length - 1]) {
                    answer = digits.length - position;
                }
                break;

            case stateC:
                answer = -1;
                break;
        }
        System.out.println("" + answer);
    }
}
