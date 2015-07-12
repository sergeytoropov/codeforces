package boldinsv.problems.p518.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class C518A {
    public static char[] latin;
    public static Map<Character, Integer> position;

    public static char[] s;
    public static char[] t;
    public static char[] m;
    public static boolean found;

    static {
        latin = "abcdefghijklmnopqrstuvwxyz".toCharArray();

        position = new HashMap<Character, Integer>();
        for (int index = 0; index < latin.length; index++) {
            position.put(latin[index], index);
        }
    }

    public static void init() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            s = reader.readLine().toCharArray();
            t = reader.readLine().toCharArray();
            reader.close();
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    private enum WAY {SMALLER, BIGGER};

    public static boolean avtomat(WAY way) {
        final int EQUALS  = 0;
        final int MIDDLE  = 1;
        final int SMALLER = 2;
        final int BIGGER  = 3;
        int state = EQUALS;

        found = false;
        m = new char[t.length];
        for (int index = 0; index < t.length; index++) {

            switch (state) {
                case EQUALS:
                    int delta = t[index] - s[index];

                    if (delta == 0) {
                        state = EQUALS;

                        m[index] = t[index];
                    } else if (delta == 1) {

                        switch (way) {
                            case SMALLER:
                                state = SMALLER;

                                m[index] = s[index];
                                break;

                            case BIGGER:
                                state = BIGGER;

                                m[index] = t[index];
                                break;
                        }
                    } else if (delta > 1) {
                        state = MIDDLE;

                        m[index] = latin[position.get(s[index]) + 1];
                        found = true;
                    }
                    break;

                case MIDDLE:
                    state = MIDDLE;

                    m[index] = 'a';
                    break;

                case SMALLER:
                    if (position.get(s[index]) < latin.length - 1) {
                        state = MIDDLE;

                        m[index] = latin[position.get(s[index]) + 1];
                        found = true;
                    } else {
                        state = SMALLER;

                        m[index] = s[index];
                    }
                    break;

                case BIGGER:
                    if (position.get(t[index]) > 0) {
                        state = MIDDLE;

                        m[index] = latin[position.get(t[index]) - 1];
                        found = true;
                    } else {
                        state = BIGGER;

                        m[index] = t[index];
                    }
                    break;
            }
        }
        return found;
    }

    public static void run() {
        found = avtomat(WAY.SMALLER) == true ? true : avtomat(WAY.BIGGER);
    }

    public static void print() {
        String answer = "No such string";
        if (found) {
           answer = String.valueOf(m);
        }
        System.out.println(answer);
    }

    public static void main(String[] args) {
        init();
        run();
        print();
    }
}
