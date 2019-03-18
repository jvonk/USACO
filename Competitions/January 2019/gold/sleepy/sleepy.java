/*
ID: 100021881
LANG: JAVA
PROG: sleepy
*/

import java.io.*;
import java.util.*;

public class sleepy {
    static int N;
    static int[] p;
    public static void main (String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("sleepy.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sleepy.out")));
        N = Integer.parseInt(br.readLine());
        String[] s = br.readLine().split(" ");
        p = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            p[i] = Integer.parseInt(s[i]);
        }
        int loc = 0;
        loop: while (true) {
            int j = p[0];
            loc++;
            //1 2 4 3
            for (int i = p.length-2; i >= 0; i--) {
                if (p[i]>p[i+1]) {
                    System.out.println(i);
                    for (int k = 0; k < i; k++) {
                        p[k]=p[k+1];
                    }
                    p[i]=j;
                    continue loop;
                }
            }
            break loop;
        }
        br.close();
        out.close();
        System.out.println(System.currentTimeMillis() - startTime);
        System.exit(0);
    }
}