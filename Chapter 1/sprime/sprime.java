
/*
ID: 100021881
LANG: JAVA
PROG: sprime
*/

import java.io.*;
import java.util.*;

public class sprime {
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("sprime.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));
        int N = Integer.parseInt(br.readLine());
        br.close();
        List<Integer> temp = new ArrayList<Integer>();
        out.println(find(temp, N));
        out.close();
        System.out.println(System.currentTimeMillis()-startTime);
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
    public static List<Integer> find(List<Integer> base, int N) {
        if (base.size() < N) {
            int sum = 0;
            int count = 0;
            for (int i = 1; count < base.size(); i*=10) {
                sum+=i*base.get(count);
                count++;
            }
            temp = base;
            temp.insert(0, 0);
            if (isPrime(temp)) {
                
            }
        }
        return base;
    }
}
