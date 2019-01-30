/*
ID: 100021881
LANG: JAVA
PROG: nuggets
*/

import java.io.*;
import java.util.*;

/**
 * find minimum number that cannot be made by adding any subset of an array of components
 * @author  Johan Vonk <johan.d.s.vonk@gmail.com>
 */
public class nuggets {
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("nuggets.in"));
        int N = Integer.parseInt(br.readLine());
        int[] values = br.lines().mapToInt(Integer::parseInt).toArray();
        br.close();
        int limit = Arrays.stream(values).sum();
        int[] primesStart = new int[] {2, 3, 5};
        int[] primesRun = new int[] {2, 3};
        boolean done = false;
        for(int prime : primesStart) {
            if(done) {
                break;
            }
            int first = values[0] % prime;
            done = true;
            for(int value : values) {
                if(value % prime != first) {
                    done = false;
                    break;
                }
            }
        }

        int max = 0;
        if(!done) {
            Set<Integer> set = new HashSet<Integer>();
            set.add(0);
            for(int i = 1; i <= 64261; i++) {
                for(int j : primesRun) {
                    if((i % j == 0 && set.contains(i / j))) {
                        set.add(i);
                        break;
                    }
                }
                if(!set.contains(i)) {
                    for(int value : values) {
                        if(set.contains(i - value)) {
                            set.add(i);
                            break;
                        }
                    }
                }
                if(!set.contains(i)) {
                    max = i;
                }
            }
            //System.out.println(set);
        }
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nuggets.out")));
        out.println(max);
        out.close();
        System.out.println(System.currentTimeMillis() - startTime);
        System.exit(0);
    }
}
