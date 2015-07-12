package boldinsv.problems.p494.A;

import java.util.ArrayList;
import java.util.Scanner;

public class C9149288A {

    private static boolean balance(ArrayList<Integer> a) {
        for (int i = 0; i < a.size(); i++) {
            int c = a.get(i);
            if (c > 1) {
                a.set(i, --c);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        //System.out.println(s);
        int len = s.length();
        int c = 0, ind = 0;
        boolean fl = false;
        ArrayList<Integer> al = new ArrayList<Integer>();
        for (int i = 0; i < len; i++) {
            if (fl) break;
            switch (s.charAt(i)) {
                case '(':
                    c++;
                    break;
                case ')':
                    if (c > 0) c--;
                    else {
                        if (balance(al)) {}
                        else fl = true;
                    }
                    break;
                case '#':
                    if (c > 0) {
                        //ind = al.size();
                        al.add(c);
                        c = 0;
                    } else {
                        if (balance(al)) { al.add(1); }
                        else fl = true;
                    }
            }
        }

        if (fl || c > 0) {
            System.out.println(-1);
            return;
        }

        for (Integer i : al) {
            System.out.println(i);
        }
    }

}