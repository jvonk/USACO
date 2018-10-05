
/*
ID: 100021881
LANG: JAVA
PROG: pprime
*/

import java.io.*;
import java.util.*;

public class pprime {
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("pprime.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));
        StringTokenizer line = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(line.nextToken());
        int end = Integer.parseInt(line.nextToken());
        boolean prime[] = new boolean[end + 1];
        Arrays.fill(prime, true);
        for (int p = 2; p * p <= end; p++) {
            if (prime[p]) {
                int temp = start-start%p;
                int begin = Math.max(p * 2, temp);
                for (int i = begin; i <= end; i += p) {
                    prime[i] = false;
                }
            }
        }
        for (int i = start; i <= end; i++) {
            if (prime[i]) {
                String temp = String.valueOf(i);
                String temp2 = new StringBuilder(temp).reverse().toString();
                if (temp.equals(temp2)) {
                    out.println(i);
                }    
            }
        }
        br.close();
        out.close();
        System.out.println(System.currentTimeMillis()-startTime);
    }
}
