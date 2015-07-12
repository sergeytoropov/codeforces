package boldinsv.problems.p432.C;

import java.util.*;
import java.io.*;

public class C7443725
{
    public static void main(String[] args) throws Exception
    {
        FastScanner in = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

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
                }
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

        int n = in.nextInt();

        int[] array = new int[n];
        int[] pos = new int[n];
        for(int b = 0; b < n; b++)
        {
            array[b] = in.nextInt() - 1;
            pos[array[b]] = b;
        }

        ArrayList<int[]> swaps = new ArrayList<int[]>();

        for(int c = 0; c < pos.length; c++)
        {
            while(pos[c] > c)
            {
                int index = Collections.binarySearch(primes, pos[c] - c + 1);
                if(index < 0)
                {
                    index = -(index + 1) - 1;
                }

                int[] swap = {pos[c] - primes.get(index) + 1, pos[c]};

                swaps.add(swap);

                pos[array[swap[0]]] = swap[1];
                pos[array[swap[1]]] = swap[0];

                int temp = array[swap[0]];
                array[swap[0]] = array[swap[1]];
                array[swap[1]] = temp;
            }
        }

        out.println(swaps.size());
        for(int[] swap : swaps)
        {
            out.println((swap[0] + 1) + " " + (swap[1] + 1));
        }

        out.close();
    }

    static class FastScanner
    {
        BufferedReader br;
        StringTokenizer st;

        public FastScanner(InputStream input)
        {
            br = new BufferedReader(new InputStreamReader(input));
            st = new StringTokenizer("");
        }

        public String next() throws IOException
        {
            if(st.hasMoreTokens())
            {
                return st.nextToken();
            }
            else
            {
                st = new StringTokenizer(br.readLine());
                return next();
            }
        }

        public int nextInt() throws IOException
        {
            return Integer.parseInt(next());
        }
    }
}