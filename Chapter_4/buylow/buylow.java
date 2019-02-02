/*
ID: 100021881
LANG: JAVA
PROG: buylow
*/

import java.io.*;
import java.util.*;

/**
 * buylow
 */
public class buylow {
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("buylow.in"));
        br.close();
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("buylow.out")));
        out.close();
        System.out.println(System.currentTimeMillis() - startTime);
        System.exit(0);
    }
}
