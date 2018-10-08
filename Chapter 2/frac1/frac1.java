
/*
ID: 100021881
LANG: JAVA
PROG: frac1
*/

import java.io.*;
import java.util.*;

public class frac1 {
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("frac1.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));
        int[] primes = new int[] { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79,
                83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157 };
        int N = Integer.parseInt(br.readLine());
        List<List<Integer>> fracs = new ArrayList<List<Integer>>();

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= i; j++) {
                int top = j;
                int bottom = i;
                for (int prime : primes) {
                    while (top % prime == 0 && bottom % prime == 0 && bottom > 1) {
                        top /= prime;
                        bottom /= prime;
                    }
                }
                double div = top / bottom;
                boolean clean = false;
                List<Integer> temp = new ArrayList<Integer>();
                temp.add(top);
                temp.add(bottom);
                for (int k = 0; k < fracs.size(); k++) {
                    int a = fracs.get(k).get(0) * bottom;
                    int b = top * fracs.get(k).get(1);
                    if (a>b) {
                        fracs.add(k, temp);
                        clean=true;
                        break;
                    } else if (a==b) {
                        clean=true;
                        break;
                    }
                }
                if(!clean) {
                    fracs.add(temp);
                }
            }
        }
        for (List<Integer> result : fracs) {
            out.println(result.get(0) + "/" + result.get(1));
        }
        br.close();
        out.close();
        System.out.println(System.currentTimeMillis() - startTime);
    }


    public static ArrayList<ArrayList<Integer>> sortArray(ArrayList<ArrayList<Integer>> results) {
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            if (results.size() > 1) {
                for (int i = 1; i < results.size(); i++) {
                    if (results.get(i - 1).get(0) * results.get(i).get(1) > results.get(i).get(0)
                            * results.get(i - 1).get(1)) {
                        Collections.swap(results, i - 1, i);
                        swapped = true;
                    }
                }
            }
        }
        return results;
    }
}
