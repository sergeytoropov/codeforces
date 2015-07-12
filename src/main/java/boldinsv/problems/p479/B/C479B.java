package boldinsv.problems.p479.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class C479B {

    public static class Position {
        public int h;
        public int index;

        public Position(int h, int index) {
            this.h = h;
            this.index = index;
        }

        @Override
        public boolean equals(Object obj) {
            Position current = (Position) obj;

            if (current != null) {
                if (h == current.h && index == current.index) {
                    return true;
                }
            }

            return false;
        }

        @Override
        public String toString() {
            return "Position{" + "h=" + h + ", index=" + index + '}';
        }
    }

    public static class Record {
        public int from;
        public int to;
        public int step;
        public int max;
        public int min;

        public Record(int from, int to, int step, int max, int min) {
            this.from = from;
            this.to = to;
            this.step = step;
            this.max = max;
            this.min = min;
        }

        @Override
        public String toString() {
            return "Answer{" + "from=" + from + ", to=" + to + ", step=" + step + ", max=" + max + ", min=" + min + '}';
        }
    }

    interface Data {
        void init();
    }

    interface Readable {
        Data data();
    }

    interface Answer {
        void print();
    }

    interface Printable {
        Answer answer();
    }

    public static class Algorithm implements Readable, Printable {
        public int amountSteps;
        public boolean[][] board;
        public ArrayList<Record> answer = new ArrayList<Record>();
        private enum States {INIT, STEP, TOP, MAX, BOTTOM, MIN, SWAP, ANSWER, EXIT};

        public Position top(Position currentTop) {
            int currentIndex = currentTop.index;

            for (int h = currentTop.h; h < board.length; h++) {
                for (int index = currentIndex; index < board[h].length; index++) {

                    if (board[h][index]) {
                        return new Position(h, index);
                    }
                }
                currentIndex = 0;
            }

            return null;
        }

        public int max(Position currentTop) {
            Position next = top(new Position(currentTop.h, currentTop.index + 1));

            if (next == null) {
                next = currentTop;
            }

            return board.length - next.h;
        }

        public Position bottom(Position currentBottom) {
            int currentIndex = currentBottom.index;

            for (int h = currentBottom.h; h >= 0; h--) {
                for (int index = currentIndex; index < board[h].length; index++) {

                    if (board[h][index] == false) {
                        return new Position(h, index);
                    }
                }
                currentIndex = 0;
            }
            return null;
        }

        public int min(Position currentBottom) {
            Position next = bottom(new Position(currentBottom.h, currentBottom.index + 1));

            if (next == null) {
                next = currentBottom;
            }

            int min = board.length - next.h - 1;
            return min > 0 ? min : board.length;
        }

        public boolean swap(Position top, Position bottom) {

            if (top != null && bottom != null && top.h < bottom.h) {
                board[top.h][top.index] = false;
                board[bottom.h][bottom.index] = true;

                return true;
            }
            return false;
        }

        public void run() {
            Position top = null;
            Position bottom = null;
            int max = 0;
            int min = 0;
            int currentStep = 0;
            States state = States.INIT;

            answer.clear();
            for (boolean isContinue = true; isContinue; ) {

                switch (state) {
                    case INIT:
                        state = States.STEP;

                        top = new Position(0, 0);
                        bottom = new Position(board.length - 1, 0);

                        max = max(new Position(0, -1));
                        min = min(new Position(board.length - 1, -1));
                        break;

                    case STEP:
                        state = States.EXIT;

                        if (currentStep++ < amountSteps) {
                            state = States.TOP;
                        }
                        break;

                    case TOP:
                        state = States.MAX;

                        if ((top = top(top)) == null) {
                            state = States.BOTTOM;
                        }
                        break;

                    case MAX:
                        state = States.BOTTOM;

                        max = max(top);
                        break;

                    case BOTTOM:
                        state = States.MIN;

                        if ((bottom = bottom(bottom)) == null) {
                            state = States.SWAP;
                        }
                        break;

                    case MIN:
                        state = States.SWAP;

                        min = min(bottom);
                        break;

                    case SWAP:
                        state = States.ANSWER;

                        if (swap(top, bottom) == false) {
                            state = States.EXIT;
                        }
                        break;

                    case ANSWER:
                        state = States.STEP;

                        answer.add(new Record(top.index + 1, bottom.index + 1, currentStep, max, min));
                        break;

                    case EXIT:
                        isContinue = false;
                        break;
                }
            }
        }

        private class InputData implements Data {

            public int max(int[] array) {
                int max = 0;

                for (int index = 0; index < array.length; index++) {
                    if (max < array[index]) {
                        max = array[index];
                    }
                }
                return max;
            }

            public void init() {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String[] items;
                int[] heights;

                try {
                    items = reader.readLine().split(" ");

                    int n = Integer.parseInt(items[0]);
                    amountSteps = Integer.parseInt(items[1]);

                    items = reader.readLine().split(" ");

                    heights = new int[items.length];
                    for (int index = 0; index < items.length; index++) {
                        heights[index] = Integer.parseInt(items[index]);
                    }

                    board = new boolean[max(heights)][heights.length];

                    for (int h = board.length - 1; h >= 0; h--) {
                        for (int index = 0; index < heights.length; index++) {
                            board[h][index] = false;

                            if (h >= (board.length - heights[index])) {
                                board[h][index] = true;
                            }
                        }
                    }

                } catch (IOException ioe) {
                    System.out.println(ioe);
                }
            }
        }

        private class OutputData implements Answer {

            public void print() {
                int maxNotStable = board.length + 1;
                int lastStep = 0;

                for (Record r: answer) {
                    int currentNotStable = r.max - r.min;

                    if (maxNotStable > currentNotStable) {
                        maxNotStable = currentNotStable;
                        lastStep = r.step;
                    }
                }

                if (lastStep == 0) {
                    int max = max(new Position(0, -1));
                    int min = min(new Position(board.length - 1, -1));

                    System.out.println((max - min) + " 0");
                } else {
                    System.out.println(maxNotStable + " " + lastStep);

                    for (Record r: answer) {
                        if (r.step > lastStep) {
                            break;
                        }
                        System.out.println(r.from + " " + r.to);
                    }
                }
            }
        }

        public Data data() {
            return new InputData();
        }

        public Answer answer() {
            return new OutputData();
        }
    }

    public static void main(String[] args) {
        Algorithm algorithm = new Algorithm();

        algorithm.data().init();
        algorithm.run();
        algorithm.answer().print();
    }
}
