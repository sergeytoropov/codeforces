package boldinsv.problems.p510.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C510A {
    private enum Positions {TOP, RIGHT, BOTTOM, LEFT};

    private static void print(Positions position, int length) {

        switch (position) {
            case TOP:
            case BOTTOM:
                for (int idx = 0; idx < length; idx++) {
                    System.out.print('#');
                }
                break;

            case RIGHT:
                for (int idx = 0; idx < length - 1; idx++) {
                    System.out.print('.');
                }
                System.out.print('#');
                break;

            case LEFT:
                System.out.print('#');
                for (int idx = 1; idx < length; idx++) {
                    System.out.print('.');
                }
                break;
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] params = reader.readLine().split(" ");
        reader.close();

        int n = Integer.parseInt(params[0]);
        int m = Integer.parseInt(params[1]);

        Positions position = Positions.TOP;
        for (int idx = 0; idx < n; idx++) {
            switch (position) {
                case TOP:
                    print(Positions.TOP, m);

                    position = Positions.RIGHT;
                    break;

                case RIGHT:
                    print(Positions.RIGHT, m);

                    position = Positions.BOTTOM;
                    break;

                case BOTTOM:
                    print(Positions.BOTTOM, m);

                    position = Positions.LEFT;
                    break;

                case LEFT:
                    print(Positions.LEFT, m);

                    position = Positions.TOP;
                    break;
            }
        }
    }
}
