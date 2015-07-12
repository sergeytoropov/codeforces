package boldinsv.problems.p482.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class C482A {

    public static class Init {
        public int n;
        public int k;

        public Init() throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String[] items = reader.readLine().split(" ");
            reader.close();

            n = Integer.parseInt(items[0]);
            k = Integer.parseInt(items[1]);
        }

        public List<Integer> getDigitalRow() {
            List<Integer> digitalRow = new LinkedList<Integer>();
            for (int digit = 1; digit <= n; digit++) {
                digitalRow.add(digit);
            }
            return digitalRow;
        }
    }

    public static class Algorithm {
        private int k;
        private List<Integer> digitalRow;
        private LinkedList<Integer> miniRow;
        private LinkedList<Integer> moveRow;
        private LinkedList<Integer> answerRow;

        private enum Actions {CREATE, MOVE, ADD, STOP};

        public Algorithm(int k, List<Integer> digitalRow) {
            this.k = k;
            this.digitalRow = digitalRow;
            this.miniRow = new LinkedList<Integer>();
            this.moveRow = new LinkedList<Integer>();
            this.answerRow = new LinkedList<Integer>();
        }

        private boolean create() {
            miniRow.clear();

            for (int index = 0; index <= k; index ++) {
                if (digitalRow.size() > 0) {
                    miniRow.add(digitalRow.remove(0));
                }
            }
            return digitalRow.size() > 0;
        }

        private void move() {
            int magic = k;

            moveRow.clear();
            if (miniRow.size() == k + 1) {

                while (miniRow.size() > 1) {
                    Integer first = miniRow.getFirst();
                    Integer last = miniRow.getLast();

                    int delta = first - last;
                    if (delta < 0) {
                        delta *= -1;
                    }

                    moveRow.add(miniRow.removeFirst());
                    if (delta == magic) {
                        miniRow.addFirst(miniRow.removeLast());
                    }
                    magic -= 1;
                }
                moveRow.add(miniRow.removeFirst());
            } else {

                while (miniRow.size() > 0) {
                    moveRow.add(miniRow.removeFirst());
                }
            }
        }

        private void add() {
            while (moveRow.size() > 0) {
                answerRow.add(moveRow.removeFirst());
            }
        }

        public List<Integer> run() {
            answerRow.clear();

            Actions action = Actions.CREATE;
            for (boolean isContinue = true; isContinue;) {
                switch (action) {
                    case CREATE:
                        action = Actions.STOP;
                        if (create()) {
                            action = Actions.MOVE;
                        }
                        break;

                    case MOVE:
                        action = Actions.ADD;
                        move();
                        break;

                    case ADD:
                        action = Actions.CREATE;
                        add();
                        break;

                    case STOP:
                        move();
                        add();
                        isContinue = false;
                        break;
                }
            }
            return answerRow;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();

            for (Integer digit: answerRow) {
                builder.append(digit);
                builder.append(" ");
            }

            return builder.toString().trim();
        }
    }

    public static void main(String[] args) throws IOException {
        Init init = new Init();
        Algorithm algorithm = new Algorithm(init.k, init.getDigitalRow());
        algorithm.run();
        System.out.println(algorithm.toString());
    }
}
