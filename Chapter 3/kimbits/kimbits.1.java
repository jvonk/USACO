
/*
ID: 100021881
LANG: JAVA
PROG: kimbits
*/

import java.io.*;
import java.util.*;

public class kimbits {
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("kimbits.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("kimbits.out")));

        StringTokenizer l = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(l.nextToken());
        int L = Integer.parseInt(l.nextToken());
        int I = Integer.parseInt(l.nextToken());
        boolean[] res = solve(N, L, I, new boolean[N+1]);
        String str = "";
        for (int i = 0; i <= N; i++) {
            str+=res[i]?"1":"0";
        }
        out.println(str);
        br.close();
        out.close();
        System.out.println(System.currentTimeMillis() - startTime);
    }
    public static boolean[] solve (int N, int L, int I, boolean[] arr) {
        if (I==-1) {
            return arr;
        }
        int k = -1;
        int total = 0;
        for (k = 1; k <= N; k++) {
            total=0;
            int[] sums = new int[N+1];
            sums[0]=1;
            sums[k]=1;
            for (int i = 1; 2*i <= k; i++) {
                sums[i]+=sums[i-1]*(k-i+1)/i;
                sums[k-i]=sums[i];
            }
            for (int i = 0; i <= L; i++) {
                //System.out.println(k + "\t" + i + "\t" + sums[i]);
                total+=sums[i];
            }
            if (total>=I) {
                break;
            }
        }
        System.out.println(N+"\t"+L+"\t"+I);
        if (k<=N || true) {
            arr[k]=true;
            return solve(k-1, L-1, I-total+1, arr);
        } else {
            return solve(N-1, L, I-total+1, arr);
        }
    }
}