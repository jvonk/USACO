
/*
ID: 100021881
LANG: JAVA
PROG: hamming
*/

import java.io.*;
import java.util.*;

public class hamming {
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("hamming.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));
        StringTokenizer line = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(line.nextToken());
        int B = Integer.parseInt(line.nextToken());
        int D = Integer.parseInt(line.nextToken());
        System.out.println(N + " " + B + " " + D);
        HashSet<Long> nums = new HashSet<Long>();
        long num = 0;
        while (nums.size() < N) {
            if (hammingDistance(nums, num, D) || num == 0) {
                nums.add(num);
                if (nums.size() % 10 == 0 || nums.size()==N) {
                    out.println(num);
                } else {
                    out.print(num + " ");
                }
            }
            num++;
        }
        br.close();
        out.close();
        System.out.println(System.currentTimeMillis() - startTime);
    }

    public static boolean hammingDistance(HashSet<Long> nums, long num, int D) {
        for (long n : nums) {
            if (Long.bitCount(n ^ num) < D) {
                return false;
            }
        }
        return true;
    }
}
