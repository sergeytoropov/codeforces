package boldinsv.problems.p499.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C499A {
    public static int BEGIN = 0;
    public static int END = 1;

    public static class Init {
        public int n;
        public int x;
        public int[][] lr;

        public Init() throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String[] items;

            items = reader.readLine().split(" ");
            n = Integer.parseInt(items[0]);
            x = Integer.parseInt(items[1]);

            lr = new int[n][2];
            for (int index = 0; index < n; index++) {
                items = reader.readLine().split(" ");

                lr[index][BEGIN] = Integer.parseInt(items[0]);
                lr[index][END] = Integer.parseInt(items[1]);
            }
            reader.close();
        }
    }

    public static class Algoritm {
        private int skip;
        private int[][] moments;

        private enum Actions {MOMENT, SEE, NEXT, EXIT};

        public Algoritm(int skip, int[][] moments) {
            this.skip = skip;
            this.moments = moments;
        }

        public int run() {
            int answer = 0;
            int index  = 0;
            int time   = 1;
            int currentMoment = 0;

            Actions action = Actions.MOMENT;
            for (boolean isContinued = true; isContinued;) {

                switch (action) {
                    case MOMENT:
                        action = Actions.EXIT;

                        if (index < moments.length) {
                            currentMoment = index;
                            index++;

                            action = Actions.NEXT;
                        }
                        break;

                    case SEE:
                        time++;
                        answer++;

                        if (time <= moments[currentMoment][END]) {
                            action = Actions.SEE;
                        } else {
                            action = Actions.MOMENT;
                        }
                        break;

                    case NEXT:
                        if (moments[currentMoment][BEGIN] - skip >= time) {
                            time += skip;

                            action = Actions.NEXT;
                        } else {
                            action = Actions.SEE;
                        }
                        break;

                    case EXIT:
                        isContinued = false;
                        break;
                }
            }
            return answer;
        }
    }

    public static void main(String[] args) throws IOException {
        Init init = new Init();
        Algoritm algoritm = new Algoritm(init.x, init.lr);
        System.out.println("" + algoritm.run());
    }
}
