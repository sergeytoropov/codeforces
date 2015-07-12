package boldinsv.problems.p491.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;

public class C491A {

    public static class Init {
        public int a;
        public int b;

        public Init() throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            a = Integer.parseInt(reader.readLine());
            b = Integer.parseInt(reader.readLine());
            reader.close();
        }
    }

    interface Answer {
        void print();
    }

    interface Printable {
        Answer answer();
    }

    public static class Algorithm implements Printable {
        private int left;
        private int right;

        private LinkedList<Integer> leftList = new LinkedList<Integer>();
        private LinkedList<Integer> rightList = new LinkedList<Integer>();

        public Algorithm(int left, int right) {
            this.left = left;
            this.right = right;
        }

        public void run() {
            leftList.clear();
            rightList.clear();

            int height = 1;
            leftList.addFirst(height);
            if (left == 0) {
                leftList.clear();
                rightList.addFirst(height);
            }

            while (left > 0 || right > 0) {
                if (right-- > 0) {
                    rightList.addFirst(++height);
                }
                if (left > 0 && left > right) {
                    leftList.addLast(++height);
                    left--;
                }
            }
        }

        private class A implements Answer {
            public void print() {
                StringBuilder builder = new StringBuilder();
                ListIterator<Integer> iter;

                iter = leftList.listIterator();
                while (iter.hasNext()) {
                    builder.append(iter.next());
                    builder.append(" ");
                }

                iter = rightList.listIterator();
                while (iter.hasNext()) {
                    builder.append(iter.next());
                    builder.append(" ");
                }

                System.out.println(builder.toString().trim());
            }
        }

        public Answer answer() {
            return new A();
        }
    }

    public static void main(String[] args) throws IOException {
        Init init = new Init();
        Algorithm algorithm = new Algorithm(init.a, init.b);
        algorithm.run();
        algorithm.answer().print();
    }
}
