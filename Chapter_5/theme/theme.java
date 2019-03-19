/*
ID: 100021881
LANG: JAVA
PROG: theme
*/

import java.util.*;
import java.io.*;

public class theme {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("theme.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("theme.out")));
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        int count = 0;
        loop: while (true) {
            String[] arr = br.readLine().split(" ");
            for (String s : arr) {
                nums[count++] = Integer.parseInt(s);
                if (count >= N) break loop;
            }
        }

        int max = -1;
        for (int i = 0; i < N-5; i++) {
            for (int j = 5; j < N-i; j++) {
                int diff = nums[j+i]-nums[i];
                for (int k = 1; k < Math.min(j, N-i-j) && max<Math.min(j, N-i-j)-1; k++) {
                    if (nums[i+j+k]-nums[i+k]!=diff) break;
                    if (k>=4 && k>max) max=k;
                }
            }
        }
        out.println(max+1);
        br.close();
        out.close();
    }
}