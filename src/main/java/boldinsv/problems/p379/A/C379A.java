package boldinsv.problems.p379.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C379A {
    public int a;
    public int b;
    public int answer;

    public void init() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] items = reader.readLine().split(" ");

        a = Integer.parseInt(items[0]);
        b = Integer.parseInt(items[1]);

        reader.close();
    }

    private enum States {LIGHT, CREATE, EXIT};

    public void run() {
        int total = a;
        int finish = 0;
        States state = States.LIGHT;

        answer = 0;
        for (boolean isContinue = true; isContinue; ) {

            switch (state) {
                case LIGHT:
                    state = States.CREATE;

                    while (total-- > 0) {
                        finish++;
                        answer++;
                    }
                    break;

                case CREATE:
                    state = States.EXIT;

                    total = finish / b;
                    finish = finish % b;

                    if (total > 0) {
                        state = States.LIGHT;
                    }
                    break;

                case EXIT:
                    isContinue = false;
                    break;
            }
        }
    }

    public void print() {
        System.out.println("" + answer);
    }

    public static void main(String[] args) throws IOException {
        C379A algorithm = new C379A();

        algorithm.init();
        algorithm.run();
        algorithm.print();
    }
}
