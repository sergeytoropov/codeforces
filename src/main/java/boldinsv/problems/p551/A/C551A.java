package boldinsv.problems.p551.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class C551A {
    public static int n;
    public static List<Integer> listAi = new ArrayList<>();
    public static List<String> listResult = new ArrayList<>();

    public static void init() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            try {
                n = Integer.parseInt(reader.readLine());

                String[] items = reader.readLine().split(" ");

                listAi.clear();
                for (int index = 0; index < items.length; index++) {
                    listAi.add(Integer.parseInt(items[index]));
                }
            } finally {
                reader.close();
            }
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public static void run() {
        listResult.clear();
        listAi.stream().forEach(overValue -> {
            Integer positiion = listAi.stream()
                    .filter(belowValue -> overValue < belowValue)
                    .reduce(1, (acc, belowValue) -> acc + 1);
            listResult.add("" + positiion);
        });
    }

    public static void print() {
        String answer = listResult.stream().reduce("", (acc, value) -> acc + value + " ");
        System.out.println(answer.trim());
    }

    public static void main(String[] args) {
        init();
        run();
        print();
    }
}
