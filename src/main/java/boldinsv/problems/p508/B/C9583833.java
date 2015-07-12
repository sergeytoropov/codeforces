package boldinsv.problems.p508.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


// Разобрать вариант!

public class C9583833 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int lastnum = Integer.parseInt(str.charAt(str.length() - 1) + "");
        boolean cont = true;
        int lasteven = -1;

        for (int i = 0; i < str.length() - 1 && cont; i++) {
            String st = str.charAt(i) + "";
            int num = Integer.parseInt(st);
            if (num % 2 == 0 && num < lastnum) {
                String result = str.substring(0, i) + lastnum + str.substring(i + 1, str.length() - 1) + num;
                cont = false;
                System.out.println(result);
            } else if (num % 2 == 0) {
                lasteven = i;
            }
        }
        if (cont && lasteven != -1) {
            String st = str.charAt(lasteven) + "";
            int num = Integer.parseInt(st);
            String result = str.substring(0, lasteven) + lastnum + str.substring(lasteven + 1, str.length() - 1) + num;
            System.out.println(result);
        } else if(cont) {
            System.out.println(-1);
        }

    }

}