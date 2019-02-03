/*
ID: 100021881
LANG: JAVA
PROG: buylow
*/

import java.io.*;
import java.util.*;
import java.math.*;

/**
* buylow
*/
public class buylow {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("buylow.in"));
        int N = Integer.parseInt(br.readLine());
        int[] prices = new int[N];
        int[] previous = new int[N];
        BigInteger[] count = new BigInteger[N];
        Arrays.fill(count, BigInteger.ZERO);
        int n = 0;
        String str;
        while((str = br.readLine()) != null) {
            String[] line = str.split(" ");
            for(int i = 0; i < line.length; i++) {
                prices[n++] = Integer.parseInt(line[i]);
            }
        }
        br.close();
        int max = 0;
        TreeSet<Integer> visited = new TreeSet<Integer>();
        for(int i = N - 1; i >= 0; i--) {
            previous[i] = 1;
            count[i] = BigInteger.ONE;
            visited.clear();
            for(int j = i + 1; j < N; j++) {
                if(prices[i] <= prices[j]) {
                    continue;
                }
                if(previous[j] >= previous[i]) {
                    previous[i] = previous[j] + 1;
                    visited.clear();
                    count[i] = BigInteger.ZERO;
                }
                if(previous[i] == previous[j] + 1 && !visited.contains(prices[j])) {
                    visited.add(prices[j]);
                    count[i] = count[i].add(count[j]);
                }
            }
            max = Math.max(max, previous[i]);
        }
        BigInteger ans = BigInteger.ZERO;
        for(int j = 0; j < N; j++) {
            if(max == previous[j] && !visited.contains(prices[j])) {
                ans = ans.add(count[j]);
                visited.add(prices[j]);
            }
        }
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("buylow.out")));
        out.println(max + " " + ans);
        out.close();
        System.exit(0);
    }
}
