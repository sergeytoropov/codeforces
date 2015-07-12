package boldinsv.problems.p486.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class C486A {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BigInteger n = new BigInteger(reader.readLine());
        reader.close();

        if (n.mod(new BigInteger("2")).equals(new BigInteger("0"))) {
            n = n.divide(new BigInteger("2"));
        } else {
            n = n.add(new BigInteger("1")).divide(new BigInteger("2")).multiply(new BigInteger("-1"));
        }
        System.out.println(n.toString());
    }
}
