
/*
ID: 100021881
LANG: JAVA
PROG: ariprog
*/

import java.io.*;
import java.util.*;

public class ariprog {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("ariprog.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));
        int N = Integer.parseInt(br.readLine()); // length of arithmetic progressions to search. All arithmetic
                                                 // progressions must be of this length
        int M = Integer.parseInt(br.readLine()); // upper bound for bisquares (p^2+q^2) with p and q being less than M
        int max = M * M * 2;
        boolean[] bisquares = new boolean[max + 1];
        ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
        for (int p = 0; p <= M; p++) {
            for (int q = 0; q <= M; q++) {
                bisquares[p * p + q * q] = true;
            }
        }
        // makes array with true if it is a bisquare

        // output of the form "a b" with a being the start of the arithmetic progression
        // and b being the distance between each one
        for (int i = 0; i < max; i++) {
            if (!bisquares[i])
                continue;
            diffLoop: for (int j = 1; j <= (max-i) / (N - 1); j++) {
                for (int k = 1; k <= N - 1; k++) {
                    if (!bisquares[i + j * k])
                        continue diffLoop;
                }
                ArrayList<Integer> temp = new ArrayList<Integer>();
                temp.add(j);
                temp.add(i);
                results.add(temp);
            }
        }
        results = sortArray(results);
        if (results.size() > 0) {
            for (ArrayList<Integer> result : results) {
                out.println(result.get(1) + " " + result.get(0));
            }
        } else {
            out.println("NONE");
        }
        out.close();

        br.close();
    }

    public static ArrayList<ArrayList<Integer>> sortArray(ArrayList<ArrayList<Integer>> results) {
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            if (results.size()>1) {
                for (int i = 1; i < results.size(); i++) {
                    if (results.get(i - 1).get(0) > results.get(i).get(0)) {
                        Collections.swap(results, i - 1, i);
                        swapped = true;
                    } else if (results.get(i - 1).get(0) == results.get(i).get(0) && results.get(i - 1).get(1) > results.get(i).get(1)) {
                        Collections.swap(results, i - 1, i);
                        swapped = true;
                    }
                }
            }
        }
        return results;
    }
}
