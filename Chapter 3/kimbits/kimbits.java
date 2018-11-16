
/*
ID: 100021881
LANG: JAVA
PROG: kimbits
*/

import java.io.*;
import java.util.*;

public class kimbits {
    private static int N, L, top;
    private static long I;
    private static String[] list;
    private static PrintWriter out;
    private static boolean flag=true;

    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("kimbits.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("kimbits.out")));
 
        //StringTokenizer l = new StringTokenizer(br.readLine());
        String[] l = (br.readLine()).split(" ", 3);
        N = Integer.parseInt(l[0]);
        L = Integer.parseInt(l[1]);
        I = Long.parseLong(l[2]);

        top = 0;
        flag=true;
        recurse("", 0);
        br.close();
        out.close();
        System.out.println(System.currentTimeMillis() - startTime);
    }
    private static void recurse (String str, int numOnes) {
        if (!flag) return;
        if (str.length()==N) {
            top++;
            if (top==I) {
                out.println(str);
                flag = false;
            }
            return;
        }

        recurse(str+"0", numOnes);
        if (numOnes<L)
            recurse(str+"1", numOnes+1);
    }
}