package boldinsv.problems.p378.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C378A {

    interface Data {
        void read();
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
        public int a;
        public int b;
        public String answer;

        private class InputData implements Data {
            public void read() {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                try {
                    String[] items = reader.readLine().split(" ");

                    a = Integer.parseInt(items[0]);
                    b = Integer.parseInt(items[1]);

                    reader.close();
                } catch (IOException ioe) {
                    System.out.println(ioe);
                }
            }
        }

        public void run() {
            int aCounter = 0;
            int bCounter = 0;
            int equals = 0;

            for (int cube = 1; cube <= 6; cube++) {
                if (Math.abs(a - cube) < Math.abs(b - cube)) {
                    aCounter += 1;
                } else if (Math.abs(b - cube) < Math.abs(a - cube)) {
                    bCounter += 1;
                } else {
                    equals += 1;
                }
            }

            answer = aCounter + " " + equals + " " + bCounter;
        }

        private class OutputData implements Answer {
            public void print() {
                System.out.println(answer);
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
        Algorithm object = new Algorithm();
        object.data().read();
        object.run();
        object.answer().print();
    }
}
