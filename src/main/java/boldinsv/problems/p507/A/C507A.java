package boldinsv.problems.p507.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class C507A {
    private static final int INSTRUMENT = 0;
    private static final int ORDER = 1;

    public static class Init {
        public int n;
        public int k;
        public int[] musicalInstruments;

        public Init() throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            int[] items =
                    Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
            n = items[0];
            k = items[1];

            musicalInstruments =
                    Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();

            reader.close();
        }

        public Integer[][] getMusicalInstruments() {
             return IntStream.range(0, musicalInstruments.length)
                    .mapToObj(index -> new Integer[][] {{Integer.valueOf(musicalInstruments[index])}, {index}})
                    .reduce(
                            new Integer[2][musicalInstruments.length],
                            (acc, element) -> {
                                int index = element[ORDER][0];
                                acc[INSTRUMENT][index] = element[INSTRUMENT][0];
                                acc[ORDER][index] = index + 1;
                                return acc;
                            });
        }
    }

    public static class Algoritm {
        private int hours;
        private Integer[][] musicalInstruments;
        private int quantity;

        public Algoritm(int hours, Integer[][] musicalInstuments) {
            this.hours = hours;
            this.musicalInstruments = musicalInstuments;
            this.quantity = 0;
        }

        public void sort() {
            IntStream
                    .range(0, musicalInstruments[INSTRUMENT].length)
                    .forEach(i ->
                            IntStream.range(i, musicalInstruments[INSTRUMENT].length)
                                    .filter(j -> musicalInstruments[INSTRUMENT][i] > musicalInstruments[INSTRUMENT][j])
                                    .forEach(j -> {
                                        int tempInstrument = musicalInstruments[INSTRUMENT][i];
                                        int tempOrder = musicalInstruments[ORDER][i];

                                        musicalInstruments[INSTRUMENT][i] = musicalInstruments[INSTRUMENT][j];
                                        musicalInstruments[ORDER][i] = musicalInstruments[ORDER][j];

                                        musicalInstruments[INSTRUMENT][j] = tempInstrument;
                                        musicalInstruments[ORDER][j] = tempOrder;
                                }));
        }

        public boolean run() {
            sort();

            Integer[] answer = Arrays.stream(musicalInstruments[INSTRUMENT])
                    .collect(Collectors.reducing(
                            new Integer[] {hours, 0},
                            value -> new Integer[] {value, 0},
                            (acc, element) -> {
                                if (acc[0] >= element[0]) {
                                    acc[0] -= element[0];
                                    acc[1] += 1;
                                }
                                return acc;
                            }));

            hours = answer[0];
            quantity = answer[1];

            return true;
        }

        public int getQuantity() {
            return quantity;
        }

        public int[] getOrder() {
            return Arrays.stream(musicalInstruments[ORDER]).limit(quantity).mapToInt(value -> value).toArray();
        }

        public Integer[][] getMusicalInstruments() {
            return musicalInstruments;
        }
    }

    public static void main(String[] args) throws IOException {
        Init init = new Init();
        Algoritm algoritm = new Algoritm(init.k, init.getMusicalInstruments());

        algoritm.run();

        System.out.println(algoritm.getQuantity());
        if (algoritm.getQuantity() > 0) {
            System.out.println(
                    Arrays.stream(algoritm.getOrder()).mapToObj(String::valueOf).collect(Collectors.joining(" "))
            );
        }
    }
}
