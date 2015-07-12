package boldinsv.problems.p380.A;

import java.util.*;
import java.io.*;

public class C5676696A {
    public static void main(String[] args) throws IOException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        n = Integer.parseInt(rd.readLine());
        StringTokenizer st;
        A = new int[n];
        L = new int[n];
        C = new int[n];
        S = new long[n];
        int cnt = 0;
        for(int i=0; i<n; i++){
            st = new StringTokenizer(rd.readLine());
            int type = Integer.parseInt(st.nextToken());
            if(type==1){
                int added = Integer.parseInt(st.nextToken());
                A[i] = added;
                L[i] = -1;
                C[i] = -1;
                S[i] = (i==0 ? 1 : S[i-1] + 1);
                if(cnt<100001){
                    array[cnt] = A[i];
                    cnt++;
                }
            }
            else{
                A[i] = -1;
                L[i] = Integer.parseInt(st.nextToken());
                C[i] = Integer.parseInt(st.nextToken());
                S[i] = S[i-1] + 1L*L[i]*1L*C[i];
                for(int x=0; x<C[i]; x++){
                    if(cnt>=100001)
                        break;
                    for(int y=0; y<L[i]; y++){
                        if(cnt>=100001)
                            break;
                        array[cnt] = array[y];
                        cnt++;
                    }
                }
            }
        }

        //  System.out.println(Arrays.toString(array));
        int m = Integer.parseInt(rd.readLine());
        st = new StringTokenizer(rd.readLine());
        //System.out.println(Arrays.toString(S));
        for(int i=0; i<m; i++){
            long q = Long.parseLong(st.nextToken()) - 1;
            pw.print(answerFor(q) + (i < m-1 ? " ": ""));
        }
        pw.println();
        pw.flush();
        pw.close();
    }

    static int answerFor(long index){
        int k = binSearch(0, n-1, index);
        if(A[k]!=-1)
            return A[k];

        int rem = (int)  ((index - S[k-1]) % L[k]);
        return array[rem];
    }


    static int binSearch(int L, int R, long index){
        if(L==R) return L;
        if(R==L+1) return S[L]-1>=index ? L : R;
        int M = (L+R)>>1;
        return S[M]-1 >= index ? binSearch(L, M, index) : binSearch(M+1, R, index);
    }


    static int[] array = new int[100002];
    static int[] L, C;
    static int[] A;
    static long[] S;
    static int n;
}