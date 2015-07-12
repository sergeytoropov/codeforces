package boldinsv.problems.p432.C;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class C7443725Test {

    @Test
    public void primes() {
        boolean[] prime = new boolean[100001];
        for(int x = 2; x < prime.length; x++)
        {
            prime[x] = true;

        }

        for(int y = 2; y * y < prime.length; y++)
        {
            if(prime[y])
            {
                for(int z = y * y; z < prime.length; z += y)
                {
                    prime[z] = false;
//                    System.out.print(z + ", ");
                }
               // System.out.println();
            }
        }

        ArrayList<Integer> primes = new ArrayList<Integer>();
        for(int a = 0; a < prime.length; a++)
        {
            if(prime[a])
            {
                primes.add(a);
            }
        }

        for (Integer i: primes) {
            System.out.println("" + i);
        }
        System.out.println("amount = " + primes.size());
    }
}