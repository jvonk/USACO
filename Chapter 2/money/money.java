
/*
ID: 100021881
LANG: JAVA
PROG: zerosum
*/

import java.io.*;
import java.util.*;

public class zerosum {
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("zerosum.in"));
        int N = Integer.parseInt(br.readLine());
        br.close();
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("zerosum.out")));
        out.println(N);
        out.close();
        System.out.println(System.currentTimeMillis() - startTime);
    }
}
