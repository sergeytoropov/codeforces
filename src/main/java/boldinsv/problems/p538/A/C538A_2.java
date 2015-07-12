package boldinsv.problems.p538.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C538A_2 {
    public static final String codeforces = "CODEFORCES";
    public static String line;
    public static String answer;

    public static void init() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            line = reader.readLine();
            reader.close();
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    private static enum Actions {LEFT, RIGHT, MIDDLE, YES, EXIT};

    private static boolean left() {
        /*
        for (int index = 0; index < line.length() - codeforces.length(); index++) {
            if (line.substring(index).equals(codeforces)) {
                return true;
            }
        }
        return false;
        */

        return line.substring(line.length() - codeforces.length(), line.length()).equals(codeforces);
    }

    private static boolean right() {
        return line.substring(0, codeforces.length()).equals(codeforces);
    }

    private static boolean middle() {
        for (int index = 0; index < codeforces.length() + 1; index++) {
            String s = line.substring(0, index) + line.substring(index + line.length() - codeforces.length());

            if (s.equals(codeforces)) {
                return true;
            }
        }
        return false;
    }

    public static void run() {
        Actions action = Actions.LEFT;

        answer = "NO";
        for (boolean continued = true; continued; ) {
            switch (action) {
                case LEFT:
                    action = Actions.RIGHT;

                    if (left()) {
                        action = Actions.YES;
                    }
                    break;

                case RIGHT:
                    action = Actions.MIDDLE;

                    if (right()) {
                        action = Actions.YES;
                    }
                    break;

                case MIDDLE:
                    action = Actions.EXIT;

                    if (middle()) {
                        action = Actions.YES;
                    }
                    break;

                case YES:
                    action = Actions.EXIT;

                    answer = "YES";
                    break;

                case EXIT:
                    continued = false;
                    break;
            }
        }
    }

    public static void print() {
        System.out.println(answer);
    }

    public static void main(String[] args) {
        init();
        run();
        print();
    }
}
