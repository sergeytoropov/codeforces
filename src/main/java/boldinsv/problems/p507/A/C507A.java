package boldinsv.problems.p507.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C507A {
    private static final int INSTRUMENT = 0;
    private static final int ORDER = 1;

    public static class Init {
        public int n;
        public int k;
        public int[] musicalInstruments;

        public Init() throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String[] items;

            items = reader.readLine().split(" ");
            n = Integer.parseInt(items[0]);
            k = Integer.parseInt(items[1]);

            items = reader.readLine().split(" ");
            musicalInstruments = new int[n];
            for (int index = 0; index < n; index++) {
                musicalInstruments[index] = Integer.parseInt(items[index]);
            }
            reader.close();
        }

        public int[][] getMusicalInstruments() {
            int[][] mi = new int[2][musicalInstruments.length];

            for (int index = 0; index < musicalInstruments.length; index++) {
                mi[INSTRUMENT][index] = musicalInstruments[index];
                mi[ORDER][index] = index + 1;
            }

            return mi;
        }
    }

    public static class Algoritm {
        private int hours;
        private int[][] musicalInstruments;
        private int quantity;

        public Algoritm(int hours, int[][] musicalInstuments) {
            this.hours = hours;
            this.musicalInstruments = musicalInstuments;
            this.quantity = 0;
        }

        public void sort() {
            for (int i = 0; i < musicalInstruments[INSTRUMENT].length; i++) {
                for (int j = i; j < musicalInstruments[INSTRUMENT].length; j++) {

                    if (musicalInstruments[INSTRUMENT][i] > musicalInstruments[INSTRUMENT][j]) {
                        int tempInstrument = musicalInstruments[INSTRUMENT][i];
                        int tempOrder = musicalInstruments[ORDER][i];

                        musicalInstruments[INSTRUMENT][i] = musicalInstruments[INSTRUMENT][j];
                        musicalInstruments[ORDER][i] = musicalInstruments[ORDER][j];

                        musicalInstruments[INSTRUMENT][j] = tempInstrument;
                        musicalInstruments[ORDER][j] = tempOrder;
                    }
                }
            }
        }

        public boolean run() {
            sort();

            quantity = 0;
            for (int index = 0; index < musicalInstruments[INSTRUMENT].length; index++) {
                if (hours < musicalInstruments[INSTRUMENT][index]) {
                    break;
                } else {
                    hours -= musicalInstruments[INSTRUMENT][index];
                    quantity++;
                }
            }

            return true;
        }

        public int getQuantity() {
            return quantity;
        }

        public int[] getOrder() {
            int[] order = null;

            if (quantity > 0) {
                order = new int[quantity];
                for (int index = 0; index < quantity; index++) {
                    order[index] = musicalInstruments[ORDER][index];
                }
            }
            return order;
        }

        public int[][] getMusicalInstruments() {
            return musicalInstruments;
        }
    }

    public static void main(String[] args) throws IOException {
        Init init = new Init();
        Algoritm algoritm = new Algoritm(init.k, init.getMusicalInstruments());

        algoritm.run();

        System.out.println(algoritm.getQuantity());
        if (algoritm.getQuantity() > 0) {
            int[] order = algoritm.getOrder();
            String strOrder = "";
            for (int index = 0; index < order.length; index++) {
                strOrder += order[index] + " ";
            }
            System.out.println(strOrder.trim());
        }
    }
}
