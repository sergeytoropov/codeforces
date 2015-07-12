package boldinsv.problems.p501.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C501A {
    private static String vasya = "Vasya";
    private static String misha = "Misha";
    private static String tie   = "Tie";

    public static class Init {
        public int a;
        public int b;
        public int c;
        public int d;

        public Init() throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String[] items = reader.readLine().split(" ");
            reader.close();

            a = Integer.parseInt(items[0]);
            b = Integer.parseInt(items[1]);
            c = Integer.parseInt(items[2]);
            d = Integer.parseInt(items[3]);
        }
    }

    public static int max(int exp, int minutes) {
        int a = 3 * exp / 10;
        int b = exp - exp / 250 * minutes;

        return a > b ? a : b;
    }

    public static void main(String[] args) throws IOException {
        Init init = new Init();

        int resMisha = max(init.a, init.c);
        int resVasya = max(init.b, init.d);

        String answer = tie;
        if (resMisha > resVasya) {
            answer = misha;
        } else if (resVasya > resMisha) {
            answer = vasya;
        }
        System.out.println(answer);
    }
}
