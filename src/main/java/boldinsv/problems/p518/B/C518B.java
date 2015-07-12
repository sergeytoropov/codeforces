package boldinsv.problems.p518.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class C518B {

    public static class Init {
        public String s;
        public String t;

        public Init() throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            s = reader.readLine();
            t = reader.readLine();
            reader.close();
        }

        public Map<Character, Integer> getNewspaper() {
            Map<Character, Integer> newspaper = new HashMap<Character, Integer>();
            char[] letters = t.toCharArray();

            for (int index = 0; index < letters.length; index++) {
                Integer amount = newspaper.get(letters[index]);
                if (amount == null) {
                    amount = 1;
                } else {
                    amount += 1;
                }
                newspaper.put(letters[index], amount);
            }

            return newspaper;
        }
    }

    public static class Algorithm {
        private String word;
        private Map<Character, Integer> newspaper;

        public Algorithm(String word, Map<Character, Integer> newspaper) {
            this.word = word;
            this.newspaper = newspaper;
        }

        public static char invert(char letter) {
            char upperLetter = String.valueOf(letter).toUpperCase().charAt(0);

            if (letter == upperLetter) {
                return String.valueOf(letter).toLowerCase().charAt(0);
            }
            return upperLetter;
        }

        public String run() {
            int ura = 0;
            int opa = 0;
            LinkedList<Character> lettersList = new LinkedList<Character>();

            char[] letters = word.toCharArray();
            for (int index = 0; index < letters.length; index++) {
                lettersList.add(letters[index]);
            }

            ListIterator<Character> iter = lettersList.listIterator();
            while (iter.hasNext()) {
                char letter = iter.next();

                Integer amount = newspaper.get(letter);
                if (amount != null && amount > 0) {
                    ura += 1;
                    newspaper.put(letter, amount - 1);

                    iter.remove();
                }
            }

            iter = lettersList.listIterator();
            while (iter.hasNext()) {
                char letter = iter.next();

                Integer amount = newspaper.get(Algorithm.invert(letter));
                if (amount != null && amount > 0) {
                    opa += 1;
                    newspaper.put(Algorithm.invert(letter), amount - 1);
                }
            }

            return ura + " " + opa;
        }
    }

    public static void main(String[] args) throws IOException {
        Init init = new Init();
        Algorithm algorithm = new Algorithm(init.s, init.getNewspaper());
        System.out.println(algorithm.run());
    }
}
