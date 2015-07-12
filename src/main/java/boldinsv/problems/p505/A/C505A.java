package boldinsv.problems.p505.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

public class C505A {
    private static String NA = "NA";

    public static class Palyndrom implements Iterable {
        private char[] string;

        private char markedChar;
        private int markedPosition;

        public static String create(char[] string, char ch, int pos) {
            int left = 0;
            int right = string.length - 1;
            char[] newString = new char[string.length + 2];
            int newLeft = 0;
            int newRight = newString.length - 1;

            while (left <= right) {
                if (newLeft == pos || newRight == pos + 1) {
                    newString[newLeft++] = ch;
                    newString[newRight--] = ch;
                }

                newString[newLeft++] = string[left++];
                newString[newRight--] = string[right--];
            }
            return String.valueOf(newString);
        }

        public static String addChar(char[] string) {
            int left = 0;
            int right = string.length - 1;
            char[] newString = new char[string.length + 1];
            int newLeft = 0;
            int newRight = newString.length - 1;

            while (left <= right) {
                if (right - left == 1) {
                    newString[newLeft++] = string[left++];
                    newString[newRight--] = string[right--];
                    newString[newLeft] = 'y';
                } else if (left == right) {
                    newString[newLeft++] = string[left];
                    newString[newLeft] = string[left++];
                } else {
                    newString[newLeft++] = string[left++];
                    newString[newRight--] = string[right--];
                }
            }

            return String.valueOf(newString);
        }

        public Palyndrom(char[] string) {
            this.string = string;
        }

        public Palyndrom(String string) {
            this.string = string.toCharArray();
        }

        public boolean hasIt() {
            int left = 0;
            int right = string.length - 1;

            while (left <= right) {
                if (string[left++] != string[right--]) {
                    return false;
                }
            }
            return string.length > 0;
        }

        private class PalyndromIterator implements Iterator {
            private int position = 0;

            public PalyndromIterator() {
            }

            public boolean hasNext() {
                if (string.length > 1) {
                    return position < string.length;
                }
                return false;
            }

            public Object next() {
                char[] p = new char[string.length - 1];
                int i = 0;

                for (int index = 0; index < string.length; index++) {
                    if (position == index) {
                        markedChar = string[position];
                        markedPosition = position;

                        continue;
                    }
                    p[i++] = string[index];
                }
                position++;

                return new Palyndrom(p);
            }

            public void remove() {

            }
        }

        public Iterator iterator() {
            return new PalyndromIterator();
        }

        public String getString() {
            return String.valueOf(string);
        }

        public char getMarkedChar() {
            return markedChar;
        }

        public int getMarkedPosition() {
            return markedPosition;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Palyndrom palyndrom = new Palyndrom(reader.readLine());
        reader.close();

        if (palyndrom.hasIt()) {
            System.out.println(Palyndrom.addChar(palyndrom.getString().toCharArray()));
        } else {
            Palyndrom miniPalyndrom = null;
            Iterator<Palyndrom> iter = palyndrom.iterator();
            while (iter.hasNext()) {
                Palyndrom p = iter.next();

                if (p.hasIt()) {
                    miniPalyndrom = p;
                    break;
                }
            }

            if (miniPalyndrom == null) {
                System.out.println(NA);
            } else {
                System.out.println(Palyndrom.create(miniPalyndrom.getString().toCharArray(), palyndrom.getMarkedChar(), palyndrom.getMarkedPosition()));
            }
        }
    }
}
