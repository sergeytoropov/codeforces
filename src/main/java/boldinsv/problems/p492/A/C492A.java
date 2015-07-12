package boldinsv.problems.p492.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C492A {

    public static class Init {
        public int n;

        public Init() throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(reader.readLine());
            reader.close();
        }
    }

    public static class Algorithm {
        private int n;

        public Algorithm(int n) {
            this.n = n;
        }

        public int run() {
            int sumRow = 0;
            int amount = 0;
            int height = 0;

            while (true) {
                height += 1;
                sumRow += height;

                amount += sumRow;
                if (amount > n) {
                    break;
                }
            }
            return height - 1;
        }
    }

    public static void main(String[] args) throws IOException {
        Init init = new Init();
        Algorithm algorithm = new Algorithm(init.n);
        System.out.println("" + algorithm.run());
    }
}
