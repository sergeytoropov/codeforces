package boldinsv.problems.p540.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C540A {
    public static int n;
    public static int[] code;
    public static int[] answerCode;
    public static int count;

    public static void init() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            try {
                n = Integer.parseInt(reader.readLine());

                char[] items;

                items = reader.readLine().toCharArray();
                code = new int[items.length];
                for (int index = 0; index < items.length; index++) {
                    code[index] = items[index] - '0';
                }

                items = reader.readLine().toCharArray();
                answerCode = new int[items.length];
                for (int index = 0; index < items.length; index++) {
                    answerCode[index] = items[index]- '0';
                }
            } finally {
                reader.close();
            }
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public static void run() {
        int min1;
        int min2;

        count = 0;
        for (int index = 0; index < code.length; index++) {
            if (code[index] > answerCode[index]) {

                min1 = code[index] - answerCode[index];
                min2 = (answerCode[index] + 10) - code[index];
            } else if (code[index] < answerCode[index]) {

                min1 = (code[index] + 10) - answerCode[index];
                min2 = answerCode[index] - code[index];
            } else {

                min1 = 0;
                min2 = 0;
            }

            count += ((min1 > min2) ? min2 : min1);
        }
    }

    public static void print() {
        System.out.println("" + count);
    }

    public static void main(String[] args) {
        init();
        run();
        print();
    }
}
