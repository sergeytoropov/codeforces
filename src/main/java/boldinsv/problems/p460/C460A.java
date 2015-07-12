package boldinsv.problems.p460;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C460A {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] params = reader.readLine().split(" ");
        reader.close();

        int n = Integer.parseInt(params[0]);
        int m = Integer.parseInt(params[1]);

        int days = 0;
        while (n > 0) {
            days += 1;
            n = n - 1;
            if (days % m == 0) {
                n += 1;
            }
        }
        System.out.print("" + days);
    }
}
