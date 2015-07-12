package boldinsv.problems.p509.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C509B {
    public static int n;
    public static int k;
    public static int[] ai;

    public static class Ball {
        public boolean invisiable;
        public int color;
        public int amount;

        public Ball() {
            this.invisiable = true;
            this.color = 0;
            this.amount = 0;
        }

        public Ball(boolean invisiable) {
            this.invisiable = invisiable;
            this.color = 0;
            this.amount = 0;
        }

        public Ball(boolean invisiable, int color, int amount) {
            this.invisiable = invisiable;
            this.color = color;
            this.amount = amount;
        }

        @Override
        public boolean equals(Object obj) {
            Ball ball = (Ball) obj;

            return ball != null ? (invisiable == ball.invisiable && color == ball.color && amount == ball.amount) : false;
        }

        @Override
        public String toString() {
            return "Ball{" +
                    "invisiable=" + invisiable +
                    ", color=" + color +
                    ", amount=" + amount +
                    '}';
        }
    }

    public static Ball[][] board;
    public static boolean result;

    public static void init() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] items;

        try {
            items = reader.readLine().split(" ");
            n = Integer.parseInt(items[0]);
            k = Integer.parseInt(items[1]);

            items = reader.readLine().split(" ");
            ai = new int[items.length];
            for (int index = 0; index < items.length; index++) {
                ai[index] = Integer.parseInt(items[index]);
            }
            reader.close();
        }
        catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public static int max() {
        int max = ai[0];

        for (int index = 1; index < ai.length; index++) {
            if (max < ai[index]) {
                max = ai[index];
            }
        }
        return max;
    }

    public static void prepare() {
        int max = C509B.max();

        board = new Ball[ai.length][max];

        for (int index = 0; index < board.length; index++) {
            for (int height = 0; height < max; height++) {
                board[index][height] = new Ball();

                if (height < ai[index]) {
                    board[index][height].invisiable = false;

                    if (height == 0) {
                        board[index][height].color = 1;
                        board[index][height].amount = 1;
                    }
                }
            }
        }
    }

    public static void draw(int height, int color) {
        for (int index = 0; index < board.length; index++) {
            Ball currBall = board[index][height];
            Ball prevBall = board[index][height - 1];

            currBall.color = color;

            if (currBall.color == prevBall.color) {
                // Цвета шаров совпадают

                if (currBall.invisiable) {
                    // Скрытый шар

                    currBall.amount = prevBall.amount;
                } else {
                    // Видимый шар
                    if (prevBall.invisiable) {
                        currBall.amount = 1;
                    } else {
                        currBall.amount = prevBall.amount + 1;
                    }
                }

            } else {
                // Новый цвет

                if (currBall.invisiable) {
                    // Скрытый шар
                    currBall.amount = 0;
                } else {
                    // Видимый шар
                    currBall.amount = 1;
                }
            }
        }
    }

    public static int delta(int height) {
        int min = board[0][height].amount;
        int max = board[0][height].amount;

        for (int index = 1; index < board.length; index++) {
            Ball ball = board[index][height];

            if (min > ball.amount) {
                min = ball.amount;
            }

            if (max < ball.amount) {
                max = ball.amount;
            }
        }
        return max - min;
    }

    public enum State {INIT, EXIT, DRAW, NEXT_HEIGHT, NEXT_COLOR, DELTA};

    public static void run() {
        int color = 1;
        int height = 0;

        result = true;
        State state = State.INIT;
        for (boolean continued = true; continued; ) {

            switch (state) {
                case INIT:
                    state = State.NEXT_HEIGHT;

                    prepare();
                    break;

                case EXIT:
                    continued = false;
                    break;

                case DRAW:
                    state = State.DELTA;

                    draw(height, color);
                    break;

                case NEXT_HEIGHT:
                    state = State.DRAW;

                    if (++height >= board[0].length) {
                        state = State.EXIT;
                    }
                    break;

                case NEXT_COLOR:
                    state = State.DRAW;

                    if (++color > k) {
                        state = State.EXIT;

                        result = false;
                    }
                    break;

                case DELTA:
                    state = State.NEXT_HEIGHT;

                    if (delta(height) > 1) {
                        state = State.NEXT_COLOR;
                    }
                    break;
            }
        }
    }

    public static void print() {
        if (result) {
            System.out.println("YES");

            for (int index = 0; index < board.length; index++) {
                String line = "";

                for (int height = 0; height < board[index].length; height++) {
                    Ball ball = board[index][height];

                    if (!ball.invisiable) {
                        line += " " + ball.color;
                    }
                }

                System.out.println(line.trim());
            }
        } else {
            System.out.println("NO");
        }
    }

    public static void main(String[] args) {
        init();
        run();
        print();
    }
}
