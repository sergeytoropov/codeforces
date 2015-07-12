package boldinsv.problems.p81;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Created by sergejboldin on 16.09.14.
 */
public class C81B {
    final static private int stateExit       = 0;
    final static private int stateNumber     = 1;
    final static private int stateComma      = 2;
    final static private int stateThreePoint = 3;
    final static private int stateGap        = 4;
    private static int state;

    private static int getState(char ch) {

        if ("0123456789".indexOf(ch) >= 0) {
            return stateNumber;
        }
        if (".".indexOf(ch) == 0) {
            return stateThreePoint;
        }
        if (",".indexOf(ch) == 0) {
            return stateComma;
        }
        if (" ".indexOf(ch) == 0) {
            return stateGap;
        }

        return -1;
    }


    private static class Tape {
        private char[] chars;
        private int position;

        public Tape(char[] chars) {
            this.chars = chars;
            this.position = -1;
            if (chars.length > 0) {
                this.position = 0;
            }
        }

        public boolean isNextChar() {
            if ((this.position + 1) < chars.length)  {
                return true;
            }
            return false;
        }

        public char getNextChar() {
            if ((this.position + 1) < chars.length) {
                this.position += 1;
            }
            return chars[this.position];
        }

        public char getChar() {
            return chars[this.position];
        }
    }

    private static class Item {
        public String value;
        public int type;

        public Item(String value, int type) {
            this.value = value;
            this.type = type;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Tape tape = new Tape(reader.readLine().toCharArray());
        reader.close();

        ArrayList<Item> list = new ArrayList<Item>();

        String item = "";
        char ch;

        ch = tape.getChar();
        state = getState(ch);

        boolean isContinued = true;
        while (isContinued) {

            switch (state) {
                case stateExit:
                    isContinued = false;
                    break;

                case stateNumber:
                    item = "" + ch;
                    while (true) {
                        if (tape.isNextChar()) {
                            ch = tape.getNextChar();
                            state = getState(ch);
                            if (state == stateNumber) {
                                item += ch;
                            } else {
                                list.add(new Item(item, stateNumber));
                                break;
                            }
                        } else {
                            state = stateExit;
                            list.add(new Item(item, stateNumber));
                            break;
                        }
                    }

                    break;

                case stateComma:
                    item = "" + ch;
                    list.add(new Item(item, stateComma));

                    if (tape.isNextChar()) {
                        ch = tape.getNextChar();
                        state = getState(ch);
                    } else {
                        state = stateExit;
                    }
                    break;

                case stateThreePoint:
                    item = "" + ch;
                    item += tape.getNextChar();
                    item += tape.getNextChar();
                    list.add(new Item(item, stateThreePoint));

                    if (tape.isNextChar()) {
                        ch = tape.getNextChar();
                        state = getState(ch);
                    } else {
                        state = stateExit;
                    }
                    break;

                case stateGap:
                    while (true) {
                        if (tape.isNextChar()) {
                            ch = tape.getNextChar();
                            state = getState(ch);
                            if (state == stateGap) {
                                continue;
                            } else {
                                break;
                            }
                        } else {
                            state = stateExit;
                            break;
                        }
                    }
                    break;
            }
        }

        ListIterator<Item> iter = list.listIterator();
        boolean previous;
        boolean next;
        Item it;
        Item prevItem = null;
        String answer = "";
        while (iter.hasNext()) {
            previous = iter.hasPrevious();
            it = iter.next();
            next = iter.hasNext();

            switch (it.type) {
                case stateNumber:
                    if (prevItem != null && prevItem.type == stateNumber) {
                        answer += " ";
                    }
                    answer += it.value;
                    break;

                case stateComma:
                    answer += it.value;
                    if (next) {
                        answer += " ";
                    }
                    break;

                case stateThreePoint:
                    if (previous && prevItem.type != stateComma) {
                        answer += " ";
                    }
                    answer += it.value;
                    break;
            }
            prevItem = it;
        }
        System.out.println(answer);
    }
}
