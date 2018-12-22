/*
ID: 100021881
LANG: JAVA
PROG: mixmilk
*/

// if you want to turn all the debug on, just find and replace "// DEBUG " with nothing

import java.io.*;
import java.util.*;

/*
 * mixmilk
 */
public class mixmilk {
    public static void main(String[] args) throws Exception {
        // set startTime to measure how long the program takes
        long startTime = System.currentTimeMillis();

        // create input BufferedReader from file
        BufferedReader br = new BufferedReader(new FileReader("mixmilk.in"));

        int[] bucket1 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] bucket2 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] bucket3 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        br.close();

        for (int i = 0; i < 33; i++) {
            int[] res = solve(bucket1, bucket2);
            bucket1[1] = res[0];
            bucket2[1] = res[1];
            res = solve(bucket2, bucket3);
            bucket2[1] = res[0];
            bucket3[1] = res[1];
            res = solve(bucket3, bucket1);
            bucket3[1] = res[0];
            bucket1[1] = res[1];
        }
        int[] res = solve(bucket1, bucket2);
        bucket1[1] = res[0];
        bucket2[1] = res[1];

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mixmilk.out")));
        out.println(bucket1[1]);
        out.println(bucket2[1]);
        out.println(bucket3[1]);
        out.close();
        // print final time taken
        System.out.println(System.currentTimeMillis() - startTime);
        System.exit(0);
    }

    public static int[] solve(int[] bucket1, int[] bucket2) {
        int diff = bucket2[0] - bucket2[1];
        if (diff >= bucket1[1]) {
            bucket2[1] += bucket1[1];
            bucket1[1] = 0;
        } else {
            bucket2[1] = bucket2[0];
            bucket1[1] -= diff;
        }
        return new int[] { bucket1[1], bucket2[1] };
    }
}