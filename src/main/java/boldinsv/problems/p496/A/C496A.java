package boldinsv.problems.p496.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C496A {

    public static class Init {
        public int n;
        public int[] heights;

        public Init() throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String[] items;

            n = Integer.parseInt(reader.readLine());
            items = reader.readLine().split(" ");

            reader.close();

            heights = new int[items.length];
            for (int index = 0; index < items.length; index++) {
                heights[index] = Integer.parseInt(items[index]);
            }
        }
    }

    public static class Algoritm {
        private int[] heights;

        public Algoritm(int[] heights) {
            this.heights = heights;
        }

        public int maxD(int passIndex) {
            int d;
            int maxD = 0;

            for (int index = 1; index < heights.length; index++) {
                if (index == passIndex) {
                    continue;
                } else if (index - 1 == passIndex) {
                    d = heights[index] - heights[index - 2];
                } else {
                    d = heights[index] - heights[index - 1];
                }

                if (maxD < d) {
                    maxD = d;
                }
            }
            return maxD;
        }

        public int run() {
            int minMaxD = maxD(1);

            for (int index = 1; index < heights.length - 1; index++) {
                int maxD = maxD(index);

                if (minMaxD > maxD) {
                    minMaxD = maxD;
                }
            }
            return minMaxD;
        }
    }

    public static void main(String[] args) throws IOException {
        Init init = new Init();
        Algoritm algoritm = new Algoritm(init.heights);
        System.out.println("" + algoritm.run());
    }
}
