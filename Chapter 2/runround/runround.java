
/*
ID: 100021881
LANG: JAVA
PROG: runround
*/

import java.io.*;
import java.util.*;

public class runround {
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("runround.in"));
        int M = Integer.parseInt(br.readLine());
        br.close();
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("runround.out")));
        boolean works = false;
        int i = String.valueOf(M).length() - 1;
        while (!works) {
            //M+=Math.pow(10, String.valueOf(M).length()-1-i);
            M++;
            char[] num = String.valueOf(M).toCharArray();
            for (int j = 0; j < num.length; j++) {
                if (num[j]=='0') {
                    num[j]++;
                }
                for (int k = 0; k < j; k++) {
                    if (num[k]==num[j]) {
                        if (num[j] != '9') {
                            M = Integer.parseInt(String.valueOf(num));
                            M += Math.pow(10, num.length - 1 - j);
                            num = String.valueOf(M).toCharArray();
                        } else {
                            M = Integer.parseInt(String.valueOf(num));
                            M += Math.pow(10, num.length - 1 - j);
                            num = String.valueOf(M).toCharArray();
                        }
                        j=0;
                        k=0;
                        break;
                    }
                }
            }
            String visited = work(num);
            char[] check = visited.toCharArray();
            works = check[check.length - 1] == num[0];
            for (i = 0; i < num.length; i++) {
                if (!works) {
                    break;
                }
                if (check.length - num.length - 1 + i < 0 || check[check.length - num.length - 1 + i] != num[i]) {
                    works = false;
                }
            }
        }
        out.println(M);
        System.out.println(System.currentTimeMillis() - startTime);
        out.close();
    }

    public static String work(char[] num) {
        boolean[] looped = new boolean[num.length];
        String visited = "";
        for (int i = -1; i != 0;) {
            if (i == -1) {
                i = 0;
            }
            if (!looped[i]) {
                looped[i] = true;
                // visited+=num[i]+" ";
                int j;
                for (j = i + 1; j <= (i + num[i] - '0'); j++) {
                    visited += num[j % (num.length)];
                }
                // visited+="\n";
                i = j - 1;
                i %= num.length;
            } else {
                return visited;
            }
        }
        for (int i = 0; i < num.length; i++) {
            if(!looped[i]) {
                visited+="?";
            }
        }
        return visited;
    }
}
