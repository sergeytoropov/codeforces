package boldinsv.problems.p518.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.function.ToIntBiFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
            return t.chars()
                    .mapToObj(ch -> (char) ch)
                    .collect(Collectors.groupingBy(ch -> Character.valueOf(ch),
                                                   Collectors.summingInt(value -> 1)));
        }
    }

    public static class Answer {
        public int ura = 0;
        public int opa = 0;
    }

    public static class Word {
        public final Answer answer;
        public final Character ch;
        public final Map<Character, Integer> newspaper;

        public Word(Character ch, Map<Character, Integer> newspaper, Answer answer) {
            this.ch = ch;
            this.newspaper = newspaper;
            this.answer = answer;
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

            return (letter == upperLetter) ?
                    String.valueOf(letter).toLowerCase().charAt(0) :
                    upperLetter;
        }

        public String run() {
            Answer answer = new Answer();

            LinkedList<Word> lettersList = word.chars()
                    .mapToObj(ch -> new Word((char) ch, newspaper, answer))
                    .collect(Collectors.toCollection(LinkedList::new));

            LinkedList<Word> lettersListTwo = lettersList.stream()
                    .filter(item -> {
                        Integer amount = item.newspaper.get(item.ch);
                        if (amount != null && amount > 0) {
                            item.answer.ura += 1;
                            item.newspaper.put(item.ch, amount - 1);
                            return false;
                        }
                        return true;
                    })
                    .collect(Collectors.toCollection(LinkedList::new));

            lettersListTwo.stream().forEach(item -> {
                Integer amount = item.newspaper.get(Algorithm.invert(item.ch));
                if (amount != null && amount > 0) {
                    item.answer.opa += 1;
                    item.newspaper.put(Algorithm.invert(item.ch), amount - 1);
                }
            });

            return (lettersList == null || lettersList.size() == 0) ?
                    "0 0" :
                    lettersList.get(0).answer.ura + " " + lettersList.get(0).answer.opa;
        }
    }

    public static void main(String[] args) throws IOException {
        Init init = new Init();
        Algorithm algorithm = new Algorithm(init.s, init.getNewspaper());
        System.out.println(algorithm.run());
    }
}
