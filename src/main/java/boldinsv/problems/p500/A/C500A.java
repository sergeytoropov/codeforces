package boldinsv.problems.p500.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C500A {
    private final static String YES = "YES";
    private final static String NO = "NO";

    public static class Init {
        public int n;
        public int t;
        public int[] portals;

        public Init() throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String[] items;

            items = reader.readLine().split(" ");
            n = Integer.parseInt(items[0]);
            t = Integer.parseInt(items[1]);

            items = reader.readLine().split(" ");
            portals = new int[n - 1];
            for (int i = 0; i < n - 1; i++) {
                portals[i] = Integer.parseInt(items[i]);
            }
            reader.close();
        }
    }

    public static class Algoritm {
        private int n;
        private int t;
        private int[] portals;

        private enum Positions {CURRENT, NEXT, EXIT};

        public Algoritm(int n, int t, int[] portals) {
            this.n = n;
            this.t = t;
            this.portals = portals;
        }

        public String run() {
            Positions position = Positions.CURRENT;
            int currentPortal = 1;
            String answer = NO;

            boolean isContinued = true;
            while (isContinued) {
                switch (position) {
                    case CURRENT:
                        position = Positions.EXIT;

                        if (currentPortal == t) {
                            answer = YES;
                        }

                        if (currentPortal < t) {
                            position = Positions.NEXT;
                        }
                        break;

                    case NEXT:
                        position = Positions.CURRENT;

                        currentPortal += portals[currentPortal - 1];
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
        Algoritm algoritm = new Algoritm(init.n, init.t, init.portals);
        System.out.println("" + algoritm.run());
    }
}
