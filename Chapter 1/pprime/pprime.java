
/*
ID: 100021881
LANG: JAVA
PROG: pprime
*/

import java.io.*;
import java.util.*;

public class pprime {
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("pprime.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));
        StringTokenizer line = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(line.nextToken());
        int end = Integer.parseInt(line.nextToken());
        List<Integer> primes = sieveOfEratosthenes((int) Math.sqrt(end) + 1);
        List<Integer> pals = new List<Integer>();
        pals = generatePal(pals, N);
        System.out.println(System.currentTimeMillis()-startTime);
        br.close();
        out.close();
        System.out.println(System.currentTimeMillis()-startTime);
    }
    public static List<Integer> generatePal(List<Integer> digits, int N) {
        List<Integer> pals = new ArrayList<Integer>();
        if (N==0) {
            for (d = 1; d <= 9; d+=2) {
                List<Integer> temp = digits;
                temp.add(d); 
                pals.addAll(generatePal(temp, N+1));
            }
        } else {
            for (d = 1; d <= 9; d++) {
                List<Integer> temp = digits;
                temp.add(d); 
                pals.addAll(generatePal(temp, N+1));
            }
        }
        return pals;
    }
    public static List<Integer> sieveOfEratosthenes(int n) {
        List<Integer> primeNumbers = new LinkedList<>();
        boolean prime[] = new boolean[n + 1];
        Arrays.fill(prime, true);
        for (int p = 2; p * p <= n; p++) {
            if (prime[p]) {
                for (int i = p * 2; i <= n; i += p) {
                    prime[i] = false;
                }
            }
        }
        for (int i = 2; i <= n; i++) {
            if (prime[i]) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }
    public static boolean isPrime(int number, List<Integer> primes) {
        int sqrt = (int) Math.sqrt(number) + 1;
        for (int i = 0; primes.get(i) < sqrt; i++) {
            if (number % primes.get(i) == 0) {
                return false;
            }
        }
        return true;
    }
}
