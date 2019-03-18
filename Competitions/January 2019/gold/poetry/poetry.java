/*
ID: 100021881
LANG: JAVA
PROG: poetry
*/

import java.io.*;
import java.util.*;

public class poetry {
    public static long MOD = 1000000007L;
    public static int N, M, K;
    public static Word[] w;
    public static void main (String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("poetry.in"));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        K = Integer.parseInt(line[2]);
        w=new Word[N];
        for (int i = 0; i < N; i++) {
            line = br.readLine().split(" ");
            w[i] = new Word (Integer.parseInt(line[0]), Integer.parseInt(line[1]));
        }
        int[] f = new int[26];
        for (int i = 0; i < M; i++) {
            f[br.readLine().charAt(0)-'A']++;
        }
        br.close();
        long[] ways = new long[K+1];
        long[] c = new long[K+1];
        ways[0] = 1L;
        for (int i = 0; i <= K; i++) {
            for (int j = 0; j < N; j++) {
                if (w[j].s+i>K) continue;
                if (w[j].s+i==K) {
                    c[w[j].c] = mod(c[w[j].c]+ways[i]);
                }
                ways[w[j].s + i]=mod(ways[w[j].s + i]+ways[i]);
            }
        }
        long ans = 1L;
        for (int freq : f) {
            if (freq==0) continue;
            long sum = 0L;
            for (int i = 0; i < c.length; i++) {
                if (c[i]==0) continue;
                sum=mod(sum+pow(c[i], freq));
            }
            ans=mod(ans*sum);
        }
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("poetry.out")));
        out.println(ans);
        out.close();
        System.out.println(System.currentTimeMillis() - startTime);
        System.exit(0);
    }
    public static long mod(long a) {
        return (a+MOD)%MOD;
    }
    public static long pow(long a, int b) {
        if (b==0) return 1;
        if (b==1) return mod(a);
        long ans = pow(a, b/2);
        ans = mod(ans*ans);
        if (b%2==1) return mod(ans*a);
        return mod(ans);
    }
    public static class Word {
        int s, c;
        public Word(int a, int b) {
            s=a;
            c=b;
        }
    }
}