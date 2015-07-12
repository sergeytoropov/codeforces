package boldinsv.problems.p494.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class C494A {
    public static final char OPEN_BRACKET  = '(';
    public static final char CLOSE_BRACKET = ')';
    public static final char LATTICE       = '#';

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
        public Deque<Character> message;
        public Deque<Character> newMessage;
        public int[] answer;

        private class InputData implements Data {

            public void init() {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                int amount = 0;

                message = new LinkedList();
                try {
                    char[] chars = reader.readLine().toCharArray();

                    for (char ch: chars) {
                        message.offerLast(ch);

                        if (ch == '#') {
                            amount += 1;
                        }
                    }

                    answer = new int[amount];
                    for (int index = 0; index < answer.length; index++) {
                        answer[index] = 0;
                    }

                } catch (IOException ioe) {
                    System.out.println(ioe);
                }
            }
        }

        // Данный вид обмена ссылок не работает!
        public void swap(Deque<Character> a, Deque<Character> b) {
            Deque<Character> temp;

            temp = a;
            a = b;
            b = temp;
        }

        public enum States {INIT, BOTH, ADD, EXIT};

        public void init() {
            int index = 0;

            for (Character ch: message) {
                switch (ch) {
                    case OPEN_BRACKET:
                        newMessage.offerLast(ch);
                        break;

                    case CLOSE_BRACKET:
                        if (newMessage.peekLast() != null && newMessage.peekLast() == OPEN_BRACKET) {
                            newMessage.pollLast();
                        } else {
                            newMessage.offerLast(ch);
                        }
                        break;

                    case LATTICE:
                        newMessage.offerLast(CLOSE_BRACKET);
                        newMessage.offerLast(ch);

                        answer[index++] += 1;
                        break;
                }
            }
        }

        public void addLattice(int count) {
            for (int i = 0; i < count; i++) {
                newMessage.offerLast(LATTICE);
            }
        }

        public boolean both() {
            boolean founded = false;
            int index = 0;

            for (Character ch: message) {
                switch (ch) {
                    case OPEN_BRACKET:
                        if (index > 0) {
                            addLattice(index);
                            index = 0;
                        }
                        newMessage.offerLast(ch);
                        break;

                    case CLOSE_BRACKET:
                        if (newMessage.peekLast() != null && newMessage.peekLast() == OPEN_BRACKET) {
                            newMessage.pollLast();

                            founded = true;

                        } else {
                            if (index > 0) {
                                addLattice(index);
                                index = 0;
                            }
                            newMessage.offerLast(ch);
                        }
                        break;

                    case LATTICE:
                        if (newMessage.peekLast() != null && newMessage.peekLast() == OPEN_BRACKET) {
                            index += 1;
                        } else {
                            if (index > 0) {
                                addLattice(index);
                                index = 0;
                            }
                            newMessage.offerLast(ch);
                        }
                        break;
                }
            }

            if (index > 0) {
                addLattice(index);
                index = 0;
            }

            return founded;
        }

        public boolean add() {
            boolean added = false;
            int index = 0;

            for (Character ch: message) {
                switch (ch) {
                    case OPEN_BRACKET:
                    case CLOSE_BRACKET:
                        newMessage.offerLast(ch);
                        break;

                    case LATTICE:
                        if (newMessage.peekLast() != null && newMessage.peekLast() == OPEN_BRACKET) {
                            added = true;

                            while (newMessage.peekLast() != null && newMessage.peekLast() == OPEN_BRACKET) {
                                newMessage.pollLast();
                                answer[index] += 1;
                            }
                        }
                        newMessage.offerLast(ch);
                        index += 1;
                        break;
                }
            }

            return added;
        }

        public void run() {
            States state = States.INIT;

            newMessage = new LinkedList<Character>();
            for (boolean isContinue = true; isContinue; ) {

                switch (state) {
                    case INIT:
                        state = States.BOTH;

                        init();
                        break;

                    case BOTH:
                        state = States.ADD;
                        if (both()) {
                            state = States.BOTH;
                        }
                        break;

                    case ADD:
                        state = States.EXIT;
                        if (add()) {
                            state = States.BOTH;
                        }
                        break;

                    case EXIT:
                        isContinue = false;
                        break;
                }

                if (state != States.EXIT) {
                    Deque<Character> temp;

                    temp = message;
                    message = newMessage;
                    newMessage = temp;

                    newMessage.clear();
                }
            }
        }

        private class OutputData implements Answer {

            public void print() {
                for (Character ch: message) {
                    if (ch != '#') {
                        System.out.println("-1");
                        return;
                    }
                }

                for (int index = 0; index < answer.length; index++) {
                    System.out.println("" + answer[index]);
                }
            }
        }

        public Answer answer() {
            return new OutputData();
        }

        public Data data() {
            return new InputData();
        }
    }

    public static void main(String[] args) {
        Algorithm algorithm = new Algorithm();
        algorithm.data().init();
        algorithm.run();
        algorithm.answer().print();
    }
}
