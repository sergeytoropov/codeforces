package boldinsv.problems.p382.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class C382A {
    public static List<Character> leftPart;
    public static List<Character> rightPart;
    public static char[] symbols;

    static {
        leftPart = new LinkedList<Character>();
        rightPart = new LinkedList<Character>();
    }

    public static void init() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        final int LEFT  = 0;
        final int RIGHT = 1;
        int state = LEFT;

        leftPart.clear();
        rightPart.clear();
        try {
            char[] symbols = reader.readLine().toCharArray();
            for (int index = 0; index < symbols.length; index++) {
                if (symbols[index] == '|') {
                    state = RIGHT;
                    continue;
                }

                switch (state) {
                    case LEFT:
                        leftPart.add(symbols[index]);
                        break;

                    case RIGHT:
                        rightPart.add(symbols[index]);
                        break;
                }
            }

            C382A.symbols = reader.readLine().toCharArray();
            reader.close();
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public static void run() {
        for (int index = 0; index < symbols.length; index++) {
            if (leftPart.size() > rightPart.size()) {
                rightPart.add(symbols[index]);
            } else {
                leftPart.add(symbols[index]);
            }
        }
    }

    public static void print() {
        StringBuilder builder = new StringBuilder();

        if (leftPart.size() != rightPart.size()) {
            builder.append("Impossible");
        } else {
            for (Character ch : leftPart) {
                builder.append(ch);
            }

            builder.append('|');

            for (Character ch : rightPart) {
                builder.append(ch);
            }
        }

        System.out.println(builder.toString());
    }

    public static void main(String[] args) {
        init();
        run();
        print();
    }
}
