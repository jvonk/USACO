/*
ID: 100021881
LANG: JAVA
PROG: fact4
*/

// if you want to turn all the debug on, just find and replace "// DEBUG " with nothing

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.Deque;
import java.util.LinkedList;

/*
 * fact4
 */
public class fact4 {
    public static void main(String[] args) throws Exception {
        // set startTime to measure how long the program takes
        long startTime = System.currentTimeMillis();

        // create input BufferedReader from file
        BufferedReader br = new BufferedReader(new FileReader("fact4.in"));

        int N = Integer.parseInt(br.readLine());

        br.close();

        /* OLD CODE
        int digit = 1;
        for (int i = 1; i <= N; i++) {
            int t = i % 10;
            if (t > 1) {
                digit *= t;
                while (digit % 10 == 0) {
                    digit /= 10;
                }
                digit %= 10;
            }
        }
        */
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fact4.out")));
        //out.println(digit);
        out.println(D(N));
        out.close();
        // print final time taken
        System.out.println(System.currentTimeMillis() - startTime);
    }
    public static int D (int N) {
        int[] arr = new int[] {1, 1, 2, 6, 4, 2, 2, 4, 2, 8};
        if (N < 10) {
            return arr[N];
        }
        int div = N/10;
        int rem = div%10;
        int res = -1;
        if (rem%2==0) {
            res = 6 * D(Math.floorDiv(N,5)) * arr[N%10];
        } else {
            res = 4 * D(Math.floorDiv(N,5)) * arr[N%10];
        }
        while (res % 10 == 0) {
            res /= 10;
        }
        res%=10;
        return res;
    }
}