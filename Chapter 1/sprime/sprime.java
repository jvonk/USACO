
/*
ID: 100021881
LANG: JAVA
PROG: sprime
*/

import java.io.*;
import java.util.*;

public class sprime {
    public static void main(String[] args) throws Exception {
        //long startTime = System.nanoTime();
        BufferedReader br = new BufferedReader(new FileReader("sprime.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));
        int N = Integer.parseInt(br.readLine())+1;
        br.close();
        LinkedHashSet<Integer> results = new LinkedHashSet<Integer>();
        results=find(0, N, results, 0);
        for (int result : results) {
            out.println(result);
        }
        out.close();
        //System.out.println(System.nanoTime() - startTime);
    }

    public static boolean isPrime(int number) {
        int sqrt = (int) Math.sqrt(number) + 1;
        for (int i = 2; i < sqrt; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static LinkedHashSet<Integer> find(int base, int N, LinkedHashSet<Integer> results, int length) {
        length++;
        if (base == 0) {
            results.addAll(find(2, N, results, length));
            results.addAll(find(3, N, results, length));
            results.addAll(find(5, N, results, length));
            results.addAll(find(7, N, results, length));
        } else if (isPrime(base)) {
            if (length<N) {
                base *= 10;
                results.addAll(find(base+1, N, results, length));
                results.addAll(find(base+3, N, results, length));
                results.addAll(find(base+5, N, results, length));
                results.addAll(find(base+7, N, results, length));
                results.addAll(find(base+9, N, results, length));        
            } else {
                results.add(base);
            }
        }
        return results;
    }
}
