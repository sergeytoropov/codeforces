package boldinsv.problems.p509.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C509A {

    public static class Init {
        public int n;

        public Init() throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(reader.readLine());
            reader.close();
        }

        public int[][] createMatrix() {
            int[][] matrix = new int[n][n];

            for (int i = 0; i < n; i++) {
                matrix[0][i] = 1;
                matrix[i][0] = 1;
            }

            for (int i = 1; i < n; i++) {
                for (int j = 1; j < n; j++) {
                    matrix[i][j] = matrix[i - 1][j] + matrix[i][j - 1];
                }
            }
            return matrix;
        }
    }

    public static class Algoritm {
        private int[][] matrix;

        public Algoritm(int[][] matrix) {
            this.matrix = matrix;
        }

        public int run() {
            int last = matrix.length - 1;
            return matrix[last][last];
        }
    }

    public static void main(String[] args) throws IOException {
        Init init = new Init();
        Algoritm algoritm = new Algoritm(init.createMatrix());
        System.out.println("" + algoritm.run());
    }
}
