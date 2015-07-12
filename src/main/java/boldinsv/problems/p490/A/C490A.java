package boldinsv.problems.p490.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;

public class C490A {

    public static int min(int a, int b, int c) {
        int min = a;

        if (b <= a && b <= c) {
            min = b;
        } else if (c < a && c < b) {
            min = c;
        }
        return min;
    }

    public static class Init {
        public int n;
        public int[] persons;

        public Init() throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(reader.readLine());
            persons = new int[n];

            int index = 0;
            for (String item: reader.readLine().split(" ")) {
                persons[index++] = Integer.parseInt(item) - 1;
            }
        }
    }

    interface Printable {
        Answer answer();
    }

    interface Answer {
        void print();
    }

    public static class Algorithm implements Printable {
        private int[] persons;
        private LinkedList<Integer>[] groups;

        public Algorithm(int[] persons) {
            this.persons = persons;

            groups = new LinkedList[3];
            for (int index = 0; index < groups.length; index++) {
                groups[index] = new LinkedList<Integer>();
            }
        }

        public void run() {
            for (int person = 0; person < persons.length; person++) {
                groups[persons[person]].add(person + 1);
            }
        }

        public class A implements Answer {
            public void print() {
                int min = min(groups[0].size(), groups[1].size(), groups[2].size());
                System.out.println("" + min);

                Iterator<Integer> group0 = groups[0].iterator();
                Iterator<Integer> group1 = groups[1].iterator();
                Iterator<Integer> group2 = groups[2].iterator();
                for (int index = 0; index < min; index++) {
                    if (group0.hasNext() && group1.hasNext() && group2.hasNext()) {
                        System.out.println(group0.next() + " " + group1.next() + " " + group2.next());
                    }
                }
            }
        }

        public Answer answer() {
            return new A();
        }
    }

    public static void main(String[] args) throws IOException {
        Init init = new Init();
        Algorithm algorithm = new Algorithm(init.persons);
        algorithm.run();
        algorithm.answer().print();
    }
}
