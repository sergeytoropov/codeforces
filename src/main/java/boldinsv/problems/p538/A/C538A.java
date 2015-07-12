package boldinsv.problems.p538.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C538A {
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

    public static void run() {
        answer = "NO";

        if (line.length() >= codeforces.length()) {
            exit:
            for (int index = 0; index < codeforces.length() + 1; index++) {
                String s = line.substring(0, index) + line.substring(index + line.length() - codeforces.length());

                if (s.equals(codeforces)) {
                    answer = "YES";

                    break exit;
                }
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
