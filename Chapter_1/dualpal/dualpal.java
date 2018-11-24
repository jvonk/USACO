
/*
ID: 100021881
LANG: JAVA
PROG: dualpal
*/

import java.io.*;
import java.util.*;

public class dualpal {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("dualpal.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));
        String l = br.readLine();
        StringTokenizer line = new StringTokenizer(l);
        int N = Integer.parseInt(line.nextToken());
        int S = Integer.parseInt(line.nextToken());
        int count = 0;
        for (int i = S + 1; count < N; i++) {
            int numBases = 0;
            for (int base = 2; base <= 10; base++) {
                String result = Integer.toString(i, base);
                String reverse = new StringBuilder(result).reverse().toString();
                if (reverse.equals(result)) {
                    numBases++;
                }
            }
            if (numBases >= 2) {
                count++;
                out.println(i);
            }
        }
        out.close();
        br.close();
    }
}