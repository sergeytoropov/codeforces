package boldinsv.problems.p508.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C508A {
    public static final int WHITE = 0;
    public static final int BLACK = 1;

    public static class Init {
        public int n;
        public int m;
        public int k;
        public int[][] steps;

        public Init() throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String[] params;

            params = reader.readLine().split(" ");
            n = Integer.parseInt(params[0]);
            m = Integer.parseInt(params[1]);
            k = Integer.parseInt(params[2]);

            steps = new int[k][2];
            for (int idx = 0; idx < k; idx++) {
                params = reader.readLine().split(" ");
                steps[idx][0] = Integer.parseInt(params[0]);
                steps[idx][1] = Integer.parseInt(params[1]);
            }

            reader.close();
        }

        public int[][] getGameDesk() {
            int[][] gameDesk = new int[n][m];

            for (int x = 0; x < n; x++) {
                for (int y = 0; y < m; y++) {
                    gameDesk[x][y] = WHITE;
                }
            }

            return gameDesk;
        }
    }

    public static class Algoritm {
        private int[][] gameDesk;
        private int maxX;
        private int maxY;

        private int[][] steps;

        public Algoritm(int[][] gameDesk, int[][] steps) {
            this.gameDesk = gameDesk;
            this.maxX = gameDesk.length;
            this.maxY = gameDesk[0].length;

            this.steps = steps;
        }

        private boolean isBlack(int x, int y) {
            if (gameDesk[x][y] == BLACK) {
                return true;
            }
            return false;
        }

        public boolean topLeft2x2(int x, int y) {
            if (x - 1 >= 0 && y - 1 >= 0) {
                if (isBlack(x, y) && isBlack(x, y - 1) && isBlack(x - 1, y - 1) && isBlack(x - 1, y)) {
                    return true;
                }
            }
            return false;
        }

        public boolean bottomLeft2x2(int x, int y) {
            if (x + 1 < maxX && y - 1 >= 0) {
                if (isBlack(x, y) && isBlack(x, y - 1) && isBlack(x + 1, y - 1) && isBlack(x + 1, y)) {
                    return true;
                }
            }
            return false;
        }

        public boolean topRight2x2(int x, int y) {
            if (x - 1 >= 0 && y + 1 < maxY) {
                if (isBlack(x, y) && isBlack(x, y + 1) && isBlack(x - 1, y + 1) && isBlack(x - 1, y)) {
                    return true;
                }
            }
            return false;
        }

        public boolean bottomRight2x2(int x, int y) {
            if (x + 1 < maxX && y + 1 < maxY) {
                if (isBlack(x, y) && isBlack(x, y + 1) && isBlack(x + 1, y + 1) && isBlack(x + 1, y)) {
                    return true;
                }
            }
            return false;
        }

        public boolean is2x2(int x, int y) {
            if (topLeft2x2(x, y) || bottomLeft2x2(x, y) || topRight2x2(x, y) || bottomRight2x2(x, y)) {
                return true;
            }
            return false;
        }

        public int solve() {
            int counter = 0;
            for (int index = 0; index < steps.length; index++) {
                int x = steps[index][0] - 1;
                int y = steps[index][1] - 1;

                gameDesk[x][y] = BLACK;
                counter++;

                if (is2x2(x, y)) {
                   return counter;
                }
            }
            return 0;
        }

    }

    public static void main(String[] args) throws IOException {
        Init init = new Init();
        Algoritm algoritm = new Algoritm(init.getGameDesk(), init.steps);
        System.out.println("" + algoritm.solve());
    }
}
