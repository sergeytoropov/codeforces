package boldinsv.problems.p508.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class C508B {
    public static int[] currency;
    public static boolean found;

    public static void init() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            char[] items = reader.readLine().toCharArray();

            currency = new int[items.length];
            for (int index = 0; index < items.length; index++) {
                currency[index] = Integer.parseInt(String.valueOf(items[index]));
            }
            reader.close();
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public static void change(int from, int to) {
        found = true;

        int temp       = currency[from];
        currency[from] = currency[to];
        currency[to]   = temp;
    }

    public static int[] part1() {
        int[] array = null;

        if (currency[0] % 2 == 0) {
            array = Arrays.copyOf(currency, currency.length);

            array[0] = currency[currency.length - 1];
            array[array.length - 1] = currency[0];
        }
        return array;
    }

    public static int[] part2() {
        int[] array = null;

        if (currency.length > 2) {
            array = Arrays.copyOf(currency, currency.length);

            int pos = 1;
            int max = currency[pos];

            for (int index = 1; index < currency.length - 1; index++) {
                if (max < currency[index]) {
                    max = currency[index];
                    pos = index;
                }
            }

            int temp = array[0];
            array[0] = array[pos];
            array[pos] = temp;
        }

        return array;
    }

    public static int[] part3() {
        int[] array = null;

        if (currency.length > 2) {
            int min = -1;

            for (int index = 1; index < currency.length - 1; index++) {
                if (currency[index] % 2 == 0) {
                    min = index;

                    if (currency[min] <= currency[currency.length - 1]) {
                        break;
                    }
                }
            }

            if (min != -1) {
                array = Arrays.copyOf(currency, currency.length);

                int temp = array[min];
                array[min] = array[array.length - 1];
                array[array.length - 1] = temp;
            }
        }

        return array;
    }

    public static boolean max(ArrayList<int[]> list) {
        Iterator<int[]> iter = list.iterator();

        while (iter.hasNext()) {
            int[] array = iter.next();

            if (array == null) {
                iter.remove();
            }
        }

        while (list.size() >= 2) {

            int[] a = list.get(0);
            int[] b = list.get(1);

            int removeIndex = 0;
            for (int index = 0; index < a.length && index < b.length; index++) {
                if (a[index] < b[index]) {
                    removeIndex = 1;
                    break;
                }
            }

            list.remove(removeIndex);
        }

        if (list.size() == 1) {
            currency = Arrays.copyOf(list.get(0), list.get(0).length);
            return true;
        }

        return false;
    }

    // четное
    public static void even() {
        ArrayList<int[]> list = new ArrayList<int[]>();
        list.add(part1());
        list.add(part2());
        list.add(part3());

        found = max(list);
    }

    // нечетное
    public static void odd() {
        int pos = -1;

        for (int index = 0; index < currency.length - 1; index++) {
            if (currency[index] % 2 == 0) {
                pos = index;

                if (currency[currency.length - 1] > currency[pos]) {
                    break;
                }
            }
        }

        if (pos != -1) {
            change(pos, currency.length - 1);
        }
    }

    public static void run() {
        found = false;
        if (currency[currency.length - 1] % 2 == 0) {
            even();
        } else {
            odd();
        }
    }

    public static void print() {
        if (found) {
            StringBuilder builder = new StringBuilder();

            for (int digit: currency) {
                builder.append(digit);
            }
            System.out.println(builder.toString());
        } else {
            System.out.println("-1");
        }
    }

    public static void main(String[] args) {
        init();
        run();
        print();
    }
}
